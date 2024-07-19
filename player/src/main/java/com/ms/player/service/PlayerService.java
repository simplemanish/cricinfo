package com.ms.player.service;

import com.ms.player.model.Player;

/**
 * Provide business logic for managing players.
 * 
 * <p>
 * It handles operations such as creating, finding, deleting, updating players.
 * </p>
 * 
 * Please see the {@link com.ms.player.service.PlayerService} class
 * 
 * @author 047929
 * @version 1.0.0
 */
public interface PlayerService {

	/**
	 * Create new or update an existing player.
	 * 
	 * @param player player to create or update..
	 * @return created or updated player.
	 */
	public Player save(Player player);

	/**
	 * Find a player by it's first name, last name and nationality.
	 * 
	 * @param firstname
	 * 			First name.
	 * @param lastname
	 * 			Last name.
	 * @param nationality
	 * 			Nationality.
	 * @return
	 * 		Player.
	 */
	public Player findPlayerByNameAndDob(String firstname, String lastname, String nationality);

	/**
	 * Get a player by player id.
	 * 
	 * @param id
	 * 			Player id.
	 * @return
	 * 		Player.
	 */
	public Player getPlayerById(Long id);

	/**
	 * Delete a player.
	 * 
	 * @param player
	 * 			Player to be deleted.
	 */
	public void deletePlayer(Player player);
}
