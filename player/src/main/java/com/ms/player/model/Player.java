package com.ms.player.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Represent a player entity.
 * 
 * <p>
 * 	It is mapped to a table names players.
 * </p>
 * 
 * Please see the {@link com.ms.player.model.Player} class
 * @author 047929
 * @version 1.0.0
 */
@Entity
@Table(name = "players")
public class Player {
	/**
	 * Player's id, auto-generated.
	 */
	private Long id;
	/**
	 * Player's first name.
	 */
	private String firstname;
	/**
	 * Player's last name.
	 */
	private String lastname;
	/**
	 * Player's date of birth.
	 */
	private Date dob;
	/**
	 * Player's nationality.
	 */
	private String nationality;
	/**
	 * Player's role.
	 */
	private Set<Role> roles;

	/**
	 * Player no argument constructor.
	 */
	public Player() {
		super();
	}

	/**
	 * Player constructor with argument.
	 * 
	 * @param id          Player's id.
	 * @param firstname   Player's first name.
	 * @param lastname    Player's last name.
	 * @param dob         Player's date of birth.
	 * @param nationality Player's nationality.
	 * @param roles       Player's role.
	 */
	public Player(Long id, String firstname, String lastname, Date dob, String nationality, Set<Role> roles) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.nationality = nationality;
		this.roles = roles;
	}

	/**
	 * Get player's id.
	 * 
	 * @return Player's id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * Set player's id.
	 * 
	 * @param id Player's id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get player's first name.
	 * 
	 * @return First name.
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Set player's first name.
	 * 
	 * @param firstname First name.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Get player's last name.
	 * 
	 * @return Last name.
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Set player's last name.
	 * 
	 * @param lastname Last name.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Get player's roles entity.
	 * 
	 * @return roles.
	 */
	@ManyToMany
	@JoinTable(name = "player_role", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * Set player's roles entity.
	 * 
	 * @param roles roles.
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * Get player's date of birth.
	 * 
	 * @return Date of birth.
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Set player's date of birth.
	 * 
	 * @param dob Date of birth.
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Get player's nationality.
	 * 
	 * @return Nationality.
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Set player's nationality.
	 * 
	 * @param nationality Nationality.
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
