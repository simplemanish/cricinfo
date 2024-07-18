package com.ms.player.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.player.model.Player;
import com.ms.player.model.Role;
import com.ms.player.repository.PlayerRepository;
import com.ms.player.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role getRoleById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}
	
}
