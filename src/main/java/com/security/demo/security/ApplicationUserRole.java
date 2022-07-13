package com.security.demo.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author umeshkhatiwada13@infodev
 * @project spring-security
 * @created 08/07/2022 - 5:54 PM
 */

@Getter
public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE,
            ApplicationUserPermission.STUDENT_WRITE,
            ApplicationUserPermission.STUDENT_READ)),

    ADMIN_TRAINEE(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.STUDENT_READ)),
    STUDENT(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    // This method sets permission to each users and is used in ApplicationSecurityConfig
    // ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities() will add course:read, student:read and ROLE_ADMIN_TRAINEE
    // to adminTrainee user
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
