package com.JavaProject.Final.Service;

import com.JavaProject.Final.Exception.OrderNotFoundException;

import com.JavaProject.Final.Pojo.*;
import com.JavaProject.Final.Pojo.DTO.OrderDTO;
import com.JavaProject.Final.Pojo.DTO.OrderProductDTO;
import com.JavaProject.Final.Repository.OrderProductRepository;
import com.JavaProject.Final.Repository.OrderRepository;
import com.JavaProject.Final.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    TableService tableService;
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    WaiterService waiterService;

    public void saveOrder(Order order){
        orderRepository.save(order);
    }
    @Transactional
    public Order saveOrderFromData(OrderDTO orderDTO){
        try{
            List<OrderProduct> orderProducts = new ArrayList<>();
            List<OrderProductDTO> myProducts = orderDTO.getOrderProducts();

            Order order = new Order();
            double orderPrice = 0;

            OrderProduct orderProduct;
            for(OrderProductDTO orderProductDTO : myProducts){
                orderProduct = new OrderProduct();

                Product product = productService.getProductById(orderProductDTO.getProduct().getProductID());

                orderProduct.setProduct(product);
                orderProduct.setQuantity(orderProductDTO.getQuantity());
                orderProduct.setOrder(order);
                orderPrice = orderPrice + (product.getPrice() * orderProductDTO.getQuantity());
                orderProducts.add(orderProduct);
            }
            productService.checkStock(orderProducts);

            order.setOrderPrice(orderPrice);
            order.setTable(orderDTO.getTable());
            order.setWaiter(orderDTO.getWaiter());
            order.setOrderDate(LocalDate.now());
            order.setOrderProducts(orderProducts);

            Waiter waiter = waiterService.getWaiterById(orderDTO.getWaiter().getWaiterID());
            order.setWaiter(waiter);
            Tables table = tableService.getTableById(orderDTO.getTable().getTableID());
            order.setTable(table);
            saveOrder(order);
            return order;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById (Integer id) throws OrderNotFoundException{
        if(orderExists(id)){
            return orderRepository.findById(id).get();

        }else
            throw new OrderNotFoundException(("Order with id: "+id+" doesnt exist"));
    }

    public void deleteOrder(Integer id) throws OrderNotFoundException {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
        else throw new OrderNotFoundException("An order with this id: " + id + " doesn't exist");
    }

    public Double sumAmountByOrderDate(Date date) {

        return orderRepository.sumAmountByOrderDate(date);
    }


    public boolean orderExists(Integer id) {
        return orderRepository.existsById(id);
    }



}
