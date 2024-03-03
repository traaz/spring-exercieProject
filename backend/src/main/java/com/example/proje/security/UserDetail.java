package com.example.proje.security;

import com.example.proje.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetail implements org.springframework.security.core.userdetails.UserDetails {
    private String name;
    private String password;

    public UserDetail(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
