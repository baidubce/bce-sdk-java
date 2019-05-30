/*
 * Copyright 2018 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.core.protocol.mqtt;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTT connect base
 * Created by liuzhenxing01 on 2018/10/9.
 */
public class MqttConnection {

    private MqttAsyncClient mqttAsyncClient;
    private MqttConnectOptions connectionOptions;
    private IMqttActionListener mqttMessageListener;
    private static final String TLS_V_1_2 = "TLSv1.2";

    public MqttConnection(String serverURI, String clientId, String userName, String
            password, SocketFactory socketFactory, MqttCallback mqttCallbackListener,
                          IMqttActionListener mqttMessageListener) throws MqttException {

        if (serverURI == null || mqttCallbackListener == null || mqttMessageListener == null) {
            throw new IllegalArgumentException("serverURI, mqttCallbackListener, mqttMessageListener can't be null!");
        }
        this.mqttAsyncClient = new MqttAsyncClient(serverURI, clientId, new MemoryPersistence());
        this.mqttAsyncClient.setManualAcks(true);
        this.connectionOptions = new MqttConnectOptions();
        this.initOptions(userName, password, socketFactory);
        this.mqttMessageListener = mqttMessageListener;
        this.mqttAsyncClient.setCallback(mqttCallbackListener);
    }

    private void initOptions(String userName, String password, SocketFactory socketFactory) {
        this.connectionOptions.setKeepAliveInterval(180);
        this.connectionOptions.setCleanSession(true);
        this.connectionOptions.setUserName(userName);
        this.connectionOptions.setSocketFactory(socketFactory);
        if (password != null && !password.isEmpty()) {
            this.connectionOptions.setPassword(password.toCharArray());
        }
    }

    public MqttAsyncClient getMqttAsyncClient() {
        return this.mqttAsyncClient;
    }

    /**
     * is connect success
     * @return false
     */
    public boolean isConnected() {
        if (null != this.mqttAsyncClient) {
            return this.mqttAsyncClient.isConnected();
        }
        return false;
    }

    public IMqttToken disconnect() throws MqttException {
        if (this.mqttAsyncClient != null) {
            return this.mqttAsyncClient.disconnect();
        }
        return null;
    }

    public void close() throws MqttException {
        if (this.mqttAsyncClient != null) {
            this.mqttAsyncClient.close();
        }
    }

    public void openConnection() {
        try {
            this.mqttAsyncClient.connect(connectionOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * publish message
     * @param message
     */
    public void publishMessage(BceIotMessage message) {
        String topic = message.getTopic();
        MqttMessage mqttMessage = new MqttMessage(message.getPayload());
        mqttMessage.setQos(message.getQos());

        try {
            this.mqttAsyncClient.publish(topic, mqttMessage, message, mqttMessageListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * subscribe Topic
     * @param message
     */
    public void subscribeTopic(BceIotMessage message) {
        try {
            this.mqttAsyncClient.subscribe(message.getTopic(), message.getQos(), message, mqttMessageListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unsubscribeTopic(BceIotMessage message) {
        try {
            this.mqttAsyncClient.unsubscribe(message.getTopic(), message, mqttMessageListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param keystore
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws IOException
     * @throws CertificateException
     */
    public static SSLSocketFactory getFactory(KeyStore keystore) {
        try {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            if (null == keystore) {
                tmf.init((KeyStore) null);
            } else {
                tmf.init(keystore);
            }
            SSLContext context = SSLContext.getInstance(TLS_V_1_2);
            context.init(null, tmf.getTrustManagers(), null);
            return context.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get SSLSocketFactory
     * @param caKeystore
     * @param clientKeystore
     * @param keystorePassword
     *
     * @return
     */
    public static SSLSocketFactory getFactory(KeyStore caKeystore, KeyStore clientKeystore, String keystorePassword) {
        try {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(caKeystore);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(clientKeystore, keystorePassword.toCharArray());
            SSLContext context = SSLContext.getInstance(TLS_V_1_2);
            KeyManager[] kms = kmf.getKeyManagers();
            context.init(kms, tmf.getTrustManagers(), null);
            return context.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}