package com.baidubce.services.iotdmp.model.product;

public enum ProductAccessType {
    WIFI("WIFI"),
    OTHER_GENERATION("2G/3G/4G"),
    FIFTH_GENERATION("5G"),
    ETHERNET("ETHERNET"),
    NB_IOT("NB-IOT"),
    BLE("BLE"),
    ZIGBEE("ZigBee"),
    MODBUS("Modbus"),
    OPC_UA("OPC UA"),
    HTTP("HTTP/HTTPS"),
    MQTT("MQTT"),
    OTHER("OTHER");

    private String alias;

    ProductAccessType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}