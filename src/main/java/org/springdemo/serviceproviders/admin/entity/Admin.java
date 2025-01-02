package org.springdemo.serviceproviders.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springdemo.serviceproviders.user.User;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "admins")
public class Admin extends User {

}
