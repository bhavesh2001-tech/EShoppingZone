package com.og.shoppingZone.productservice.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.og.shoppingZone.productservice.dto.ProductDTO;
import com.og.shoppingZone.productservice.entity.Product;
import com.og.shoppingZone.productservice.service.ProductService;
import com.og.shoppingZone.productservice.service.ProductServiceImpl;

import io.swagger.v3.oas.annotations.Operation;



	
@RestController
@RequestMapping("/products")
public class ProductController {
	  @Autowired
	    private ProductServiceImpl productService;

	    
	    // Add a new product.
	    @Operation(summary = "Add New Product")
	    @PostMapping("/add")
	    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
	        Product product = productService.convertToEntity(productDTO);
	        Product savedProduct = productService.addProduct(product);
	        ProductDTO responseDTO = productService.convertToDTO(savedProduct);
	        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	    }

	    
	     //Retrieve all products.
	    @Operation(summary = "Dispaly All Products")
	    @GetMapping("/all")
	    public ResponseEntity<List<ProductDTO>> getAllProducts() {
	        List<Product> products = productService.getAllProducts();
	        List<ProductDTO> productDTOs = products.stream()
	                .map(productService::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(productDTOs);
	    }

	    
	     //Retrieve a product by its ID.
	    @Operation(summary = "Dispaly Product By ID")  
	    @GetMapping("/{id}")
	    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
	        Optional<Product> productOpt = productService.getProductById(id);
	        if (productOpt.isPresent()) {
	            ProductDTO productDTO = productService.convertToDTO(productOpt.get());
	            return ResponseEntity.ok(productDTO);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }

	    
	     //Retrieve a product by its name.
	    @Operation(summary = "Dispaly Product By Name") 
	    @GetMapping("/name/{name}")
	    public ResponseEntity<ProductDTO> getProductByName(@PathVariable String name) {
	        Optional<Product> productOpt = productService.getProductByName(name);
	        if (productOpt.isPresent()) {
	            ProductDTO productDTO = productService.convertToDTO(productOpt.get());
	            return ResponseEntity.ok(productDTO);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	    

	    
	     // Retrieve products by their category.
	    @Operation(summary = "Dispaly Products By Category") 
	    @GetMapping("/category/{category}")
	    public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable String category) {
	        List<Product> products = productService.getProductByCategory(category);
	        List<ProductDTO> productDTOs = products.stream()
	                .map(productService::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(productDTOs);
	    }

	    
	     //Retrieve products by their type.
	    @Operation(summary = "Dispaly Product By Type")
	    @GetMapping("/type/{type}")
	    public ResponseEntity<List<ProductDTO>> getProductByType(@PathVariable String type) {
	        List<Product> products = productService.getProductByType(type);
	        List<ProductDTO> productDTOs = products.stream()
	                .map(productService::convertToDTO)
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(productDTOs);
	    }

	    
	     // Update an existing product by its ID.
	    @Operation(summary = "Upadte Product Based on ID") 
	    @PutMapping("/{id}")
	    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
	        Product product = productService.convertToEntity(productDTO);
	        Product updatedProduct = productService.updateProduct(id, product);
	        ProductDTO responseDTO = productService.convertToDTO(updatedProduct);
	        return ResponseEntity.ok(responseDTO);
	    }

	    
	     // Delete a product by its ID.
	    @Operation(summary = "Delete Product") 
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
	        productService.deleteProductById(id);
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
}
