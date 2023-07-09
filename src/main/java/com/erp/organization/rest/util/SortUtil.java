package com.erp.organization.rest.util;

import com.erp.organization.exception.CustomParameterizedException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SortUtil {
    static public void validateSortProperties(Sort sort, String... allowedSortProperties) {
        validateSortProperties(sort, new HashSet<>(Arrays.asList(allowedSortProperties)));
    }

    static public void validateSortProperties(Sort sort, Set<String> allowedSortProperties) {
        for (Order order : sort) {
            if (!allowedSortProperties.contains(order.getProperty())) {
                throw new CustomParameterizedException("Invalid sort property", order.getProperty());
            }
        }
    }

    static public void validateSortProperties(Sort sort, Class<?> beanClass, String... additionalAllowedSortProperties) {
        validateSortProperties(sort, beanClass, new HashSet<>(Arrays.asList(additionalAllowedSortProperties)));
    }

    static public void validateSortProperties(Sort sort, Class<?> beanClass, Set<String> additionalAllowedSortProperties) {
        for (Order order : sort) {
            String property = order.getProperty();

            if (!additionalAllowedSortProperties.contains(property)) {
                PropertyDescriptor desc = BeanUtils.getPropertyDescriptor(beanClass, property);
                if (desc == null) {
                    throw new CustomParameterizedException("Invalid sort property", property);
                }
            }
        }
    }
}
