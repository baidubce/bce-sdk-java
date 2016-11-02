/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.http;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * Daemon thread to periodically check connection pools for idle connections.
 *
 * <p>
 * Connections sitting around idle in the HTTP connection pool for too long will eventually be terminated by the BCE end
 * of the connection, and will go into CLOSE_WAIT. If this happens, sockets will sit around in CLOSE_WAIT, still using
 * resources on the client side to manage that socket. Many sockets stuck in CLOSE_WAIT can prevent the OS from creating
 * new connections.
 *
 * <p>
 * This class closes idle connections before they can move into the CLOSE_WAIT state.
 *
 * <p>
 * This thread is important because by default, we disable Apache HttpClient's stale connection checking, so without
 * this thread running in the background, cleaning up old/inactive HTTP connections, we'd see more IO exceptions when
 * stale connections (i.e. closed on the BCE side) are left in the connection pool, and requests grab one of them to
 * begin executing a request.
 */
public final class IdleConnectionReaper extends Thread {

    /**
     * The period between invocations of the idle connection reaper.
     */
    private static final int PERIOD_IN_MILLIS = 20 * 1000;

    /**
     * The list of registered connection managers, whose connections will be periodically checked and idle connections
     * closed.
     */
    private static List<HttpClientConnectionManager> connectionManagers = Lists.newArrayList();

    /**
     * Singleton instance of the connection reaper.
     */
    private static IdleConnectionReaper instance = new IdleConnectionReaper();

    static {
        instance.start();
    }

    /**
     * Shared log for any errors during connection reaping.
     */
    private static final Logger logger = LoggerFactory.getLogger(IdleConnectionReaper.class);

    /**
     * Private constructor - singleton pattern.
     */
    private IdleConnectionReaper() {
        super("java-sdk-http-connection-reaper");
        this.setDaemon(true);
    }

    /**
     * Registers the given connection manager with this reaper;
     *
     * @return true if the connection manager has been successfully registered; false otherwise.
     */
    public static synchronized boolean registerConnectionManager(HttpClientConnectionManager connectionManager) {
        if (instance == null) {
            return false;
        }
        return connectionManagers.add(connectionManager);
    }

    /**
     * Removes the given connection manager from this reaper, and shutting down the reaper if there is zero connection
     * manager left.
     *
     * @return true if the connection manager has been successfully removed; false otherwise.
     */
    public static synchronized boolean removeConnectionManager(HttpClientConnectionManager connectionManager) {
        if (instance == null) {
            return false;
        }
        return connectionManagers.remove(connectionManager);
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(PERIOD_IN_MILLIS);

                // Copy the list of managed ConnectionManagers to avoid possible
                // ConcurrentModificationExceptions if registerConnectionManager or
                // removeConnectionManager are called while we're iterating (rather
                // than block/lock while this loop executes).
                List<HttpClientConnectionManager> connectionManagers = null;
                synchronized (IdleConnectionReaper.class) {
                    connectionManagers = Lists.newArrayList(this.connectionManagers);
                }
                for (HttpClientConnectionManager connectionManager : connectionManagers) {
                    // When we release connections, the connection manager leaves them
                    // open so they can be reused. We want to close out any idle
                    // connections so that they don't sit around in CLOSE_WAIT.
                    try {
                        connectionManager.closeExpiredConnections();
                        connectionManager.closeIdleConnections(60, TimeUnit.SECONDS);
                    } catch (Throwable t) {
                        logger.warn("Unable to close idle connections", t);
                    }
                }
            } catch (Throwable t) {
                logger.debug("Reaper thread: ", t);
            }
        }
    }

    /**
     * Shuts down the thread, allowing the class and instance to be collected.
     *
     * <p>
     * Since this is a daemon thread, its running will not prevent JVM shutdown. It will, however, prevent this class
     * from being unloaded or garbage collected, in the context of a long-running application, until it is interrupted.
     * This method will stop the thread's execution and clear its state. Any use of a service client will cause the
     * thread to be restarted.
     *
     * @return true if an actual shutdown has been made; false otherwise.
     */
    public static synchronized boolean shutdown() {
        if (instance != null) {
            instance.interrupt();
            connectionManagers.clear();
            instance = null;
            return true;
        }
        return false;
    }
}
