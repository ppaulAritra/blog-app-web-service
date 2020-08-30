/**
 * 
 */
package com.blog.adminpanel.domain.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.blog.adminpanel.domain.Image;

import javax.validation.constraints.Size;


/**
 * @author Aritra
 *
 */
public class BloggerDTO {
	private Long id;
	private String name;
	@Size(min = 6)
	private String password;
	private String email;
	private String phone;
	private Long imageId;
	private String imageUrl;
	private Long roleId;
	private Boolean isApproved;
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Boolean getApproved() {
		return isApproved;
	}

	public void setApproved(Boolean approved) {
		isApproved = approved;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

	@Override
	public String toString() {
		return "BloggerDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", imageId=" + imageId +
				", imageUrl='" + imageUrl + '\'' +
				", roleId=" + roleId +
				", isApproved=" + isApproved +
				", isDeleted=" + isDeleted +
				'}';
	}
}
