package com.master_diploma.cafe.config;

import com.master_diploma.cafe.models.UserTable;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    @Getter
    private final Long id;
    @Getter
    private final String role;
    private final UserTable userTable;
    public MyUserDetails(UserTable user){
        this.userTable = user;
        this.id = userTable.getId();
        role = userTable.getRole().getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return userTable.getPassword();
    }

    @Override
    public String getUsername() {
        return userTable.getFirstName();
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
