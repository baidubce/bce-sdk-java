package com.baidubce.services.iothisk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.UUID;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothisk.device.IotHiskDevice;
import com.baidubce.services.iothisk.device.model.ActiveMessage;
import com.baidubce.services.iothisk.device.model.Device;
import com.baidubce.services.iothisk.device.model.DeviceKey;
import com.baidubce.services.iothisk.device.model.DeviceSdkType;
import com.baidubce.services.iothisk.device.model.SeType;
import com.baidubce.services.iothisk.device.seplatform.SecureElement;
import com.baidubce.services.iothisk.device.seplatform.SecureElementFactory;
import com.baidubce.services.iothisk.model.ActiveRequest;
import com.baidubce.services.iothisk.model.ActiveResponse;
import com.baidubce.services.iothisk.model.CipherRequest;
import com.baidubce.services.iothisk.model.CipherResponse;

/**
 * Unit test for iot hisk device client
 */
public class IotHiskDeviceTest {

    private static final String ACCESSKEY = "YOUR_ACCESSKEY";
    private static final String SECRETKEY = "YOUR_SECRETKEY";
    private static final String ENDPOINT = "YOUR_ENDPOINT";

    private IotHiskDevice noneRtcdevice;
    private IotHiskDevice rtcdevice;

    private IotHiskClient cloudTestClient;

    private static final String serialNumber = new String(new byte[]{0x00, 0x00, 0x00, 0x01});
    private static final String expectedDeviceId = "6FDDB0754EDCF7ED9B21796888DAFECB88BCF0A4CE21A1FB63B4FAB21D081FF0";

    @Before
    public void setUp() {
        noneRtcdevice = new IotHiskDevice(buildDevice(DeviceSdkType.NONE_RTC));
        rtcdevice = new IotHiskDevice(buildDevice(DeviceSdkType.RTC));

        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESSKEY, SECRETKEY))
                .withEndpoint(ENDPOINT);
        cloudTestClient = new IotHiskClient(config);
    }

    @Test
    public void getDeviceIdTest() {
        assertThat(noneRtcdevice.getDeviceId(), equalTo(expectedDeviceId));
    }

    @Test
    public void getDeviceActiveDataWithNoneRTCCounterTest() {
        noneRtcdevice.setCurrentCounter(1);
        byte[] activeData = noneRtcdevice.getActiveData();
        // 64 bytes deviceId
        // 4 bytes seId
        // 1 byte sdk type
        // 4 bytes counter
        // 32 bytes signature
        assertThat(activeData.length, equalTo(64 + 4 + 1 + 4 + 32));

        noneRtcdevice.setCurrentCounter(0);
        byte[] plainMessage = noneRtcdevice.decrypt(activeData);

        SecureElement se = getSeFromDevice(buildDevice(DeviceSdkType.NONE_RTC));
        ActiveMessage activeMessage = se.parseActiveMessage(plainMessage);
        se.checkActiveMessage(activeMessage);

        assertThat(activeMessage.getDeviceId(), equalTo(expectedDeviceId));
        assertThat(activeMessage.getSeId(), equalTo(serialNumber));
        assertThat(activeMessage.getSdkType(), equalTo(DeviceSdkType.NONE_RTC));
    }

    @Test
    public void getDeviceActiveDataWithRTCCounterTest() {
        byte[] activeData = rtcdevice.getActiveData();
        assertThat(activeData.length, equalTo(64 + 4 + 1 + 4 + 32));

        byte[]  plainMessage = rtcdevice.decrypt(activeData);
        SecureElement se = getSeFromDevice(buildDevice(DeviceSdkType.RTC));
        ActiveMessage activeMessage = se.parseActiveMessage(plainMessage);
        se.checkActiveMessage(activeMessage);

        assertThat(activeMessage.getDeviceId(), equalTo(expectedDeviceId));
        assertThat(activeMessage.getSeId(), equalTo(serialNumber));
        assertThat(activeMessage.getSdkType(), equalTo(DeviceSdkType.RTC));
    }

    @Test
    public void deviceEncryptDecryptTest() {
        String message = "helloworld";

        byte[] cipherMessage = rtcdevice.encrypt(message.getBytes());
        byte[] plainMessage = rtcdevice.decrypt(cipherMessage);
        assertThat(plainMessage, equalTo(message.getBytes()));
    }

    @Test
    public void noneRtcDeviceCloudActiveTest() {
        IotHiskDevice noneRtcDevice = new IotHiskDevice(buildCloudTestNoneRtcDevice());
        deviceCloudActiveTest(noneRtcDevice, cloudTestClient, DeviceSdkType.NONE_RTC);
    }

    @Test
    public void rtcDeviceCloudActiveTest() {
        IotHiskDevice rtcDevice = new IotHiskDevice(buildCloudTestRtcDevice());
        deviceCloudActiveTest(rtcDevice, cloudTestClient, DeviceSdkType.RTC);
    }

    @Test
    public void noneRtcDeviceCloudEncryptDeviceEncryptTest() {
        IotHiskDevice noneRtcDevice = new IotHiskDevice(buildCloudTestNoneRtcDevice());
        deviceCloudEncryptDeviceEncryptTest(noneRtcDevice, cloudTestClient);
    }

    @Test
    public void rtcDeviceCloudEncryptDeviceEncryptTest() {
        IotHiskDevice rtcDevice = new IotHiskDevice(buildCloudTestRtcDevice());
        deviceCloudEncryptDeviceEncryptTest(rtcDevice, cloudTestClient);
    }

    @Test
    public void noneRtcDeviceDeviceEncryptCloudDecryptTest() {
        IotHiskDevice noneRtcDevice = new IotHiskDevice(buildCloudTestNoneRtcDevice());
        deviceEncryptCloudDecryptTest(noneRtcDevice, cloudTestClient);
    }

    @Test
    public void rtcDeviceDeviceEncryptCloudDecryptTest() {
        IotHiskDevice rtcDevice = new IotHiskDevice(buildCloudTestRtcDevice());
        deviceEncryptCloudDecryptTest(rtcDevice, cloudTestClient);
    }

    private void deviceCloudActiveTest(IotHiskDevice device, IotHiskClient client,
            DeviceSdkType expectedSdkType) {
        String deviceId = device.getDeviceId();
        byte[] activeData = device.getActiveData();

        ActiveRequest activeRequest = new ActiveRequest();
        activeRequest.setData(toBase64FromBytes(activeData));

        ActiveResponse activeResponse = client.active(deviceId, activeRequest);
        assertThat(activeResponse.getData(), equalTo(deviceId));
        assertThat(activeResponse.getSdkType(), equalTo(expectedSdkType.getStatusCode()));
    }

    private void deviceCloudEncryptDeviceEncryptTest(IotHiskDevice device, IotHiskClient client) {
        String deviceId = device.getDeviceId();
        String message = UUID.randomUUID().toString();

        CipherRequest request = new CipherRequest();
        request.setData(toBase64FromBytes(message.getBytes()));
        CipherResponse response = client.encrypt(deviceId, request);

        byte[] plainMessageBytes = device.decrypt(toBytesFromBase64(response.getData()));
        assertThat(plainMessageBytes, equalTo(message.getBytes()));
    }

    private void deviceEncryptCloudDecryptTest(IotHiskDevice device, IotHiskClient client) {
        String deviceId = device.getDeviceId();
        String message = UUID.randomUUID().toString();

        byte[] cipherMessageBytes = device.encrypt(message.getBytes());

        CipherRequest request = new CipherRequest();
        request.setData(toBase64FromBytes(cipherMessageBytes));
        CipherResponse response = client.decrypt(deviceId, request);

        assertThat(toBytesFromBase64(response.getData()), equalTo(message.getBytes()));
    }

    private static String toBase64FromBytes(byte[] data) {
        return Base64.toBase64String(data);
    }

    private static byte[] toBytesFromBase64(String data) {
        return Base64.decode(data);
    }


    private SecureElement getSeFromDevice(Device device) {
        DeviceKey deviceKey = new DeviceKey();
        deviceKey.setSeId(device.getSerialNumber());
        deviceKey.setSeType(device.getType());

        return SecureElementFactory.createSe(device, deviceKey);
    }

    private Device buildDevice(DeviceSdkType deviceSdkType) {
        Device device = new Device();
        device.setType(SeType.MBED_AKEY);
        device.setDeviceCompany("xCompany");
        device.setDeviceType("yProduct");
        device.setMasterKey("1234567890120A0B0C0D0E0F11223344");
        device.setDeviceSdkType(deviceSdkType);
        device.setSerialNumber(serialNumber);

        return device;
    }

    private Device buildCloudTestNoneRtcDevice() {
        Device device = new Device();
        device.setType(SeType.MBED_AKEY);
        device.setDeviceCompany("92DPmr43oFsSEh66554r6nzo0U3qkb4a");
        device.setDeviceType("201KjOMcVce406jxvZtQ1e62OW0375Ic");
        device.setMasterKey("4BB538C286044FCE381BA9A3F4AFF5EF");
        device.setDeviceSdkType(DeviceSdkType.NONE_RTC);
        device.setSerialNumber("D3o20bvX5wo42es6IoRL2p7fMuE5D4O7");

        return device;
    }

    private Device buildCloudTestRtcDevice() {
        Device device = new Device();
        device.setType(SeType.MBED_AKEY);
        device.setDeviceCompany("iqp");
        device.setDeviceType("hrubtjp5nsvf8332y3clhv5gult88lja");
        device.setMasterKey("829C967ADADCEF694F51DA79B00F84B4");
        device.setDeviceSdkType(DeviceSdkType.RTC);
        device.setSerialNumber("test22");

        return device;
    }

}
