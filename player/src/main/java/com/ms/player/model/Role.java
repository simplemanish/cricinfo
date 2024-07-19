package com.ms.player.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Represent a Role entity.
 * 
 * <p>
 * It is mapped to a table names roles.
 * </p>
 * 
 * Please see the {@link com.ms.player.model.Role} class
 * 
 * @author 047929
 * @version 1.0.0
 */
@Entity
@Table(name = "roles")
public class Role {
	/**
	 * Role id. auto-generated.
	 */
	private Long id;
	/**
	 * Role name.
	 */
	private String name;
	/**
	 * Players for a specific roles.
	 */
	private Set<Player> player;

	/**
	 * No argument constructor.
	 */
	public Role() {
		super();
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param id Role id.
	 */
	public Role(Long id) {
		super();
		this.id = id;
	}

	/**
	 * Get role's id.
	 * 
	 * @return role id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * Set role's id.
	 * 
	 * @param id Role id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get role's name.
	 * 
	 * @return Role's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set role's name.
	 * 
	 * @param name Role's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get player's for a role.
	 * 
	 * @return Player entities.
	 */
	@ManyToMany(mappedBy = "roles")
	public Set<Player> getPlayer() {
		return player;
	}

	/**
	 * Set player for a role.
	 * 
	 * @param player Player entities.
	 */
	public void setPlayer(Set<Player> player) {
		this.player = player;
	}
}
