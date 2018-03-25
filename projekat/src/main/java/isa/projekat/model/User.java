package isa.projekat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotNull

	private String email;
	
	@NotNull
	private String userPassword;
	
	@NotNull
	private String userPasswordConf;
	
	@NotNull
	private  String userName;
	
	@NotNull
	private String userSurname;
	
	@NotNull
	private String city;
	
	@NotNull
	private String mobileNumber;
	
	@NotNull
	private String userRole;
	
	public User() {
		super();
	}

	public User(String email, String userPassword, String userPasswordConf,String userName,
			String userSurname,String city, String mobileNumber, String userRole) {
		super();
		this.email = email;
		this.userPassword = userPassword;
		this.userPasswordConf = userPasswordConf;
		this.userName = userName;
		this.userSurname = userSurname;
		this.city = city;
		this.mobileNumber = mobileNumber;
		this.userRole = userRole;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserPasswordConf() {
		return userPasswordConf;
	}

	public void setUserPasswordConf(String userPasswordConf) {
		this.userPasswordConf = userPasswordConf;
	}
	
	
	
	
	
}
