package com.lph.common.response.across;

import java.io.Serializable;


/**
 * 服务之间调用响应实体
 *
 * @param <T>
 */
public class AcrossServiceResponse<T> implements Serializable {

    private static final long serialVersionUID = -2932524468952580504L;

    /**
     * 接口状态成功或失败
     * 0失败
     * 1成功
     */
    private Integer status;

    /**
     * 返回的数据
     */
    private T data;



    /**
     * 是否成功响应。
     *
     * @return
     */
    private boolean isSuccess() {
        return this.status == 1;
    }

    /**
     * 响应放置数据
     *
     * @param data
     */
    public static <T> AcrossServiceResponse success(T data) {
        AcrossServiceResponse response = new AcrossServiceResponse();
        response.setStatus(1);
        response.setData(data);
        return response;
    }

    /**
     * 响应失败
     */
    public static AcrossServiceResponse error() {
        AcrossServiceResponse response = new AcrossServiceResponse();
        response.setStatus(0);
        return response;
    }


    /**
     * 获取响应数据。
     * 响应失败返回null
     *
     * @return
     */
    public T obtainResponse() {
        if (isSuccess()) {
            return this.data;
        }
        return null;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
