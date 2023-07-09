package com.erp.organization.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "svc-user", url = "http://localhost:8086")
public interface UserResourceFeign {
    @GetMapping(value = "/api/user/{uid}")
    String getDepartmentById(@PathVariable("uid") Long departmentId);
}
