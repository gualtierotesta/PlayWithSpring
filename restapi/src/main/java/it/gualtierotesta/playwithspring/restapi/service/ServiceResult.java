package it.gualtierotesta.playwithspring.restapi.service;

import lombok.ToString;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;

@ToString
public class ServiceResult<T> {

    private final T data;
    private final boolean success;

    public static <T> ServiceResult<T> success(T pData) {
        return new ServiceResult<>(Objects.requireNonNull(pData), true);
    }

    public static <T> ServiceResult<T> failure() {
        return new ServiceResult<>(null, false);
    }

    private ServiceResult(final T pData, boolean pSuccess) {
        data = pData;
        success = pSuccess;
    }

    public T data() {
        if (success) {
            return data;
        }
        throw  new NoSuchElementException("No data in failure Service result");
    }

    public boolean isSuccess() {
        return success;
    }

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (success) {
            action.accept(data);
        } else {
            emptyAction.run();
        }
    }

}
