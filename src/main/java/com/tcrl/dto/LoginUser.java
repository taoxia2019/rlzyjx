package com.tcrl.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcrl.entity.Permission;
import com.tcrl.entity.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName LoginUser
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/10/9 16:31
 * @Version 1.0
 */
@Data
public class LoginUser extends Users implements UserDetails {
    private List<Permission> permissions;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.parallelStream().filter(p->!StringUtils.isEmpty(p.getPermission()))
                .map(p->new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getStatus()!=Status.LOCKED;
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
