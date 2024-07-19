package com.ms.player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.player.model.Role;
import com.ms.player.repository.RoleRepository;

/**
 * Provide business logic for finding role.
 * 
 * <p>
 * It handles operations such as finding role.
 * </p>
 * 
 * Please see the {@link com.ms.player.service.RoleServiceImpl} class
 * 
 * @author 047929
 * @version 1.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	/**
	 * Role repository for db operation on role.
	 */
	@Autowired
	RoleRepository roleRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Role getRoleById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}
	
}
