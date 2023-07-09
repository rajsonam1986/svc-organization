package com.erp.organization.rest;

import com.erp.organization.service.UserResourceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/raj")
public class UserApiCont {

    @Autowired
    private UserResourceFeign userResourceFeign;
    @GetMapping("/user/{uid}")
    public ResponseEntity<String> getAbc(@PathVariable Long uid) {
        userResourceFeign.getDepartmentById(uid);
        return new ResponseEntity( userResourceFeign.getDepartmentById(uid), HttpStatus.OK);
    }
}
