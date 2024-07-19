package com.ms.player.form;

import java.util.Objects;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Player form DTO to collect player information. 
 * 
 * Please see the {@link com.ms.player.form.PlayerForm} class
 * 
 * @author 047929
 * @version 1.0.0
 *
 */
public class PlayerForm {

	/**
	 * Player's id.
	 */
	private Long id;

	/**
	 * Player's first name.
	 */
	@Size(min = 4, max = 20, message = "{validation.size}")
	private String firstname;
	
	/**
	 * Player's last name
	 */
	@Size(min = 4, max = 20, message = "{validation.size}")
	private String lastname;

	/**
	 * Player's date of birth.
	 */
	@NotEmpty(message = "{validation.notempty}")
	private String dob;
	
	/**
	 * Player's nationality
	 */
	@NotEmpty(message = "{validation.notempty}")
	private String nationality;
	
	/**
	 * Player's role ids.
	 */
	@NotEmpty(message = "{validation.notempty}")
	private Set<Long> roles;

	/**
	 * Player's role name.
	 */
	private Set<String> roleNames;

	/**
	 * Default constructor.
	 */
	public PlayerForm() {

	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param id
	 * 			player's id
	 * @param firstname
	 * 			player's first name.
	 * @param lastname	
	 * 			player's last name.
	 * @param dob
	 * 			player's date of birth.
	 * @param nationality
	 * 			player's nationality
	 * @param roles
	 * 			player's role ids.
	 */
	public PlayerForm(Long id, String firstname, String lastname, String dob, String nationality, Set<Long> roles) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.nationality = nationality;
		this.roles = roles;
	}

	/**
	 * Get player id.
	 * @return 
	 * 		id.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Set player id.
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get player's first name.
	 * 
	 * @return
	 * 		first name.
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Set player's first name.
	 * 
	 * @param firstname
	 * 			first name.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Get player's last name.
	 * 
	 * @return
	 * 		last name.	
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Set player's last name.
	 * 
	 * @param lastname
	 * 			last name.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Get player's date of birth.
	 * 
	 * @return
	 * 		date of birth.
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Set player's date of birth.
	 * 
	 * @param dob
	 * 			date of birth.
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * Get player's nationality.
	 * 
	 * @return
	 * 		nationality.
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Set player's nationality.
	 * 
	 * @param nationality
	 * 			nationality.
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Get player's role ids.
	 * 
	 * @return
	 * 		role ids.
	 */
	public Set<Long> getRoles() {
		return roles;
	}

	/**
	 * Set player's role ids.
	 * 
	 * @param roles
	 * 			role ids.
	 */
	public void setRoles(Set<Long> roles) {
		this.roles = roles;
	}

	/**
	 * Get player's role names.
	 * 
	 * @return
	 * 		role names.
	 */
	public Set<String> getRoleNames() {
		return roleNames;
	}

	/**
	 * Set player's role names.
	 * 
	 * @param roleNames
	 * 			role names.
	 */
	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}

	/**
	 * hash code.
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dob, firstname, id, lastname, nationality, roles);
	}

	/**
	 * equals method.
	 */
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
