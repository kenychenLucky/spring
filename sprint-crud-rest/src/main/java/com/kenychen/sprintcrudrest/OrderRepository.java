package com.kenychen.sprintcrudrest;
import com.kenychen.sprintcrudrest.Model.Order;
import com.kenychen.sprintcrudrest.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
