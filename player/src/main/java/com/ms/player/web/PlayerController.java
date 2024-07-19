package com.ms.player.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.player.form.PlayerForm;
import com.ms.player.model.Player;
import com.ms.player.model.Role;
import com.ms.player.service.PlayerService;
import com.ms.player.service.RoleService;
import com.ms.player.validator.PlayerDobValidator;
import com.ms.player.validator.PlayerDuplicateValidator;
import com.ms.player.validator.RoleValidator;

import jakarta.validation.Valid;

/**
 * PlayerController is a REST controller that handles HTTP requests for player
 * related operations.
 * 
 * <p>
 * It provides end-points for creating, retrieving, updating and deleting player
 * resource.
 * </p>
 * 
 * Please see the {@link com.ms.player.web.PlayerController} class
 * 
 * @author 047929
 * @version 1.0.0
 */
@RestController
@RequestMapping("/v1/player")
public class PlayerController {

	/**
	 * Player service.
	 */
	@Autowired
	PlayerService playerService;
	/**
	 * User service.
	 */
	@Autowired
	RoleService roleService;
	/**
	 * Player date of birth validator.
	 */
	@Autowired
	PlayerDobValidator playerDobValidator;
	/**
	 * Player duplicate validator.
	 */
	@Autowired
	PlayerDuplicateValidator playerDuplicateValidator;
	/**
	 * Role validator.
	 */
	@Autowired
	RoleValidator roleValidator;

	Logger logger = LoggerFactory.getLogger(PlayerController.class);

	/**
	 * Create player.
	 * 
	 * @param playerForm    player information.
	 * @param bindingResult
	 * 
	 * @param model
	 * 
	 * @return Success message or error message.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createPlayer(@Valid @RequestBody PlayerForm playerForm, BindingResult bindingResult,
			Model model) {
		logger.info("adding player ");
		playerDobValidator.validate(playerForm, bindingResult);
		playerDuplicateValidator.validate(playerForm, bindingResult);
		roleValidator.validate(playerForm, bindingResult);

		JSONObject resp = new JSONObject();
		if (bindingResult.hasErrors()) {
			logger.warn("input validations failed");
			List<FieldError> errors = bindingResult.getFieldErrors();
			Map<String, String> resError = new HashMap<>();
			for (FieldError error : errors) {
				resError.put(error.getField(), error.getDefaultMessage());
			}
			resp.put("error", resError);
			return new ResponseEntity<>(resp.toString(), HttpStatus.BAD_REQUEST);
		}

		Player player = new Player();
		setPlayer(playerForm, player);
		playerService.save(player);
		logger.info("Player registration successful");

		resp.put("message", "Player added successful");
		return new ResponseEntity<>(resp.toString(), HttpStatus.CREATED);
	}

	/**
	 * Get player details by player id.
	 * 
	 * @param id    Player's id.
	 * 
	 * @param model
	 * 
	 * @return Success message or error message.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<String> getPlayerDetails(@PathVariable Long id, Model model) {
		logger.info("get player details ");
		Player player = playerService.getPlayerById(id);
		JSONObject resp = new JSONObject();
		if (null == player) {
			resp.put("error", "Player not found");
			return new ResponseEntity<>(resp.toString(), HttpStatus.NOT_FOUND);
		}
		PlayerForm playerForm = convertToPlayerForm(player);

		ObjectMapper objectMapper = new ObjectMapper();
		logger.info("Player successful found");
		try {
			return new ResponseEntity<>(objectMapper.writeValueAsString(playerForm), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			resp.put("error", "server error");
			return new ResponseEntity<>(resp.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update player's details.
	 * 
	 * @param playerForm    Player's information to be updated.
	 * @param id            Player's id.
	 * @param bindingResult
	 * @param model
	 * @return Success message or error message.
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<String> updatePlayer(@RequestBody PlayerForm playerForm, @PathVariable Long id,
			BindingResult bindingResult, Model model) {
		logger.info("updating player details ");

		JSONObject resp = new JSONObject();
		Player player = playerService.getPlayerById(id);
		if (null == player) {
			resp.put("error", "Player not found");
			return new ResponseEntity<>(resp.toString(), HttpStatus.NOT_FOUND);
		}
		if (null != playerForm.getDob() && !playerForm.getDob().isEmpty()) {
			playerDobValidator.validate(playerForm, bindingResult);
		}

		if (null != playerForm.getRoles() && !playerForm.getRoles().isEmpty()) {
			roleValidator.validate(playerForm, bindingResult);
		}
		if (bindingResult.hasErrors()) {
			logger.warn("input validations failed");
			List<FieldError> errors = bindingResult.getFieldErrors();
			Map<String, String> resError = new HashMap<>();
			for (FieldError error : errors) {
				resError.put(error.getField(), error.getDefaultMessage());
			}
			resp.put("error", resError);
			return new ResponseEntity<>(resp.toString(), HttpStatus.BAD_REQUEST);
		}

		setPlayer(playerForm, player);
		playerService.save(player);

		logger.info("Player updated successful");

		resp.put("message", "Player updated successful");
		return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
	}

	/**
	 * Delete a player.
	 * 
	 * @param id    Player's id to be deleted.
	 * @param model
	 * @return Success message or error message.
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletePlayer(@PathVariable Long id, Model model) {
		logger.info("updating player details ");

		JSONObject resp = new JSONObject();
		Player player = playerService.getPlayerById(id);
		if (null == player) {
			resp.put("error", "Player not found");
			return new ResponseEntity<>(resp.toString(), HttpStatus.NOT_FOUND);
		}
		playerService.deletePlayer(player);
		logger.info("Player deleted successful");
		resp.put("message", "Player deleted successful");
		return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
	}

	private void setPlayer(PlayerForm playerForm, Player player) {
		if (null != playerForm.getFirstname()) {
			player.setFirstname(playerForm.getFirstname());
		}
		if (null != playerForm.getLastname()) {
			player.setLastname(playerForm.getLastname());
		}
		if (null != playerForm.getNationality()) {
			player.setNationality(playerForm.getNationality());
		}

		if (null != playerForm.getDob()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date = null;
			try {
				date = formatter.parse(playerForm.getDob());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			player.setDob(date);
		}

		Set<Long> roleIds = playerForm.getRoles();
		if (null != roleIds) {
			Set<Role> roles = new HashSet<Role>();
			for (Long id : roleIds) {
				roles.add(roleService.getRoleById(id));
			}
			player.setRoles(roles);
		}
	}

	private PlayerForm convertToPlayerForm(Player player) {
		PlayerForm playerForm = new PlayerForm();
		playerForm.setId(player.getId());
		playerForm.setFirstname(player.getFirstname());
		playerForm.setLastname(player.getLastname());
		playerForm.setNationality(player.getNationality());

		String pattern = "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(pattern);
		String dob = df.format(player.getDob());
		playerForm.setDob(dob);

		Set<Role> roles = player.getRoles();
		Set<Long> roleIds = new HashSet<>();
		Set<String> roleNames = new HashSet<>();
		roles.forEach((role) -> {
			roleIds.add(role.getId());
			roleNames.add(role.getName());
		});

		playerForm.setRoles(roleIds);
		playerForm.setRoleNames(roleNames);

		return playerForm;
	}

}
