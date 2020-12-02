package com.practica.gateway.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;
    private String username;
    private String password;
    private String domain;
    private boolean active = true;
    private boolean accNExpired = true;
    private boolean accNLocked = true;
    private boolean credentialsNExpired = true;
    private boolean enabled = true;
    private Set<GrantedAuthority> roles;

    //Getter y Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoles(Set<GrantedAuthority> roles) {
        this.roles = roles;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    //Constructor
    public User(String username, String password, String domain){
        this.username = username;
        this.password = password;
        this.domain = domain;
    }



    //UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accNExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accNLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNExpired;
    }

    @Override
    public boolean isEnabled() {
        return  enabled;
    }
}
