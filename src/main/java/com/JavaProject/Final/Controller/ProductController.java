package com.JavaProject.Final.Controller;

import com.JavaProject.Final.Exception.ConflictException;
import com.JavaProject.Final.Exception.ProductNotFoundException;
import com.JavaProject.Final.Pojo.DTO.ProductDTO;
import com.JavaProject.Final.Pojo.Product;
import com.JavaProject.Final.Repository.ProductRepository;
import com.JavaProject.Final.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    private final ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) throws ConflictException {
            Product product = productService.saveProductFromDTO(productDTO);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/stock/{productID}")
    public void updateProductStock(@PathVariable Integer productID, @RequestParam int amount) throws ProductNotFoundException {
        productRepository.updateStock(productID, amount);
    }

    @PutMapping("/price/{productID}")
    public void updateProductPrice(@PathVariable Integer productID, @RequestParam int price) throws ProductNotFoundException {
        productRepository.updatePrice(productID, price);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) throws ProductNotFoundException{
        return productService.getProductById(id);
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) throws ProductNotFoundException {
        return productService.getProductByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) throws ProductNotFoundException {
        productService.deleteProduct(id);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }






}

