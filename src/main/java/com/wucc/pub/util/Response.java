package com.wucc.pub.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：定义各种通用的常量值、保存各种参数便于前后台交互的各个环节使用
 * 1、保存各工作环境的各种变量值包括：年度、单位、账套、期间、登陆人、业务日期等
 * 2、定义各种通用变量值
 *
 * @author tianlei
 */
public abstract class Response extends HashMap<Object, Object> {

    private static final long serialVersionUID = 47067542629985123L;

    public Response() {
        super();
    }

    public Response(Map<Object, Object> map) {
        super(map);
    }

    /**
     * 返回前台成功标识
     */
    private static final String FLAG_SUCCESS = "success";

    /**
     * 返回前台警告标识
     */
    private static final String FLAG_WARN = "warn";

    /**
     * 返回前台失败标识
     */
    private static final String FLAG_FAIL = "fail";

    /**
     * 返回前台数据
     */
    protected static final String KEY_DATA = "data";

    /**
     * 返回前台成功失败标志
     */
    protected static final String KEY_FLAG = "flag";

    /**
     * 返回消息
     */
    protected static final String KEY_MSG = "msg";

    /**
     * 返回状态码
     */
    protected static final String KEY_CODE = "code";

    /**
     * 返回禁止信息
     */
    protected static final String KEY_FORBIDINFO = "forbidInfo";

    /**
     * 返回提醒信息
     */
    protected static final String KEY_REMINDERINFO = "reminderInfo";

    /**
     * 返回状态位
     */
    protected static final String KEY_STATUS = "status";

    /**
     * 设置成功标志
     */
    protected void setSuccessFlag() {
        put(KEY_FLAG, FLAG_SUCCESS);
    }

    /**
     * 设置警告标志
     */
    protected void setWarnFlag() {
        put(KEY_FLAG, FLAG_WARN);
    }

    /**
     * 设置失败标志
     */
    protected void setFailFlag() {
        put(KEY_FLAG, FLAG_FAIL);
    }

    /**
     * 设置返回前台消息
     */
    public Response setMsg(String msg) {
        put(KEY_MSG, msg);
        return this;
    }

    public Response setForbidInfo(List<String> forbidInfo) {
        put(KEY_FORBIDINFO, forbidInfo);
        return this;
    }

    public Response setReminderInfo(List<String> reminderInfo) {
        put(KEY_REMINDERINFO, reminderInfo);
        return this;
    }

    /**
     * 获取返回前台消息
     */
    public String getMsg() {
        if (StringUtil.isEmpty(get(KEY_MSG))) {
            return null;
        }
        return get(KEY_MSG).toString();
    }

    /**
     * 设置返回前台数据对象
     */
    public Response setData(Object dataObject) {
        put(KEY_DATA, dataObject);
        return this;
    }

    /**
     * 设置返回前台数据对象
     */
    public Object getData() {
        return get(KEY_DATA);
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccessFlag() {
        if (StringUtil.isEmpty(get(KEY_FLAG))) {
            return false;
        }
        return FLAG_SUCCESS.equals(get(KEY_FLAG).toString());
    }

    /**
     * 状态码
     */
    public Response setStatus(Status status) {
        put(KEY_CODE, status.getCode());
        put(KEY_STATUS, status.getMsg());
        return this;
    }
}
