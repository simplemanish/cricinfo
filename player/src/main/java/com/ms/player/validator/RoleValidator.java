package com.ms.player.validator;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ms.player.form.PlayerForm;
import com.ms.player.model.Role;
import com.ms.player.service.RoleService;

/**
 * This class responsible for validating Role.
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
public class RoleValidator implements Validator {

	/**
	 * Role service.
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * Message source to get language specific messages.
	 */
	@Autowired
	private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(RoleValidator.class);

	/**
	 * Supported class PlayerForm.
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return PlayerForm.class.equals(aClass);
	}

	/**
	 * Validate if role id does not exist and roles itself not there in playerForm.
	 */
	@Override
	public void validate(Object o, Errors errors) {
		PlayerForm playerForm = (PlayerForm) o;

		if (null == playerForm.getRoles()) {
			errors.rejectValue("roles", "", messageSource.getMessage("validation.role.id", null, null));
		} else if (!playerForm.getRoles().isEmpty()) {
			// invalid role id
			Set<Long> roleIds = playerForm.getRoles();
			for (Long id : roleIds) {
				Role role = roleService.getRoleById(id);
				if (role == null) {
					errors.rejectValue("roles", "", messageSource.getMessage("validation.role.id", null, null));
					break;
				}
			}
		}
	}
}
