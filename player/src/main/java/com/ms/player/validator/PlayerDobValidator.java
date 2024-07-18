package com.ms.player.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ms.player.form.PlayerForm;
import com.ms.player.model.Player;
import com.ms.player.model.Role;
import com.ms.player.service.PlayerService;
import com.ms.player.service.RoleService;

@Component
public class PlayerDobValidator implements Validator {
	@Autowired
	private PlayerService playerService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(PlayerDobValidator.class);

	@Override
	public boolean supports(Class<?> aClass) {
		return PlayerForm.class.equals(aClass);
	}

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
