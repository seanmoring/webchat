package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    @JSONField(name = "onlineCount")
    private int onlineCount;

    @JSONField(name = "username")
    private String username;

    @JSONField(name = "msg")
    private String msg;

    @JSONField(name ="type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOnlineCount() { return onlineCount; }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getMsg() {
        return msg;
    }

    public String getUsername() {
        return username;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
