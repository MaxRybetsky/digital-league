package org.example.hrsample.util;

import org.example.hrsample.entity.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthUtil {
    public static Set<SimpleGrantedAuthority> getAuths(UserEntity userEntity) {
        return userEntity.getRoleEntity().getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
