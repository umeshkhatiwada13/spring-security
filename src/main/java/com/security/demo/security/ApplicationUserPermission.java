package com.security.demo.security;

import lombok.Getter;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 08/07/2022 - 5:55 PM
 */
@Getter
public enum ApplicationUserPermission {
    STUDENT("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
