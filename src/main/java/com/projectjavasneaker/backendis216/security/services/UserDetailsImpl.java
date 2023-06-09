package com.projectjavasneaker.backendis216.security.services;

import java.util.*;
import java.util.stream.Collectors;

import com.projectjavasneaker.backendis216.models.Cart;
import com.projectjavasneaker.backendis216.models.ERole;
import com.projectjavasneaker.backendis216.models.Role;
import com.projectjavasneaker.backendis216.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;
    @JsonIgnore
    private String password;
    private String role;
    private String address;
    private String phone;
    private String gender;

    private String birth;
    private Cart cart;
    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.role = role;
    }

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, String role
            ,String address, String phone, String gender, String birth
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.role = role;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.birth = birth;
    }
//
public UserDetailsImpl(Long id, String username, String email, String password,
                       Collection<? extends GrantedAuthority> authorities, String role
        ,String address, String phone, String gender, String birth, Cart cart
) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.role = role;
    this.address = address;
    this.phone = phone;
    this.gender = gender;
    this.birth = birth;
    this.cart = cart;
}

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getRole(),
                user.getAddress(),
                user.getPhone(),
                user.getGender(),
                user.getBirth(),
                user.getCart()
                );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public String getRole(){return role;}

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public Cart getCart(){return cart;}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}