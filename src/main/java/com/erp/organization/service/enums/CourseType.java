package com.erp.organization.service.enums;

import java.util.ArrayList;
import java.util.List;

public interface CourseType {
    String YEARLY = "Yearly";
    String SEMESTER = "Semester";

    static List<String> getCourseTypes() {
        List<String> list = new ArrayList<>();
        list.add(YEARLY);
        list.add(SEMESTER);
        return list;
    }

}
