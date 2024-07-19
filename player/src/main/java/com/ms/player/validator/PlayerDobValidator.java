package com.ms.player.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ms.player.form.PlayerForm;

/**
 * This class responsible to validates date of birth in PlayerForm DTO.
 * 
 * <p>
 * Validates format of dob and null check.
 * </p>
 * 
 * Please see the {@link com.ms.player.validator.PlayerDobValidator} class
 * 
 * @author 047929
 * @version 1.0.0
 */
@Component
public class PlayerDobValidator implements Validator {

	/**
	 * Message source to get language specific messages.
	 */
	@Autowired
	private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(PlayerDobValidator.class);

	/**
	 * Supported class PlayerForm.
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return PlayerForm.class.equals(aClass);
	}

	/**
	 * Validates format of dob and null check.
	 */
	@Override
	public void validate(Object o, Errors errors) {
		PlayerForm playerForm = (PlayerForm) o;
		// dob format check yyyy-MM-dd
		if (null == playerForm.getDob()) {
			errors.rejectValue("dob", "", messageSource.getMessage("validation.date.format", null, null));
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			try {
				dateFormat.parse(playerForm.getDob());
			} catch (ParseException e) {
				errors.rejectValue("dob", "", messageSource.getMessage("validation.date.format", null, null));
			}
		}
	}
}
