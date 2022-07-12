package com.security.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 08/07/2022 - 5:54 PM
 */
public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE,
            ApplicationUserPermission.STUDENT_WRITE,
            ApplicationUserPermission.STUDENT_READ)),
    STUDENT(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }
}
