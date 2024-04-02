package com.baidubce.services.iothisk;

import static com.baidubce.services.iothisk.model.EncodeType.HEX;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothisk.model.ActiveRequest;
import com.baidubce.services.iothisk.model.ActiveResponse;
import com.baidubce.services.iothisk.model.AuthRequest;
import com.baidubce.services.iothisk.model.CipherRequest;
import com.baidubce.services.iothisk.model.CipherResponse;

/**
 * Unit test for iot hisk service
 */
public class IotHiskClientTest {

    // qa-sandbox
    private static final String ACCESSKEY = "YOUR_ACCESSKEY";
    private static final String SECRETKEY = "YOUR_SECRETKEY";
    private static final String ENDPOINT = "YOUR_ENDPOINT";

    private static final String TEST_DEVICE_ID = "openApiSdkTestId";

    private IotHiskClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESSKEY, SECRETKEY))
                .withEndpoint(ENDPOINT);
        client = new IotHiskClient(config);
    }

    @Test
    public void encryptDefaultTest() {
        String message = UUID.randomUUID().toString();
        CipherRequest request = new CipherRequest();
        request.setData(Base64.encodeBase64String(message.getBytes()));

        CipherResponse response = client.encrypt(TEST_DEVICE_ID, request);
        assertThat(response, hasProperty("data"));
        assertThat(Base64.decodeBase64(response.getData()).length, equalTo(256));
    }

    @Test
    public void encryptThenDecryptTest() {
        String message = UUID.randomUUID().toString();
        CipherRequest request = new CipherRequest();
        request.setData(Base64.encodeBase64String(message.getBytes()));

        CipherResponse response = client.encrypt(TEST_DEVICE_ID, request);
        request.setData(response.getData());
        response = client.decrypt(TEST_DEVICE_ID, request);
        assertThat(response, hasProperty("data"));
        assertThat(Base64.decodeBase64(response.getData()), equalTo(message.getBytes()));
    }

    @Test
    public void encryptHexTest() {
        String message = UUID.randomUUID().toString();
        CipherRequest request = new CipherRequest();
        request.setData(DatatypeConverter.printHexBinary(message.getBytes()));
        request.setType(HEX);

        CipherResponse response = client.encrypt(TEST_DEVICE_ID, request);
        request.setData(response.getData());
        response = client.decrypt(TEST_DEVICE_ID, request);
        assertThat(response, hasProperty("data"));
        assertThat(response.getData(),
                equalTo(DatatypeConverter.printHexBinary(message.getBytes())));
    }

    @Test
    public void decryptTest() {
        String textMessage = "baidu-bce-iot-hisk";
        String cipherBase64 = "KA8htXzwL1NYGcL2gRQgHgXe1cAYM7P3oaTWVH1sK4VINzVyxICia0F23ks87DKGveF5eXbQGLYoVQnW6A"
                + "+XO9MfLIY1tvi8vt5HIf0KKRBouIKTQF5ELIv5DhKfSqZoJLmOPypwzjDhXQNZ8ZottoluSgFI7+NBqNVqv41z9rlJeU/oKT"
                + "Dv4N+jkM22swq5EPvK0ryM0jh/pCpgcBOx82pZUNw00oZjO6KscglEWYhpSi/lKgDXX85ZdGzK/hjyb4eyORyO0Ksjw5S/hV"
                + "nruCAgc8flBT9RUGXUF7x+OARlf4FaWP41yT3HGSluum6MlV5qQAYVythiKwJdKHuksA==";

        CipherRequest request = new CipherRequest();
        request.setData(cipherBase64);
        CipherResponse response = client.decrypt(TEST_DEVICE_ID, request);
        assertThat(response, hasProperty("data"));
        assertThat(response.getData(), equalTo(Base64.encodeBase64String(textMessage.getBytes())));
    }

    @Test
    public void activeTest() {
        String cipherBase64 = "XCqxddON0cXXdFkm5l6TizO/bc5ynL59gbxnOjba1Cp9g290rH0e/jAb0EQxk1glRVKGYQuIrQntNtQ+tgT"
                + "yM2nNpm6YKnk5HP0IC8lRQ1Y2STOgO7zYEmHVz1OLZUtNktHQiPSf0q41//fuswGNcIiGhalt5No9Vwe6N1hKe/x1lOk4nMkV"
                + "pCmhW8KhVKQQ4zFPpqFirr8AYfrCXhgzjWnhE675u4p9eiyYrhgPsYp7QUM5WbRp2Zsim9HUYreV9ZmUir0LaaPORddHqyQV4"
                + "Cf/INKa6hrB5RvrHxHjBVfZJD07WfFF727xn/MrRqidHOkaldWJ2Q/CNci76Z4gxw==";

        ActiveRequest request = new ActiveRequest();
        request.setData(cipherBase64);
        ActiveResponse response = client.active(TEST_DEVICE_ID, request);
        assertThat(response, hasProperty("data"));
        assertThat(response.getData(), equalTo(TEST_DEVICE_ID));
        assertThat(response.getSdkType(), equalTo(0));
    }

    @Test
    public void authTest() {
        String authCode = "0-0-1537156652-2hRP7quCFWZs8GzEHV8cVjTknDy4XF9BuBrfrx/++NHP9ttobxqiX9UAp2FMz83t5df7l14j45"
                + "Yus63tdYsrclUZ6PVCnSSIgbmOuxuon9amasAUDTmm2C4Pv4BpdJmb4NBJR/b8IVBqtFAWbC6dvtvS9eOLN5jfucDplOwFuWo=";

        AuthRequest request = new AuthRequest();
        request.setAuthCode(authCode);
        client.auth(TEST_DEVICE_ID, request);
    }

}
