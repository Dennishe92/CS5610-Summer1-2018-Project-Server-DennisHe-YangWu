package com.example.myproject3.model;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer extends WebUser{
//	@OneToMany(mappedBy="owner")
//	@JsonIgnore
//	private List<Payment> ownedPayment;
//	
//	public void ownedPayment(Payment payment) {
//		this.ownedPayment.add(payment);
//		if(payment.getOwner() != this) {
//			payment.setOwner(this);
//		}
//	}
//	
//	public List<Payment> getOwnedPayment() {
//		return ownedPayment;
//	}
	
	
	private String firstName;
	private String lastName;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	
	@OneToMany(mappedBy="customer")
	@JsonIgnore
	private List<Order> orders;
	
	public void addOrder(Order order) {
		this.orders.add(order);
		if(order.getCustomer() != this) {
			order.setCustomer(this);
		}
	}
	
	public void removeOrder(Order order) {
		if (this.orders.contains(order)) {
			this.orders.remove(order);
		}
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	@ManyToMany
	@JoinTable(name="JOIN_CUSTOMER_RECIPE",
	joinColumns=@JoinColumn(name="CUSTOMER_ID", referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn(name="RECIPE_ID", referencedColumnName="ID"))
	@JsonIgnore
	private List<Recipe> likedRecipes;
	
	public List<Recipe> getLikedRecipes() {
		return this.likedRecipes;
	}
	
	public void likeRecipe(Recipe recipe) {
		this.likedRecipes.add(recipe);
		if(!recipe.getLikedCustomers().contains(this)) {
			recipe.getLikedCustomers().add(this);
		}
	}
	
	public void dislikeRecipe(Recipe recipe) {
		if(!recipe.getLikedCustomers().contains(this)) {
			recipe.getLikedCustomers().remove(this);
		}
	}
	
	@ManyToMany
	@JoinTable(name="JOIN_CUSTOMER_SELLER",
	joinColumns=@JoinColumn(name="CUSTOMER_ID", referencedColumnName="ID"),
	inverseJoinColumns=@JoinColumn(name="SELLER_ID", referencedColumnName="ID"))
	@JsonIgnore
	private List<Seller> followedSellers;
	
	public List<Seller> getFollowedSellers() {
		return this.followedSellers;
	}
	
	public void followSeller(Seller seller) {
		this.followedSellers.add(seller);
		if (!seller.getFollowedCustomers().contains(this)) {
			seller.getFollowedCustomers().add(this);
		}
	}
	
	public void disfollowSeller(Seller seller) {
		if (this.followedSellers.contains(seller)) {
			this.followedSellers.remove(seller);
		}
	}
	
}