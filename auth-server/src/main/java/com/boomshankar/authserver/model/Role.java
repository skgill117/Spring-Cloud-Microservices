package com.boomshankar.authserver.model;
// Generated Mar 23, 2017 2:42:13 PM by Hibernate Tools 5.2.0.Beta1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "role", catalog = "customer_details")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String roleName;
	private String lastModifiedBy;
	private Date lastModifiedDatetime;
	private Set<UserRoleMapping> userRoleMappings = new HashSet<UserRoleMapping>(0);

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role(String roleName, String lastModifiedBy, Date lastModifiedDatetime,
			Set<UserRoleMapping> userRoleMappings) {
		this.roleName = roleName;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDatetime = lastModifiedDatetime;
		this.userRoleMappings = userRoleMappings;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "role_name", nullable = false, length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "last_modified_by", length = 65535)
	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_datetime", length = 19)
	public Date getLastModifiedDatetime() {
		return this.lastModifiedDatetime;
	}

	public void setLastModifiedDatetime(Date lastModifiedDatetime) {
		this.lastModifiedDatetime = lastModifiedDatetime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<UserRoleMapping> getUserRoleMappings() {
		return this.userRoleMappings;
	}

	public void setUserRoleMappings(Set<UserRoleMapping> userRoleMappings) {
		this.userRoleMappings = userRoleMappings;
	}

}
