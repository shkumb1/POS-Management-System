package com.JavaProject.Final.Controller;

import com.JavaProject.Final.Exception.OrderNotFoundException;
import com.JavaProject.Final.Pojo.DTO.OrderDTO;
import com.JavaProject.Final.Pojo.Order;
import com.JavaProject.Final.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.saveOrderFromData(orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok().body("Order deleted successfully!");
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) throws OrderNotFoundException {
        return orderService.getOrderById(id);
    }

    @GetMapping("/circulation")
    public ResponseEntity<Map<String, Object>> TotalCirculationByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Double sumAmount = orderService.sumAmountByOrderDate(date);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "The total price is: ");
        response.put("sumAmount", sumAmount);
        return ResponseEntity.ok(response);
    }


}

