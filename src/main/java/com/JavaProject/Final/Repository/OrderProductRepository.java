package com.JavaProject.Final.Repository;

import com.JavaProject.Final.Pojo.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
}
