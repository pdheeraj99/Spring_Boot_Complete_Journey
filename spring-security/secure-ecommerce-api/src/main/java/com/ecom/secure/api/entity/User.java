package com.ecom.secure.api.entity;

import com.ecom.secure.api.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data // Lombok: getters, setters, toString, etc. kosam
@Builder // Lombok: Builder pattern kosam
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails { // <-- Chala important idi

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true) // Email must be unique
    private String email;

    private String password;

    // Manam enums create chesaka deenini marchudam
    // private String role;
    @Enumerated(EnumType.STRING) // <-- Add this annotation
    private Role role; // <-- Change the type to Role

    // ----------- UserDetails Methods -----------
    // Ee methods Spring Security ki kavali

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // User1 yokka role ni Spring Security ki istunnam
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        // Manam username ga email ni vadutunnam
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Ee kindavi ippatiki 'true' ani petteddam
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