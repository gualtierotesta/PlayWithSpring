package it.gualtierotesta.playwithspring.restapi.rest;

import it.gualtierotesta.playwithspring.restapi.service.ServiceResult;

import java.util.Objects;

public class RestResult <T> {

    private final T payload;
    private final int errorCode;
    private final String errMessage;

    private RestResult(T payload, int errorCode, String errMessage) {
        this.payload = payload;
        this.errorCode = errorCode;
        this.errMessage = errMessage;
    }

    public static <T> RestResult<T> success(T pPayLoad) {
        return new RestResult<>(Objects.requireNonNull(pPayLoad),0, "");
    }

    public static <T> RestResult<T> failure(int pErrorCode, String pErrMessage) {
        return new RestResult<>(null, pErrorCode, pErrMessage);
    }

    public T getPayload() {
        return this.payload;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrMessage() {
        return this.errMessage;
    }
}
