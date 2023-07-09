package com.erp.organization.exception;

import lombok.Getter;
import lombok.ToString;

@ToString
public class RethrownRemoteCallException extends RuntimeException {

    @Getter
    private ErrorVM errorVM;

    public RethrownRemoteCallException(ErrorVM errorVM) {
        this.errorVM = errorVM;
    }
}
