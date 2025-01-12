package org.springdemo.serviceproviders.basics.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springdemo.serviceproviders.basics.role.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "users")
public class User implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;

//    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean isActive = false ;


//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private BooleanString isActive = BooleanString.FALSE;
//
//    // Getters and Setters
//
//    public enum BooleanString {
//        TRUE, FALSE;
//    }


    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public Integer getId() {
        return id;
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
