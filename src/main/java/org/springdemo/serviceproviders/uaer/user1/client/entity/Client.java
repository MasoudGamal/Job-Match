package org.springdemo.serviceproviders.uaer.user1.client.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdemo.serviceproviders.order.entity.Order;
import org.springdemo.serviceproviders.uaer.user1.entity.User;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Client extends User {

    private String address;

    @OneToMany
    private List<Order> requests;
}
