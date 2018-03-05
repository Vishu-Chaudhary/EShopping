package com.sapient.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="products")
public class Product  {
	
	@Id
	@NotNull(message="Product id cannot be empty")
	private Integer pid;
	
	@NotEmpty(message="Product Name cannot be  empty")
	@Size(min=2, max=200, message="Product name must be 3-20 characters")
	@Pattern(regexp="[a-zA-Z0-9 ]+", message="Product Name Must Contains Only Alphabets")
	private String pName;
	
	@NotNull(message="Product price cannot be empty")
	private Double price;
	
	@NotEmpty(message="Product brand name cannot be  empty")
	@Size(min=2, max=200, message="Product brand name must be 3-20 characters")
	@Pattern(regexp="[a-zA-Z ]+", message="Product brand name must contains only alphabets")
	private String brand;
	
	private String prodImg;
	
	@ManyToOne
	@JoinColumn(name="cat_id", referencedColumnName="catId")
	private Category cat;

	public  Product() {
		super();
	}

	public Product(Integer pid, String pName, Double price, String brand, String prodImg, Category cat) {
		super();
		this.pid = pid;
		this.pName = pName;
		this.price = price;
		this.brand = brand;
		this.prodImg = prodImg;
		this.cat = cat;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProdImg() {
		return prodImg;
	}

	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pName=" + pName + ", price=" + price + ", brand=" + brand + ", prodImg="
				+ prodImg + "]";
	}
	
	
	
	
	

}
