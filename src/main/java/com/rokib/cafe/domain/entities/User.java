package com.rokib.cafe.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@NamedQuery(name = "User.gellAllUser",query = "select new com.rokib.cafe.domain.dto.user.UserDTO(u.id,u.firstname,u.lastname,u.contactNumber,u.email,u.status) from User u where u.role='user'")

@NamedQuery(name = "User.gellAllAdmin",query = "select u.email from User u where u.role='admin'")

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "contactNumber")
    private String contactNumber;


    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "role")
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
