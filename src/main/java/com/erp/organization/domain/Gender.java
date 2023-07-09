package com.erp.organization.domain;

public enum Gender {
    FEMALE('f'), MALE('m'), DIVERS('x');

    private final String key;

    private Gender(final char key) {
        this.key = "" + key;
    }

    public String getKey() {
        return key;
    }
}
