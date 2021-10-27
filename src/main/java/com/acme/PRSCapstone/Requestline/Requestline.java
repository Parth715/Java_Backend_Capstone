package com.acme.PRSCapstone.Requestline;

import javax.persistence.*;
import com.acme.PRSCapstone.Product.Product;
import com.acme.PRSCapstone.Request.Request;

@Entity(name = "requestlines")

public class Requestline {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne(optional=false)
	@JoinColumn(name="requestId")
	private Request request;
	@ManyToOne(optional=false)
	@JoinColumn(name="productId")
	private Product product;
	@Column(nullable = false)
	private int quantity;
	
	public Requestline() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
