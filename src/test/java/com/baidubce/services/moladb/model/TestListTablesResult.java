package com.baidubce.services.moladb.model;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.baidubce.services.moladb.model.transform.ListTablesResponseUnmarshaller;

public class TestListTablesResult {

    @Test
    public void testUnmarshaller() {

        String resp = "{\"lastEvaluatedTableName\":\"tableName3\",\"tableNames\":[\"tableName1\","
                + "\"tableName2\",\"tableName3\"]}";

        ListTablesResponse result = new ListTablesResponse();
        ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(
                result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> names = result.getTableNames();

        Assert.assertEquals(3, names.size());
    }

    @Test
    public void testUnmarshallerLastEmpty() {

        String resp = "{\"tableNames\":[\"tableName1\",\"tableName2\",\"tableName3\"]}";

        ListTablesResponse result = new ListTablesResponse();
        ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(
                result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> names = result.getTableNames();
        Assert.assertEquals(3, names.size());
    }

    @Test
    public void testUnmarshallerNoTable() {

        String resp = "{\"tableNames\":[]}";

        ListTablesResponse result = new ListTablesResponse();
        ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(
                result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> names = result.getTableNames();
        Assert.assertEquals(0, names.size());
    }
}