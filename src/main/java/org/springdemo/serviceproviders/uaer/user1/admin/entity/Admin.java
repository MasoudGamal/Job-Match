package org.springdemo.serviceproviders.uaer.user1.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springdemo.serviceproviders.uaer.user1.entity.User;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "admins")
public class Admin extends User {

}
