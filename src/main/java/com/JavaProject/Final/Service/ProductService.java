package com.JavaProject.Final.Service;

import com.JavaProject.Final.Exception.ConflictException;
import com.JavaProject.Final.Exception.OutOfStockException;
import com.JavaProject.Final.Exception.ProductNotFoundException;
import com.JavaProject.Final.Pojo.DTO.ProductDTO;
import com.JavaProject.Final.Pojo.OrderProduct;
import com.JavaProject.Final.Pojo.Product;
import com.JavaProject.Final.Pojo.ProductType;
import com.JavaProject.Final.Repository.OrderProductRepository;
import com.JavaProject.Final.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderProductRepository orderProductRepository;

    public ProductService(ProductRepository productRepository) {

    }

    public Product saveProductFromDTO (ProductDTO productDTO) throws ConflictException {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setProductType(ProductType.valueOf(productDTO.getProductType().toString().toUpperCase()));
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        saveProduct(product);
        return product;

    }

    public void saveProduct(Product product) throws ConflictException{
        if(productExists(product.getName())){
            throw new ConflictException("A product with this name: " + product.getName() + "already exists!");
        }else
            productRepository.save(product);
    }


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) throws ProductNotFoundException{
        if(productExists(id)) {
            return productRepository.findById(id).get();
        }
        else throw new ProductNotFoundException("A product with this id: " + id + " doesn't exist");
    }

    public Product getProductByName(String name) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findByName(name);
        if (productExists(name)) {
            return product.get();
        } else {
            throw new ProductNotFoundException("A product with this name: " + name + " doesn't exist");
        }
    }


    public void checkStock (List<OrderProduct> orderProducts)throws OutOfStockException {
        for(OrderProduct orderProduct : orderProducts){
            if(orderProduct.getQuantity()>orderProduct.getProduct().getStock()){
                throw new OutOfStockException(orderProduct.getProduct().getName() + " is out of stock");
            }else
                productRepository.updateStock(orderProduct.getProduct().getProductID(), 1 * orderProduct.getQuantity());
        }
    }
    @Transactional
    public void updateProductStock(Integer productID, int amount) throws ProductNotFoundException {
        if(productExists(productID)) {
            productRepository.updateStock(productID, amount);
        }
        else {
            throw new ProductNotFoundException("A product with this id: " + productID + " doesn't exist");
        }
    }
    @Transactional
    public void updatePrice(Integer productID, double price) throws ProductNotFoundException {
        if(productExists(productID)) {
            productRepository.updatePrice(productID, price);
        }
        else throw new ProductNotFoundException("No item found with given ID:"+ productID);
    }

    @Transactional
    public void deleteProduct(Integer id) throws ProductNotFoundException {
        if(productExists(id)) {

            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("No product found with given ID: "+ id);
        }
    }


    public boolean productExists(Integer id) {
        return productRepository.existsById(id);
    }
    public boolean productExists(String name) {
        return productRepository.existsProductByName(name);
    }

}
