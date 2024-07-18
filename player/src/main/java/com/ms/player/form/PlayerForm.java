package com.ms.player.form;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PlayerForm {

	private Long id;

	@Size(min = 4, max = 20, message = "{validation.size}")
	private String firstname;
	@Size(min = 4, max = 20, message = "{validation.size}")
	private String lastname;

	@NotEmpty(message = "{validation.notempty}")
	private String dob;
	@NotEmpty(message = "{validation.notempty}")
	private String nationality;
	@NotEmpty(message = "{validation.notempty}")
	private Set<Long> roles;

	private Set<String> roleNames;

	public PlayerForm() {

	}

	public PlayerForm(Long id, String firstname, String lastname, String dob, String nationality, Set<Long> roles) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.nationality = nationality;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Set<Long> getRoles() {
		return roles;
	}

	public void setRoles(Set<Long> roles) {
		this.roles = roles;
	}

	public Set<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dob, firstname, id, lastname, nationality, roles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerForm other = (PlayerForm) obj;
		return Objects.equals(dob, other.dob) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(id, other.id) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(nationality, other.nationality) && Objects.equals(roles, other.roles);
	}
	
	

}
