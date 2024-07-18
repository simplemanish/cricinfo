package com.ms.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.player.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
