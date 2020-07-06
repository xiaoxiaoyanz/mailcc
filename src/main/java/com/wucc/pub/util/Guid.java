package com.wucc.pub.util;

public class Guid {

    /**
     * 返回32个字符长度的唯一标识
     *
     * @return
     */
    public static String genID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * @param length
     * @return
     * @Title: getGuids
     * @Description: 返回指定长度的UUID数组
     */
    public static String[] getGuids(Integer length) {
        if (length == null) {
            length = 1;
        }
        String[] rowIds = new String[length];
        for (int i = 0; i < length; i++) {
            rowIds[i] = Guid.genID();
        }
        return rowIds;
    }

}
