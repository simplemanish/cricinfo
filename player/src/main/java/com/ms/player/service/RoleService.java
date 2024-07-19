package com.ms.player.service;

import com.ms.player.model.Role;

/**
 * Provide business logic for finding role.
 * 
 * <p>
 * It handles operations such as finding role.
 * </p>
 * 
 * Please see the {@link com.ms.player.service.RoleService} class
 * 
 * @author 047929
 * @version 1.0.0
 */
public interface RoleService {

	/**
	 * Get role by role id.
	 * 
	 * @param id
	 * 			role id.
	 * @return
	 * 		Role 
	 */
	Role getRoleById(Long id);
}
