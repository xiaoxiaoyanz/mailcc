package com.wucc.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makejava
 * @since 2020-07-01 12:10:15
 */
public class Message implements Serializable {
    private static final long serialVersionUID = -71441937496334598L;
    /**
    * 主键
    */
    private String id;
    /**
    * 所属用户id
    */
    private String userId;
    /**
    * 消息主题
    */
    private String messageSubject;
    /**
    * 消息内容
    */
    private Object messageContext;
    /**
    * 消息发送者
    */
    private String messageFrom;
    /**
    * 消息接收者
    */
    private String messageTo;
    /**
    * 创建时间
    */
    private Date createtime;
    /**
    * 是否发送过
    */
    private String isSend;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public Object getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(Object messageContext) {
        this.messageContext = messageContext;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", messageSubject='" + messageSubject + '\'' +
                ", messageContext=" + messageContext +
                ", messageFrom='" + messageFrom + '\'' +
                ", messageTo='" + messageTo + '\'' +
                ", createtime=" + createtime +
                ", isSend='" + isSend + '\'' +
                '}';
    }
}