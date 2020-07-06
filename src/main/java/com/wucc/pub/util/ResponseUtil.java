package com.wucc.pub.util;

/**
 * 包装Response
 * @author wxd
 * @date 2020.7.2
 */
public class ResponseUtil {

    /**
     * @param errorMsg    错误提示
     * @param errorStatus 错误状态
     * @param data        正确的数据
     * @return 给前台的对象
     */
    public static Response buildResponse(String errorMsg, Status errorStatus, Object data) {
        Response response;
        if (StringUtil.isNotEmpty(errorMsg)) {
            response = new FailResponse();
            response.setMsg(errorMsg);
            if (errorStatus != null) {
                response.setStatus(errorStatus);
            } else {
                response.setStatus(Status.fail);
            }
        } else {
            response = new SuccessResponse();
            if (data != null) {
                response.setData(data);
            }
            response.setStatus(Status.success);
        }
        return response;
    }

}
