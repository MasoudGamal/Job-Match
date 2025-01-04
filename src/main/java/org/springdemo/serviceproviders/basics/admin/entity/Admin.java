package org.springdemo.serviceproviders.basics.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springdemo.serviceproviders.basics.user.entity.User;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "admins")
public class Admin extends User {

}
