package com.acme.PRSCapstone.Request;

import java.math.BigDecimal;

import javax.persistence.*;

import com.acme.PRSCapstone.User.User;

@Entity(name="requests")

public class Request {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=80, nullable = false)
	private String description;
	@Column(length=80, nullable = false)
	private String justification;
	@Column(length=80)
	private String rejectionreason;
	@Column(length=20, nullable = false)
	private String deliverymode;
	@Column(length=10, nullable = false)
	private String status;
	@Column(columnDefinition = "NOT NULL DEFAULT 0.0")
	private double total;
	@ManyToOne(optional=false)
	@JoinColumn(name="userId")
	private User user;
	
	
	public Request() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getRejectionreason() {
		return rejectionreason;
	}
	public void setRejectionreason(String rejectionreason) {
		this.rejectionreason = rejectionreason;
	}
	public String getDeliverymode() {
		return deliverymode;
	}
	public void setDeliverymode(String deliverymode) {
		this.deliverymode = deliverymode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
