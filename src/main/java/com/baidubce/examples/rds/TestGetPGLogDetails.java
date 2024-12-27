package com.baidubce.examples.rds;

import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsGetPGLogDetailsRequest;
import com.baidubce.services.rds.model.RdsGetPGLogDetailsResponse;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestGetPGLogDetails {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        RdsGetPGLogDetailsRequest request = new RdsGetPGLogDetailsRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setPglogId("postgresql-2024-07-11_000000.log");
        request.setDownloadValidTimeInSec(3600);
        RdsGetPGLogDetailsResponse response = rdsClient.getPGLogDetails(request);
        print("getPGLogDetails", response);
    }
}
