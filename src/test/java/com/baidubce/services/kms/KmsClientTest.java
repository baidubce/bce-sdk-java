/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.kms;

import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.kms.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import com.baidubce.services.kms.KmsClient;
import com.baidubce.services.kms.KmsClientConfiguration;
import com.baidubce.Protocol;

/**
 * Unit test for KMS client 
 */
public class KmsClientTest {

    // ACCESS_KEY_ID, SECRET_ACCESS_KEY is needed
    private static final String ACCESS_KEY_ID = "XXX";
    private static final String SECRET_ACCESS_KEY = "XXX";
    private static final String ENDPOINT = "bkm.gz.baidubce.com";

    private KmsClient client;

    @Before
    public void setUp() {
        KmsClientConfiguration config = new KmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        config.setProtocol(Protocol.HTTPS);
        client = new KmsClient(config);
    }

    @Test 
    public void createKeyTest() throws Exception {

        CreateKeyRequest createKeyRequest = new CreateKeyRequest();
        CreateKeyRequest createKeyRequestTest = new CreateKeyRequest("CarlSun1",
                Constants.ProtectedBy.SOFTWARE.toString(), "ENCRYPT_DECRYPT",
                Constants.KeySpec.AES_128.toString(),
                Constants.Origin.BAIDU_KMS.toString());
        createKeyRequest.setDescription("CarlSun1");
        createKeyRequest.setKeyUsage("ENCRYPT_DECRYPT");
        try {
            CreateKeyResponse createKeyResponse = client.createKey(createKeyRequest); 
            System.out.println("________createKey_____");
            System.out.println(createKeyResponse.getKeyMetadata().getKeyId());
            System.out.println(createKeyResponse.getKeyMetadata().getCreationDate().toString()); 
            Assert.assertTrue(createKeyResponse.getKeyMetadata().getKeyId() != null);
            Assert.assertTrue(createKeyResponse.getKeyMetadata().getCreationDate().toString().length() > 0);
            Assert.assertTrue(createKeyResponse.getKeyMetadata().getKeyId().length() > 0);
        } catch (BceServiceException e) {
            System.out.println(e.getMessage());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void listKeysTest() throws Exception {
        try {
            ListKeysRequest listKeysRequest = new ListKeysRequest(100, "");
            ListKeysRequest listKeysRequestTest = new ListKeysRequest();
            listKeysRequestTest.setLimit(100);
            listKeysRequestTest.setMarker("");
            ListKeysResponse listKeysResponse = client.listKeys(listKeysRequest);
            List<ListKeysResponse.Key> keys = listKeysResponse.getKeys();
            System.out.println("________listKeys_____");
            for (ListKeysResponse.Key key : keys) {
                System.out.println(key.getKeyId());
            }
            Assert.assertTrue(keys.size() >= 10); 
        } catch (BceServiceException e) {
            System.out.println(e.getMessage());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void encryptTest() throws Exception {
        try {
            EncryptRequest encryptRequest = new EncryptRequest();
            EncryptRequest encryptRequestTest = 
                    new EncryptRequest("c716afcd-59ca-eaf1-5796-bff94d29e20b", "Q2FybFN1biBpcyBnZW5pdXMh");
            encryptRequest.setPlaintext("Q2FybFN1biBpcyBnZW5pdXMh");
            encryptRequest.setKeyId("c716afcd-59ca-eaf1-5796-bff94d29e20b");
            EncryptResponse encryptResponse = client.encrypt(encryptRequest);
            Assert.assertTrue(encryptResponse.getCiphertext().length() > 0);
            System.out.println("________encrppt_____");
            System.out.println(encryptResponse.getCiphertext()); 
            System.out.println(encryptResponse.getKeyId()); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void decryptTest() throws Exception {
        try {
            DecryptRequest decryptRequest = new DecryptRequest();
            DecryptRequest decryptRequestTest = new DecryptRequest("c716afcd-59ca-eaf1-5796-bff94d29e20b","CAESJGRmYWUwMmNjLTFjZTAtMWE5ZS1jN2U2LWRjNjk2ND"
                    + "Q1NDFmNxowHjLscrlg/ksm2bX8RKMFCkD6jw10lq4FCRyz9PvmfhcW8jYnMxHbIze2ia8wcS9sIA6"
                    + "CYgz/wy7YexE5z1Op3XUJ");
            decryptRequest.setCiphertext("CAESJGRmYWUwMmNjLTFjZTAtMWE5ZS1jN2U2LWRjNjk2NDQ1NDFmNxowHjLscrlg/ksm2bX8R" 
                    + "KMFCkD6jw10lq4FCRyz9PvmfhcW8jYnMxHbIze2ia8wcS9sIA6CYgz/wy7YexE5z1Op3XUJ");
            DecryptResponse decryptResponse = client.decrypt(decryptRequest);
            Assert.assertTrue(decryptResponse.getPlaintext().length() > 0);
            System.out.println("________decrypt_____");
            System.out.println(decryptResponse.getKeyId());
            System.out.println(decryptResponse.getPlaintext());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void generateDataKeyTest() throws Exception {
        try {
            GenerateDataKeyRequest generateDataKeyRequest = new GenerateDataKeyRequest();
            GenerateDataKeyRequest generateDataKeyRequestTest = 
                    new GenerateDataKeyRequest("c716afcd-59ca-eaf1-5796-bff94d29e20b", Constants.KeySpec.AES_256, 20);
            generateDataKeyRequest.setKeyId("c716afcd-59ca-eaf1-5796-bff94d29e20b");
            generateDataKeyRequest.setKeySpec(Constants.KeySpec.AES_256);
            generateDataKeyRequest.setNumberOfBytes(20);
            GenerateDataKeyResponse  generateDataKeyResponse = client.generateDataKey(generateDataKeyRequest);
            System.out.println("_______generateDataKey_____");
            Assert.assertTrue(generateDataKeyResponse.getPlaintext().length() > 0);
            System.out.println(generateDataKeyResponse.getCiphertext());
            System.out.println(generateDataKeyResponse.getKeyId());
            System.out.println(generateDataKeyResponse.getPlaintext());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void enableKeyTest() throws Exception {
        try {
            System.out.println("_______EnableKeyTest_____");
            EnableKeyRequest enableKeyRequest = new EnableKeyRequest("aafdd09f-f94a-3dcd-056d-e81dd8c6a40e");
            client.enableKey(enableKeyRequest);
        } catch (Exception e) {
            Assert.assertTrue(false);
            System.out.println(e.getMessage());
        }

        try {
            DisableKeyRequest disableKeyRequset = new DisableKeyRequest();
            disableKeyRequset.setKeyId("aafdd09f-f94a-3dcd-056d-e81dd8c6a40e");
            client.disableKey(disableKeyRequset);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test 
    public void disableKeyTest() throws Exception {
        try {
            System.out.println("_______DisableKeyTest_____");  
            DisableKeyRequest disableKeyRequset = new DisableKeyRequest("dfae02cc-1ce0-1a9e-c7e6-dc69644541f7");
            client.disableKey(disableKeyRequset);
        } catch (Exception e) {
            Assert.assertTrue(false);
            System.out.println(e.getMessage());
        }

        try {
            EnableKeyRequest enableKeyRequest = new EnableKeyRequest();
            enableKeyRequest.setKeyId("dfae02cc-1ce0-1a9e-c7e6-dc69644541f7");
            client.enableKey(enableKeyRequest);
        } catch (Exception e) {
            // Assert.assertTrue(false);
            System.out.println(e.getMessage());
        }
    }

    @Test 
    public void scheduleKeyDeletionTest() throws Exception {
        try {
            System.out.println("_______ScheduleKeyDeletion_____");
            ScheduleKeyDeletionRequest request = new ScheduleKeyDeletionRequest();
            ScheduleKeyDeletionRequest requestTest = 
                    new ScheduleKeyDeletionRequest("e7d3ea11-66ca-dcc3-5874-98e90f4b731f", 8);
            request.setPendingWindowInDays(8);
            request.setKeyId("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
            ScheduleKeyDeletionResponse response = client.scheduleKeyDeletion(request);
            Assert.assertTrue(response.getKeyId().length() > 0);
            System.out.println(response.getKeyId());
            System.out.println(response.getDeletionDate().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            CancelKeyDeletionRequest request = new CancelKeyDeletionRequest("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
            client.cancelKeyDeletion(request);
        } catch (BceServiceException e) {
            System.out.println(e.getMessage());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            EnableKeyRequest enableKeyRequest = new EnableKeyRequest("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
            client.enableKey(enableKeyRequest);
        } catch (Exception e) {
            // Assert.assertTrue(false);
            System.out.println(e.getMessage());
        }

    }
// disble  
    @Test 
    public void cancelKeyDeletionTest() throws Exception {

        try {
            System.out.println("_______CancelKeyDeletion_____");
            CancelKeyDeletionRequest request = new CancelKeyDeletionRequest();
            request.setKeyId("6e89b030-cdad-a339-e535-2fe9c28e5a25");
            client.cancelKeyDeletion(request);
        } catch (Exception e) {
            // Assert.assertTrue(false);
            System.out.println(e.getMessage());
        }

        try {
            EnableKeyRequest enableKeyRequest = new EnableKeyRequest("6e89b030-cdad-a339-e535-2fe9c28e5a25");
            client.enableKey(enableKeyRequest);
        } catch (Exception e) {
            // Assert.assertTrue(false);
            System.out.println(e.getMessage());
        }

        try {
            ScheduleKeyDeletionRequest request = new ScheduleKeyDeletionRequest();
            request.setPendingWindowInDays(8);
            request.setKeyId("6e89b030-cdad-a339-e535-2fe9c28e5a25");
            ScheduleKeyDeletionResponse response = client.scheduleKeyDeletion(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @Test 
    public void describeKeyTest() throws Exception {

        try {
            System.out.println("__________describeKey_______");
            DescribeKeyRequest request = new DescribeKeyRequest();
            request.setKeyId("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
            DescribeKeyResponse response = client.describeKey(request);
            Assert.assertTrue(response.getKeyMetadata().getKeyId().length() > 0);
            System.out.println(response.getKeyMetadata().getKeyId());
            System.out.println(response.getKeyMetadata().getCreationDate().toString());
            System.out.println(response.getKeyMetadata().getKeyState());
            System.out.println(response.getKeyMetadata().getDescription());
            System.out.println(response.getKeyMetadata().getKeyUsage());
            System.out.println(response.getKeyMetadata().getRegion());
            System.out.println(response.getKeyMetadata().getDeletionDate());
            DescribeKeyRequest requestTest = new DescribeKeyRequest("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
        } catch (BceServiceException e) {
            System.out.println(e.getMessage());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getParametersForImportTest() throws Exception {
        try {
            System.out.println("__________getParametersForImport_______");
            GetParametersForImportRequest request = new GetParametersForImportRequest();
            request.setKeyId("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
            request.setPublicKeyEncoding(Constants.PublicKeyEncoding.BASE64.toString());
            GetParametersForImportResponse response = client.getParametersForImport(request);
            System.out.println(response.getImportToken());
            System.out.println(response.getKeyId());
            System.out.println(response.getPublicKey());
            System.out.println(response.getTokenValidTill());
        } catch (BceServiceException e) {
            System.out.println(e.getMessage());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void importKeyTest() throws Exception {
        try {
            System.out.println("__________importKey_______");
            ImportKeyRequest request = new ImportKeyRequest();
            request.setKeyId("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
            request.setEncryptedKey("your encryped key");
            request.setImportToken("your token");
            request.setKeySpec(Constants.KeySpec.AES_128.toString());
            request.setKeyUsage("ENCRYPT_DECRYPT");
            KmsResponse response = client.importKey(request);
        } catch (BceServiceException e) {
            System.out.println(e.getMessage());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void importAsymmetricForImportTest() throws Exception {
        try {
            System.out.println("__________importKey_______");
            ImportAsymmetricKeyRequest request = new ImportAsymmetricKeyRequest();
            request.setKeyId("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
            request.setAsymmetricKeyUsage("ENCRYPT_DECRYPT");
            request.setEncryptedKeyEncryptionKey("your EncryptedKey by EncryptionKey");
            EncryptedSm2Key sm2Key = new EncryptedSm2Key();
            sm2Key.setEncryptedPrivateKey("yout privatekey encrypted by your EncryptedKey then base64 encode");
            sm2Key.setPublicKeyDer("your publickey encrypted by base64");
            request.setEncryptedSm2Key(sm2Key);
            request.setImportToken("your token");
            KmsResponse response = client.importAsymmetricKey(request);
        } catch (BceServiceException e) {
            System.out.println(e.getMessage());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateRoatationTest() throws Exception {
        try {
            System.out.println("__________updateRotation_______");
            UpdateRotationRequest request = new UpdateRotationRequest();
            request.setKeyId("e7d3ea11-66ca-dcc3-5874-98e90f4b731f");
            request.setRotateCycle(1);
            client.updateRotateKey(request);
        } catch (BceServiceException e) {
            System.out.println(e.getMessage());
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120
