package br.com.aws.s3.application.core.domain;

public class Properties {
    private String propertieName;
    private String value;

    public Properties() {
    }

    public Properties(String propertieName, String value) {
        this.propertieName = propertieName;
        this.value = value;
    }

    public String getPropertieName() {
        return propertieName;
    }

    public void setPropertieName(String propertieName) {
        this.propertieName = propertieName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
