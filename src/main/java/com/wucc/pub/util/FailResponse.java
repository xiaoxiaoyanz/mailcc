package com.wucc.pub.util;

import java.util.Map;

/**
 * 返回前台失败对象
 */
public class FailResponse extends Response {

    private static final long serialVersionUID = -2485130096025044970L;

    public FailResponse() {
        super();
        setFailFlag();
    }

    public FailResponse(Map<Object, Object> map) {
        super(map);
        setFailFlag();
    }

}
