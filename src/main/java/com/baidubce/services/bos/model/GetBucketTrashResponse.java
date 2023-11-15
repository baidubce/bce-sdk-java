package com.baidubce.services.bos.model;

/**
 * GetBucketTrashResponse class
 */
public class GetBucketTrashResponse extends BosResponse {
    private String trashDir;


    public String getTrashDir() {
        return trashDir;
    }

    public void setTrashDir(String trashDir) {
        this.trashDir = trashDir;
    }

    @Override
    public String toString() {
        return "GetBucketTrashResponse{" +
                "trashDir='" + trashDir + '\'' +
                '}';
    }
}
