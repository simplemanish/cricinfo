package com.ms.player.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ms.player.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Query("SELECT p FROM Player p WHERE p.firstname = ?1 and p.lastname= ?2 and p.nationality = ?3")
	List<Player> findPlayerByNameAndDob(String firstname, String lastname, String nationality);
}
