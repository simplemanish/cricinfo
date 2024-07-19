package com.ms.player.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ms.player.model.Player;

/**
 * Represent a Spring Data JPA repository for the Player entity.
 * 
 * <p>
 * It provides CRUD operation for player.
 * </p>
 * 
 * Please see the {@link com.ms.player.repository.PlayerRepository} class
 * 
 * @author 047929
 * @version 1.0.0
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

	/**
	 * Find players by first name, last name and nationality.
	 * 
	 * @param firstname
	 * @param lastname
	 * @param nationality
	 * @return
	 */
	@Query("SELECT p FROM Player p WHERE p.firstname = ?1 and p.lastname= ?2 and p.nationality = ?3")
	List<Player> findPlayerByNameAndDob(String firstname, String lastname, String nationality);
}
