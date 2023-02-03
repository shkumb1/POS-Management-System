package com.JavaProject.Final.Repository;

import com.JavaProject.Final.Pojo.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findAllByTable_TableID(Integer tableID);
    @Modifying
    @Transactional
    Order save(Order order);


        @Query("SELECT SUM(o.orderPrice) FROM Order o WHERE DATE(o.orderDate) = DATE(:date)")
        Double sumAmountByOrderDate(@Param("date") Date date);


}
