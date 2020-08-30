package com.blog.adminpanel.oauth;

import java.util.Calendar;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.blog.adminpanel.component.ApiError;



@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SettingsRepository {

    private String accessToken;
    
    private String refreshToken;

    private String name;
    private String password;
    private Long userId;
  
    private Calendar expiresIn;
    private Long roleId;
    private String roleName;

    private ApiError apierror;
	public String getAccessToken() {
		return accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public Long getUserId() {
		return userId;
	}

	public Calendar getExpiresIn() {
		return expiresIn;
	}
	public Long getRoleId() {
		return roleId;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setExpiresIn(Calendar expiresIn) {
		this.expiresIn = expiresIn;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public ApiError getApierror() {
		return apierror;
	}

	public void setApierror(ApiError apierror) {
		this.apierror = apierror;
	}

	@Override
	public String toString() {
		return "SettingsRepository{" +
				"accessToken='" + accessToken + '\'' +
				", refreshToken='" + refreshToken + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", userId=" + userId +
				", expiresIn=" + expiresIn +
				", roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", apierror=" + apierror +
				'}';
	}
}
