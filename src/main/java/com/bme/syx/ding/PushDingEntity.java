package com.bme.syx.ding;


import lombok.Data;

@Data
public class PushDingEntity {
    public int id;
    public String dingURL;
    public String dingSecret;
    public String sendeen;
    public String msg;
    public int sleeptime;
    public int customerID;
    public String groupName;
    public int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDingURL() {
        return dingURL;
    }

    public void setDingURL(String dingURL) {
        this.dingURL = dingURL;
    }

    public String getDingSecret() {
        return dingSecret;
    }

    public void setDingSecret(String dingSecret) {
        this.dingSecret = dingSecret;
    }

    public String getSendeen() {
        return sendeen;
    }

    public void setSendeen(String sendeen) {
        this.sendeen = sendeen;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSleeptime() {
        return sleeptime;
    }

    public void setSleeptime(int sleeptime) {
        this.sleeptime = sleeptime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
