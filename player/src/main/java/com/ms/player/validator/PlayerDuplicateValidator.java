package com.ms.player.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ms.player.form.PlayerForm;
import com.ms.player.model.Player;
import com.ms.player.service.PlayerService;

/**
 * This class responsible for validating duplicate player check.
 * 
 * <p>
 * Validates duplicate player if exists.
 * </p>
 * 
 * Please see the {@link com.ms.player.validator.PlayerDuplicateValidator} class
 * 
 * @author 047929
 * @version 1.0.0
 */
@Component
public class PlayerDuplicateValidator implements Validator {
	/**
	 * Player service.
	 */
	@Autowired
	private PlayerService playerService;

	/**
	 * Message source to get language specific messages.
	 */
	@Autowired
	private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(PlayerDuplicateValidator.class);

	/**
	 * Supported class PlayerForm.
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return PlayerForm.class.equals(aClass);
	}

	/**
	 * Validates if player not exists with same firstname, lastname and nationality.
	 */
	@Override
	public void validate(Object o, Errors errors) {
		PlayerForm playerForm = (PlayerForm) o;

		// existing user firstname, lastname and nationality should not be same
		Player player = playerService.findPlayerByNameAndDob(playerForm.getFirstname(), playerForm.getLastname(),
				playerForm.getNationality());
		if (null != player) {
			errors.rejectValue("firstname", "", messageSource.getMessage("validation.player.duplicate", null, null));
		}

	}

}
