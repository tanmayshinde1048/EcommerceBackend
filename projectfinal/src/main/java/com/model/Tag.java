package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "tags")
public class Tag 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@JsonBackReference(value = "product")
	@ManyToOne	
	private Product product;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_tags",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name="tag_id"))
	private List<Product> products;

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(String name, Product product, List<Product> products) {
		super();
		this.name = name;
		this.product = product;
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	 public void addProductToTag(Product product) {
		   if(getProducts()==null) {
		     this.products = new ArrayList<>();
		 }
		   if (!getProducts().contains(product)) {
		     getProducts().add(product);
		  }
		 }
}
