package com.shun.favoriteindex.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回给前台响应信息
 */
public class FiResponse implements Cloneable {

    private static final FiResponse instance = new FiResponse();

    /**
     * 数据信息
     */
    private Object msg;

    /**
     * 请求成功标识
     */
    private boolean success;

    private FiResponse() {
    }

    private static FiResponse getInstance() {
        FiResponse response = null;
        try {
            response = (FiResponse) instance.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 获取成功的响应
     * @param data
     * @return
     */
    public static FiResponse getSuccessResponse(Object data) {
        FiResponse response = getInstance();
        buildResponse(response, true, data);
        return response;
    }

    /**
     * 获取失败的响应
     * @param data
     * @return
     */
    public static FiResponse getFailureResponse(Object data) {
        FiResponse response = getInstance();
        buildResponse(response, false, data);
        return response;
    }

    /**
     * 获取成功的响应
     * @param data
     * @return
     */
    public static FiResponse getSuccessResponse(Object... data) {
        FiResponse response = getInstance();
        buildResponse(response, true, data);
        return response;
    }

    /**
     * 获取失败的响应
     * @param data
     * @return
     */
    public static FiResponse getFailureResponse(Object... data) {
        FiResponse response = getInstance();
        buildResponse(response, false, data);
        return response;
    }

    private static void buildResponse(FiResponse response, boolean isSuccess, Object data) {
        response.setSuccess(isSuccess);
        response.setMsg(data);
    }

    private static void buildResponse(FiResponse response, boolean isSuccess, Object... args) {
        response.setSuccess(isSuccess);
        if (args != null && args.length > 0) {
            Map<String, Object> retMsg = new HashMap<String, Object>();
            for (int i = 0; i < args.length - 1; i++) {
                if (i % 2 == 0 && args[i] != null && args[i + 1] != null) {
                    retMsg.put(args[i].toString(), args[i + 1]);
                }
            }
            response.setMsg(retMsg);
        } else {
            response.setMsg(new HashMap());
        }
    }

    public Object getMsg() {
        return msg;
    }

    private void setMsg(Object msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    private void setSuccess(boolean success) {
        this.success = success;
    }
}
