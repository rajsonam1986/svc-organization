package com.erp.organization.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AuthoritiesConstants {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String SUPPORT_ADMIN = "ROLE_SUPPORT_ADMIN";
    public static final String USER = "ROLE_USER";

    public static final String STUDY_COORDINATOR = "ROLE_STUDY_COORDINATOR";
    public static final String PHYSICIAN = "ROLE_PHYSICIAN";
    public static final String MPA = "ROLE_MPA";
    public static final String PATIENT = "ROLE_PATIENT";
    public static final String PATIENT_LIMITED = "ROLE_PATIENT_LIMITED";
    public static final String LIMITED_ONBOARDING = "ROLE_ONBOARDING_LIMITED";
    public static final String LIMITED_MESSAGES_COUNT = "ROLE_LIMITED_MESSAGES_COUNT";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String OTHER_SERVICE = "ROLE_OTHER_SERVICE";

    public static final String EXTERNAL_SERVICE = "ROLE_EXTERNAL_SERVICE";

    private AuthoritiesConstants() {
    }

    public static Set<String> patientAuthorities() {
        return new HashSet<>(Arrays.asList(USER, PATIENT));
    }

    public static Set<String> physicianAuthorities() {
        return new HashSet<>(Arrays.asList(USER, PHYSICIAN));
    }

}
