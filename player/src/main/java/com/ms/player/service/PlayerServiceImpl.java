package com.ms.player.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.player.model.Player;
import com.ms.player.repository.PlayerRepository;

/**
 * Provide business logic for managing players.
 * 
 * <p>
 * It handles operations such as creating, finding, deleting, updating players.
 * </p>
 * 
 * Please see the {@link com.ms.player.service.PlayerServiceImpl} class
 * 
 * @author 047929
 * @version 1.0.0
 */
@Service
public class PlayerServiceImpl implements PlayerService {

	/**
	 * Player repository for db operation on player.
	 */
	@Autowired
	private PlayerRepository playerRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player save(Player player) {
		return playerRepository.save(player);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player findPlayerByNameAndDob(String firstname, String lastname, String nationality) {
		List<Player> playerList = playerRepository.findPlayerByNameAndDob(firstname, lastname, nationality);
		return playerList.size() > 0 ? playerList.get(0) : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getPlayerById(Long id) {
		return playerRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePlayer(Player player) {
		playerRepository.delete(player);
	}
}
