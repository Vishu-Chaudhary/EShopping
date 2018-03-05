package com.sapient.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Category {
	
	@Id
	@NotNull(message="Category Id cannot be empty")
	private int catId;
	
	@NotEmpty(message="category name cannot be empty")
	@Size(min=2, max=20, message="Category name must be 3-20 characters")
	@Pattern(regexp="[a-zA-Z ]+", message="Category Name Must Contains Only Alphabets")
	private String catName;
	
	@OneToMany(mappedBy="cat")
	private Set<Product> prods;
	
	private String catImg;
	
	public String getCatImg() {
		return catImg;
	}

	public void setCatImg(String catImg) {
		this.catImg = catImg;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int catId, String catName, Set<Product> prods) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.prods = prods;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Set<Product> getProds() {
		return prods;
	}

	public void setProds(Set<Product> prods) {
		this.prods = prods;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catName=" + catName + "]";
	}

	

}
