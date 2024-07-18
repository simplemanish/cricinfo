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
public class RoleValidator implements Validator {
	@Autowired
	private PlayerService playerService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MessageSource messageSource;
	 
	Logger logger = LoggerFactory.getLogger(RoleValidator.class);

	@Override
	public boolean supports(Class<?> aClass) {
		return PlayerForm.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		PlayerForm playerForm = (PlayerForm) o;
		
		if(null == playerForm.getRoles()) {
			errors.rejectValue("roles","",messageSource.getMessage("validation.role.id",null,null));
		}
		else if(!playerForm.getRoles().isEmpty()) {
			// invalid role id
			Set<Long> roleIds = playerForm.getRoles();
			for (Long id : roleIds) {
				Role role = roleService.getRoleById(id);
				if (role == null) {
					errors.rejectValue("roles","",messageSource.getMessage("validation.role.id",null,null));
					break;
				}
			}
		}
	}
}
