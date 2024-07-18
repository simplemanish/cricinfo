package com.ms.player.service;

import java.util.Collection;

import com.ms.player.model.Player;

public interface PlayerService {

	 public Player save(Player player);
	 public Player findPlayerByNameAndDob(String firstname, String lastname, String nationality);
	 public Player getPlayerById(Long id);
	 public void deletePlayer(Player player);
}
