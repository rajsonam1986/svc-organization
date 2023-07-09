package com.erp.organization.exception;

public class CustomParameterizedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String[] params;

    public CustomParameterizedException(String message, Object... params) {
        super(message);
        this.message = message;

        this.params = toStrings(params);
    }

    private String[] toStrings(Object[] params) {
        String[] stringParams = new String[params.length];
        for (int i=0; i<params.length; ++i) {
            stringParams[i] = String.valueOf(params[i]);
        }
        return stringParams;
    }

    public ParameterizedErrorVM getErrorVM() {
        return new ParameterizedErrorVM(message, params);
    }


}
