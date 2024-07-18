package com.ms.player.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.player.model.Player;
import com.ms.player.model.Role;
import com.ms.player.repository.PlayerRepository;
import com.ms.player.repository.RoleRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Player save(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public Player findPlayerByNameAndDob(String firstname, String lastname, String nationality) {
		List<Player> playerList = playerRepository.findPlayerByNameAndDob(firstname, lastname, nationality);
		return playerList.size() > 0 ? playerList.get(0) : null;
	}

	@Override
	public Player getPlayerById(Long id) {
		return playerRepository.findById(id).orElse(null);
	}

	@Override
	public void deletePlayer(Player player) {
		playerRepository.delete(player);
	}
}
