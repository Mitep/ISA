package isa.projekat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userPassword")
public class UserPassword implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private long userId;

	@NotNull
	private String oldPassword;
	
	@NotNull
	private String newPassword;
	
	@NotNull
	private String repeatNewPassword;

	public UserPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPassword(@NotNull String oldPassword, @NotNull String newPassword, @NotNull String repeatNewPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.repeatNewPassword = repeatNewPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatNewPassword() {
		return repeatNewPassword;
	}

	public void setRepeatNewPassword(String repeatNewPassword) {
		this.repeatNewPassword = repeatNewPassword;
	}
	
	
	
}
