/*
 * Copyright 2018-2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.UUID;

import javax.net.ssl.SSLSocketFactory;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.dugo.core.protocol.mqtt.BceIotMessage;
import com.baidubce.services.dugo.core.protocol.mqtt.MqttConnection;

/**
 * keytool -importcert -trustcacerts -alias <aliasName> -file <ca>.crt -keystore ca_trust.keystore
 * openssl pkcs12 -export -in <client>.crt -inkey <client>.key -name cli -out <client>.p12
 * keytool -importkeystore -deststorepass <your password> -destkeystore <keystore> -srckeystore <client>.p12
 * -srcstoretype PKCS12
 * <p>
 * mosquitto_sub -h test.mosquitto.org -p 8884 -t "topic111111" --cafile mosquitto.org.crt --cert client.crt --key
 * client.key
 * mosquitto_sub -h test.mosquitto.org -p 8883 -t "topic111111" --cafile mosquitto.org.crt
 * <p>
 * <p>
 * Created by liuzhenxing01 on 2018/10/9.
 */

public class MqttConnectionTest {

    private static String U0;

    private static String U1;
    private static String U2;
    private static String CLIENT_ID;
    private String caKeystorePath;
    private String clientKeystorePath;
    private static String PASSWORD;
    private static final String TLS_V_1_2 = "TLSv1.2";

    @Before
    public void setUp() {
        U0 = "tcp://test.mosquitto.org";
        U1 = "ssl://test.mosquitto.org:8883";
        U2 = "ssl://test.mosquitto.org:8884";
        CLIENT_ID = "BaiduBceMQTTTest";
        URL resPath = this.getClass().getResource("/");
        caKeystorePath = resPath.getPath() + File.separator + "mqtt" + File.separator + "ca.keystore";
        clientKeystorePath = resPath.getPath() + File.separator + "mqtt" + File.separator + "client.keystore";
        PASSWORD = "11111111";
    }

    /**
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws MqttException
     */
    @Test
    public void mqttSendTestWithTLS() throws IOException, CertificateException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException, MqttException, InterruptedException {
        SSLSocketFactory factory = MqttConnection.getFactory(readCaKeyStore());
        Assert.assertNotNull(factory);
        pubMessage(factory, U1);
    }

    /**
     *
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws MqttException
     * @throws InterruptedException
     * @throws UnrecoverableKeyException
     */
    @Test
    public void mqttSendTestWithClientTLS()
            throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException, MqttException, InterruptedException, UnrecoverableKeyException {
        SSLSocketFactory factory = MqttConnection.getFactory(readCaKeyStore(), readClientKeyStore(), PASSWORD);
        Assert.assertNotNull(factory);
        pubMessage(factory, U2);
    }

    public static class A implements MqttCallback {

        @Override
        public void connectionLost(Throwable throwable) {

        }

        @Override
        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        }
    }

    public static class B implements IMqttActionListener {

        @Override
        public void onSuccess(IMqttToken iMqttToken) {

        }

        @Override
        public void onFailure(IMqttToken iMqttToken, Throwable throwable) {

        }
    }

    public void pubMessage(SSLSocketFactory factory, String serverURI) throws MqttException, InterruptedException {
        MqttConnection connection = new MqttConnection(serverURI, CLIENT_ID + UUID.randomUUID().toString(), null, null,
                factory, new A(), new B());
        connection.openConnection();
        int i = 10;
        while (i > 0) {
            if (connection.isConnected()) {
                BceIotMessage message = new BceIotMessage();
                message.setPayload("baidu test mqtt message".getBytes());
                message.setQos(1);
                message.setTopic("topic111111");
                connection.publishMessage(message);
                connection.publishMessage(message);
                connection.publishMessage(message);
                connection.publishMessage(message);
                connection.publishMessage(message);
                connection.publishMessage(message);
                break;
            }
            Thread.sleep(2000);
            i--;
        }
    }

    /**
     * get the ca key store
     *
     * @return
     */
    private KeyStore readCaKeyStore() {
        KeyStore keystore = null;
        FileInputStream is = null;
        try {
            is = new FileInputStream(caKeystorePath);
            keystore = KeyStore.getInstance("JKS");
            String keypwd = PASSWORD;
            keystore.load(is, keypwd.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return keystore;
    }

    /**
     * get the client key store
     *
     * @return
     */
    private KeyStore readClientKeyStore() {
        KeyStore keystore = null;
        FileInputStream is = null;
        try {
            is = new FileInputStream(clientKeystorePath);
            keystore = KeyStore.getInstance("jks");
            String keypwd = PASSWORD;
            keystore.load(is, keypwd.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return keystore;
    }
}