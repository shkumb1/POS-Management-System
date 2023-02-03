package com.JavaProject.Final.Service;

import com.JavaProject.Final.Exception.ConflictException;
import com.JavaProject.Final.Exception.OutOfStockException;
import com.JavaProject.Final.Exception.ProductNotFoundException;
import com.JavaProject.Final.Pojo.DTO.ProductDTO;
import com.JavaProject.Final.Pojo.Order;
import com.JavaProject.Final.Pojo.OrderProduct;
import com.JavaProject.Final.Pojo.Product;
import com.JavaProject.Final.Pojo.ProductType;
import com.JavaProject.Final.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testSaveProductFromDTO() throws ConflictException {
        // Given
        ProductDTO productDTO = new ProductDTO("Test Product1", ProductType.DRINK, 10.0, 100);

        // When
        Product savedProduct = productService.saveProductFromDTO(productDTO);

        // Then
        assertNotNull(savedProduct);
        assertEquals(productDTO.getName(), savedProduct.getName());
        assertEquals(productDTO.getProductType(), savedProduct.getProductType());
        assertEquals(productDTO.getPrice(), savedProduct.getPrice(), 0.0);
        assertEquals(productDTO.getStock(), savedProduct.getStock());
        assertTrue(productRepository.existsProductByName(savedProduct.getName()));

    }

    @Test
    public void checkStockTest() throws OutOfStockException, ConflictException {
        Order order = new Order();
        ProductDTO productDTO = new ProductDTO( "Test Product2", ProductType.DRINK, 105, 10);
        Product product = productService.saveProductFromDTO(productDTO);
        OrderProduct orderProduct = new OrderProduct(102, 5, product, order);
        List<OrderProduct> orderProducts = new ArrayList<>();
        orderProducts.add(orderProduct);

        productRepository.save(product);

        productService.checkStock(orderProducts);
    }
    @Test
    public void updatePriceTest() throws ProductNotFoundException, ConflictException {
    ProductDTO productDTO = new ProductDTO( "Test Product3", ProductType.DRINK, 105, 10);
    Product product = productService.saveProductFromDTO(productDTO);

    productRepository.save(product);
    int newPriceAmount = 1000;
    productService.updatePrice(product.getProductID(), newPriceAmount);
    Optional<Product> updatedProduct = productRepository.findById(product.getProductID());
    assertTrue(updatedProduct.isPresent());
    assertEquals(newPriceAmount, updatedProduct.get().getPrice());



}


    @Test
    public void updateProductStockTest() throws ProductNotFoundException, ConflictException {
        // Given: A product exists in the database
        ProductDTO productDTO = new ProductDTO( "Test Product4", ProductType.DRINK, 105, 10);
        Product product = productService.saveProductFromDTO(productDTO);
        productRepository.save(product);


        // When: updateProductStock method is called with the product ID and amount
        int newStockAmount = 15;
        productService.updateProductStock(product.getProductID(), newStockAmount);

        // Then: The stock of the product is updated
        Optional<Product> updatedProduct = productRepository.findById(product.getProductID());
        assertTrue(updatedProduct.isPresent());
        assertEquals(newStockAmount+product.getStock(), updatedProduct.get().getStock());
    }


}
