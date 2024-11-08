package com.og.shoppingZone.productservice.entity;



import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    
    private String productType;
    private String productName;
    private String category;
    @ElementCollection
    private Map<Integer, Double> rating; // Example: <userId, rating>
    @ElementCollection
    private Map<Integer, String> review; // Example: <userId, review>
    @ElementCollection
    private List<String> image; // List of image URLs
    private double price;
    private String description;
    @ElementCollection
    private Map<String, String> specification; // Key-value pair for specifications

    // Constructors, Getters, Setters, hashCode, equals, toString...
    
    
    
    // Example Constructor
    public Product() {}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<Integer, Double> getRating() {
		return rating;
	}

	public void setRating(Map<Integer, Double> rating) {
		this.rating = rating;
	}

	public Map<Integer, String> getReview() {
		return review;
	}

	public void setReview(Map<Integer, String> review) {
		this.review = review;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getSpecification() {
		return specification;
	}

	public void setSpecification(Map<String, String> specification) {
		this.specification = specification;
	}

    // Getters and setters for all fields
}