package org.example.hrsample.security;

import lombok.Data;
import org.example.hrsample.entity.UserEntity;
import org.example.hrsample.util.AuthUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUserEntity(UserEntity user) {
        return new User(
                user.getLogin(),
                user.getPassword(),
                user.getStatus().equals("ACTIVE"),
                user.getStatus().equals("ACTIVE"),
                user.getStatus().equals("ACTIVE"),
                user.getStatus().equals("ACTIVE"),
                AuthUtil.getAuths(user)
        );
    }
}
