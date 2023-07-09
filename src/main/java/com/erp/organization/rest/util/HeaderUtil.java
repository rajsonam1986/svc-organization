package com.erp.organization.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String HEADER_NAME_PREFIX = "X-docdok-";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_NAME_PREFIX + "alert", message);
        headers.add(HEADER_NAME_PREFIX + "params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert(entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_NAME_PREFIX + "error", "error." + errorKey);
        headers.add(HEADER_NAME_PREFIX + "params", entityName);
        return headers;
    }

    public static HttpHeaders createFailureAlert(String errorKey, String defaultMessage) {
        log.info("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_NAME_PREFIX + "error", "error." + errorKey);
        return headers;
    }
}
