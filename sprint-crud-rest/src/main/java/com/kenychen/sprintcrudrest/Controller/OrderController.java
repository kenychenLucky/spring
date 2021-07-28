package com.kenychen.sprintcrudrest.Controller;
import java.util.List;
import java.util.stream.Collectors;

import com.kenychen.sprintcrudrest.Model.*;
import com.kenychen.sprintcrudrest.OrderRepository;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;

    OrderController(OrderRepository orderRepository, OrderModelAssembler assembler) {

        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @GetMapping("/orders")
    CollectionModel<EntityModel<Order>> all() {

        List<EntityModel<Order>> orders = orderRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/orders/{id}")
    EntityModel<Order> one(@PathVariable Long id) {

        Order order = orderRepository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order);
    }

    @PostMapping("/orders")
    ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order) {

        order.setStatus(Status.IN_PROGRESS);
        Order newOrder = orderRepository.save(order);

        return ResponseEntity //
                .created(linkTo(methodOn(OrderController.class).one(newOrder.getId())).toUri()) //
                .body(assembler.toModel(newOrder));
    }

//    @DeleteMapping("/orders/{id}/cancel")
//    ResponseEntity<?> cancel(@PathVariable Long id) {
//
//        Order order = orderRepository.findById(id) //
//                .orElseThrow(() -> new OrderNotFoundException(id));
//
//        if (order.getStatus() == Status.IN_PROGRESS) {
//            order.setStatus(Status.CANCELLED);
//            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
//        }
//
//        ResponseEntity<T> method_not_allowed = ResponseEntity //
//                .status(HttpStatus.METHOD_NOT_ALLOWED) //
//                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
//                .body(
//                        Problem.create()
//                                .withTitle("Method not allowed") //
//                                .withDetail("You can't cancel an order that is in the " + order.getStatus() + " status"));
//        return method_not_allowed;
//    }
//
//    @PutMapping("/orders/{id}/complete")
//    ResponseEntity<?> complete(@PathVariable Long id) {
//
//        Order order = orderRepository.findById(id) //
//                .orElseThrow(() -> new OrderNotFoundException(id));
//
//        if (order.getStatus() == Status.IN_PROGRESS) {
//            order.setStatus(Status.COMPLETED);
//            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
//        }
//
//        ResponseEntity<T> method_not_allowed = ResponseEntity //
//                .status(HttpStatus.METHOD_NOT_ALLOWED) //
//                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
//                .body(
//                        Problem.create() //
//                        .withTitle("Method not allowed") //
//                        .withDetail("You can't complete an order that is in the " + order.getStatus() + " status"));
//        return method_not_allowed;
//    }

}
