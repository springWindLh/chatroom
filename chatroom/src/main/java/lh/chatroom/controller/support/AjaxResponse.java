package lh.chatroom.controller.support;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by lh on 2016/4/6.
 */
public abstract class AjaxResponse {
    private static final String SAVE_SUCCESS_MSG = "保存成功";
    private static final String SAVE_FAILURE_MSG = "保存失败";

    @JSONField(ordinal = 1, name = "code")
    private int code;

    @JSONField(ordinal = 2, name = "msg")
    private String msg;

    @JSONField(ordinal = 3, name = "detail")
    private String detail;

    @JSONField(ordinal = 4, name = "jumpUrl")
    private String jumpUrl;

    @JSONField(ordinal = 5, name = "data")
    private Object data;

    public AjaxResponse() {
    }

    public AjaxResponse(int code) {
        this.code = code;
    }

    public static final AjaxResponse ok() {
        return new BaseResponse(1);
    }

    public static final AjaxResponse success() {
        return ok().msg(SAVE_SUCCESS_MSG);
    }

    public static final AjaxResponse fail() {
        return new BaseResponse(0);
    }

    public static final AjaxResponse failure() {
        return fail().msg(SAVE_FAILURE_MSG);
    }

    public AjaxResponse msg(String msg) {
        this.msg = msg;
        return this;
    }

    public AjaxResponse detail(String detail) {
        this.detail = detail;
        return this;
    }

    public AjaxResponse jumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
        return this;
    }

    public AjaxResponse data(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private static class BaseResponse extends AjaxResponse {
        public BaseResponse(int code) {
            super(code);
        }
    }
}
