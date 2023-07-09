package com.erp.organization.exception;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class ParameterizedErrorVM extends ErrorVM {

    private static final long serialVersionUID = 1L;
    private final String[] params;

    public ParameterizedErrorVM() {
        this("deserialize", "deserialize");
    }

    public ParameterizedErrorVM(String message, String... params) {
        super(message);
        this.params = params;
    }

    public String[] getParams() {
        return params;
    }

}
