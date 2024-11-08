package com.og.shoppingZone.productservice.service;

import java.util.List;
import java.util.Optional;

import com.og.shoppingZone.productservice.entity.Product;

public interface ProductService {
	  Product addProduct(Product product);
	    List<Product> getAllProducts();
	    Optional<Product> getProductById(int productId);
	    Optional<Product> getProductByName(String productName);
	    Product updateProduct(int id, Product product);
	    void deleteProductById(int productId);
	    List<Product> getProductByCategory(String category);
	    List<Product> getProductByType(String productType);
}
