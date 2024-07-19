package com.ms.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.player.model.Role;

/**
 * Represent a Spring Data JPA repository for the Role entity.
 * 
 * <p>
 * It provides CRUD operation for role.
 * </p>
 * 
 * Please see the {@link com.ms.player.repository.RoleRepository} class
 * 
 * @author 047929
 * @version 1.0.0
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
