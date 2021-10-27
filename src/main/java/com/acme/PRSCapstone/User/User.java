package com.acme.PRSCapstone.User;
import javax.persistence.*;

@Entity(name="users")
@Table(uniqueConstraints=@UniqueConstraint(name="Username", columnNames= {"username"}))
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String username;
	@Column(length=30, nullable=false)
	private String password;
	@Column(length=30, nullable=false)
	private String firstname;
	@Column(length=30, nullable=false)
	private String lastname;
	@Column(length=14)
	private String phone;
	@Column(length=80)
	private String email;
	private Boolean IsReviewer;
	private Boolean IsAdmin;
	
	public User() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsReviewer() {
		return IsReviewer;
	}
	public void setIsReviewer(Boolean isReviewer) {
		IsReviewer = isReviewer;
	}
	public Boolean getIsAdmin() {
		return IsAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		IsAdmin = isAdmin;
	}
	
	
}
