package com.boomshankar.authserver.Dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String phone;
	private String email;
	private boolean isActive;
	private boolean isPasswordTemporary;

	private String lastModifiedBy;
	private Date lastModifiedDatetime;

	private String errorMessage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isPasswordTemporary() {
		return isPasswordTemporary;
	}

	public void setPasswordTemporary(boolean isPasswordTemporary) {
		this.isPasswordTemporary = isPasswordTemporary;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDatetime() {
		return lastModifiedDatetime;
	}

	public void setLastModifiedDatetime(Date lastModifiedDatetime) {
		this.lastModifiedDatetime = lastModifiedDatetime;
	}

	public UserDto(int id, String firstName, String lastName, String phone, String email, boolean isActive,
			boolean isPasswordTemporary, String lastModifiedBy, Date lastModifiedDatetime) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.isActive = isActive;
		this.isPasswordTemporary = isPasswordTemporary;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDatetime = lastModifiedDatetime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public UserDto() {
	}

}
