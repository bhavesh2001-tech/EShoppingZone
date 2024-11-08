//package com.og.shoppingZone.productservice.service;
//
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.og.shoppingZone.productservice.dto.ProductDTO;
//import com.og.shoppingZone.productservice.entity.Product;
//import com.og.shoppingZone.productservice.repository.ProductRepository;
//
//
//
//@Service
//public class ProductServiceImpl implements ProductService{
//
//	
//	@Autowired
//    private ProductRepository productRepository;
//
//    @Override
//    public Product addProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    @Override
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public Optional<Product> getProductById(int productId) {
//        return productRepository.findById(productId);
//    }
//
//    @Override
//    public Optional<Product> getProductByName(String productName) {
//        return productRepository.findByProductName(productName);
//    }
//
//    @Override
//    public Product updateProduct(int id,Product product) {
//    	Optional<Product> existingProductOptional = productRepository.findById(id);
//    	 if (existingProductOptional.isPresent()) {
//             Product existingProduct = existingProductOptional.get();
//             existingProduct.setProductType(product.getProductType());
//             existingProduct.setProductName(product.getProductName());
//             existingProduct.setCategory(product.getCategory());
//             existingProduct.setRating(product.getRating());
//             existingProduct.setReview(product.getReview());
//             existingProduct.setImage(product.getImage());
//             existingProduct.setPrice(product.getPrice());
//             existingProduct.setDescription(product.getDescription());
//             existingProduct.setSpecification(product.getSpecification());
//             
//             return productRepository.save(existingProduct);
//         } else {
//             // Handle case where product with ID doesn't exist
//             throw new RuntimeException("Product with ID " + id + " not found.");
//         }
//     }
//    
//
//    @Override
//    public void deleteProductById(int productId) {
//        productRepository.deleteById(productId);
//    }
//
//    @Override
//    public List<Product> getProductByCategory(String category) {
//        return productRepository.findByCategory(category);
//    }
//
//    @Override
//    public List<Product> getProductByType(String productType) {
//        return productRepository.findByProductType(productType);
//    }
//    
//    //Covert DTO
//    public ProductDTO convertToDTO(Product product) {
//        ProductDTO dto = new ProductDTO();
//        dto.setProductId(product.getProductId());
//        dto.setProductType(product.getProductType());
//        dto.setProductName(product.getProductName());
//        dto.setCategory(product.getCategory());
//        dto.setRating(product.getRating());
//        dto.setReview(product.getReview());
//        dto.setImage(product.getImage());
//        dto.setPrice(product.getPrice());
//        dto.setDescription(product.getDescription());
//        dto.setSpecification(product.getSpecification());
//        return dto;
//    }
//    
//    //Convert Entity
//    public Product convertToEntity(ProductDTO dto) {
//        Product product = new Product();
//        product.setProductId(dto.getProductId());
//        product.setProductType(dto.getProductType());
//        product.setProductName(dto.getProductName());
//        product.setCategory(dto.getCategory());
//        product.setRating(dto.getRating());
//        product.setReview(dto.getReview());
//        product.setImage(dto.getImage());
//        product.setPrice(dto.getPrice());
//        product.setDescription(dto.getDescription());
//        product.setSpecification(dto.getSpecification());
//        return product;
//    }
//}

package com.og.shoppingZone.productservice.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.og.shoppingZone.productservice.dto.ProductDTO;
import com.og.shoppingZone.productservice.entity.Product;
import com.og.shoppingZone.productservice.exception.ProductNotFoundException;
import com.og.shoppingZone.productservice.exception.InvalidProductException;
import com.og.shoppingZone.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        if (product == null) {
            throw new InvalidProductException("Product cannot be null");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products;
    }

    @Override
    public Optional<Product> getProductById(int productId) {
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
    	Optional<Product> byProductId = productRepository.findById(productId);
    	if (byProductId.isPresent()) {
			return byProductId;
		}
    	else {
			throw new ProductNotFoundException("Product with name " + productId + " not found");
		}
    }

    @Override
    public Optional<Product> getProductByName(String productName) {
//        return productRepository.findByProductName(productName)
//                .orElseThrow(() -> new ProductNotFoundException("Product with name " + productName + " not found"));
    	
    	Optional<Product> byProductName = productRepository.findByProductName(productName);
    	
    	if (byProductName.isPresent()) {
			return byProductName;
		}
    	else {
			throw new ProductNotFoundException("Product with name " + productName + " not found");
		}
    }

    @Override
    public Product updateProduct(int id, Product product) {
        if (product == null) {
            throw new InvalidProductException("Product details cannot be null");
        }
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        existingProduct.setProductType(product.getProductType());
        existingProduct.setProductName(product.getProductName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setRating(product.getRating());
        existingProduct.setReview(product.getReview());
        existingProduct.setImage(product.getImage());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setSpecification(product.getSpecification());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProductById(int productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in category " + category);
        }
        return products;
    }

    @Override
    public List<Product> getProductByType(String productType) {
        List<Product> products = productRepository.findByProductType(productType);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found with type " + productType);
        }
        return products;
    }

    // Convert to DTO
    public ProductDTO convertToDTO(Product product) {
        if (product == null) {
            throw new InvalidProductException("Product cannot be null");
        }
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductType(product.getProductType());
        dto.setProductName(product.getProductName());
        dto.setCategory(product.getCategory());
        dto.setRating(product.getRating());
        dto.setReview(product.getReview());
        dto.setImage(product.getImage());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setSpecification(product.getSpecification());
        return dto;
    }

    // Convert to Entity
    public Product convertToEntity(ProductDTO dto) {
        if (dto == null) {
            throw new InvalidProductException("ProductDTO cannot be null");
        }
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setProductType(dto.getProductType());
        product.setProductName(dto.getProductName());
        product.setCategory(dto.getCategory());
        product.setRating(dto.getRating());
        product.setReview(dto.getReview());
        product.setImage(dto.getImage());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setSpecification(dto.getSpecification());
        return product;
    }
}

