package com.wucc.pub.util;

/**
 * 使用枚举统一定义异常码和异常信息
 * 规则：业务模块编码+错误操作信息
 *
 * @author xuejx
 */
public enum Status {

    /* 正常*/
    success(200, "成功"), fail(-1, "失败"),

    warn(201, "警告"),

    /*系统级错误*/
    base_param_err(10001, "参数错误"), base_id_err(10002, "无效id"), base_server_exception(10002, "服务端异常"), base_get_fail(10003,
            "获取数据失败"), base_save_fail(10004, "保存数据失败"), base_del_fail(10005, "删除数据失败"), base_upd_fail(10006,
            "更新数据失败 "), base_not_found(10007,
            "未找到记录"), base_method_err(10008, "不存在的方法名"), base_service_unavailabe(10009, "不存在的方法名"),

    /* 以下为业务及错误 */
    /* 基础资料模块 */
    ma_code_null(20001, "代码为空"), ma_name_null(20002, "名称为空"), ma_codename_null(20003, "代码或名称为空"), ma_pnode_noadd(20004,
            "父级节点不允许增加下级"), ma_accoss_noallow(20005,
            "不可以跨级增加"), ma_addone_not(20006, "不可增加一级"), ma_notSysLeaf_notallow(20007, "不允许操作给非末级单位！"),

    /* 账务模块*/
    /* 凭证模板*/
    gl_vouchertem_noenter(30010, "数据异常"), gl_vouchertem_nosend(30010, "模板下发失败"),

    /* 凭证*/
    gl_voucher_nosame_user(30001, "制单人和审核人不可为同一人"), gl_voucher_nocancle_nodel(30002,
            "当前凭证不是已作废状态不能删除"), gl_voucher_sunsys_edit(30003, "禁止修改他人编制的凭证"), gl_voucher_vounnumdate_add(30004,
            "凭证号和凭证日期需同时递增"), gl_voucher_subsysvou_forbid(30005, "不允许修改子系统传过来的凭证"), gl_voucher_addvou_forbid(30006,
            "上期未结账则不允许做本期凭证"), gl_voucher_auditvou_forbid(30007, "上期未结账则不允许审核本期凭证"), gl_voucher_voudate_sysdate(30008,
            "凭证日期不能滞后于系统日期"), gl_voucher_only_delvou(30009, "当前凭证不是未审核或已作废状态不能删除"),
    ;

    private int code;

    private String msg;

    private Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
