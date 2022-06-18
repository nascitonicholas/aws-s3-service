package br.com.aws.s3.application.core.domain;

import java.util.List;

public class PropertiesConfig {

    private String bucketName;
    private String fileName;
    private List<Properties> list;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Properties> getList() {
        return list;
    }

    public void setList(List<Properties> list) {
        this.list = list;
    }

}
