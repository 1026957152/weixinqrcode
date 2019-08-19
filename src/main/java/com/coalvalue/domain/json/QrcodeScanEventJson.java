package com.coalvalue.domain.json;

public class QrcodeScanEventJson {
    public Boolean subscribe;
    /** Id of the customer */
    /** Id of the supplier */
    public String key;
    /** First Name of Customer */

    /** First Name of Customer */

    public String text;

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
