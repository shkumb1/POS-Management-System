package com.JavaProject.Final.Repository;

import com.JavaProject.Final.Pojo.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsProductByName(String name);


    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.stock = p.stock + :amount WHERE p.productID = :productID")
    void updateStock(@Param("productID") Integer productID, @Param("amount") int amount);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.price = :price WHERE p.productID = :productID")
    int updatePrice(@Param("productID") Integer productID, @Param("price") double price);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Optional<Product> findByName(@Param("name") String name);


}
