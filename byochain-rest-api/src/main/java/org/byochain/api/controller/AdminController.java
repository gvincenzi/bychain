package org.byochain.api.controller;

import java.util.Locale;

import org.byochain.api.enumeration.ByoChainApiExceptionEnum;
import org.byochain.api.enumeration.ByoChainApiResponseEnum;
import org.byochain.api.request.UserCreationRequest;
import org.byochain.api.request.UserUpdateRequest;
import org.byochain.api.response.ByoChainApiResponse;
import org.byochain.commons.exception.ByoChainException;
import org.byochain.model.entity.User;
import org.byochain.services.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController : BlockController
 * 
 * @author Giuseppe Vincenzi
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends ByoChainController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ByoChainApiResponse users(Pageable pageable, Locale locale) {
		ByoChainApiResponse response = getUsers(pageable, locale);
		return response;
	}

	@RequestMapping(value = "/users/roles", method = RequestMethod.GET)
	public ByoChainApiResponse usersWithRoles(Pageable pageable, Locale locale) {
		ByoChainApiResponse response = getUsers(pageable, locale);
		return response;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ByoChainApiResponse updateUser(@PathVariable("id") Long userId, @RequestBody UserUpdateRequest request, Locale locale) throws ByoChainException {
		if (request == null || request.getEnable() == null || userId == null) {
			throw ByoChainApiExceptionEnum.ADMIN_CONTROLLER_USER_ENABLING_MANDATORY.getExceptionBeforeServiceCall(messageSource, locale);
		}

		User user = userService.enableUser(userId, request.getEnable());
		ByoChainApiResponse response = null;
		response = ByoChainApiResponseEnum.CONTROLLER_OK.getResponse(messageSource, locale);
		response.setData(user);
		return response;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ByoChainApiResponse users(@RequestBody UserCreationRequest request, Locale locale) throws ByoChainException {
		if (request == null || request.getUsername() == null || request.getUsername().isEmpty()) {
			throw ByoChainApiExceptionEnum.ADMIN_CONTROLLER_USER_DATA_MANDATORY.getExceptionBeforeServiceCall(messageSource, locale);
		}
		
		User user = new User();
		user.setUsername(request.getUsername());
		user = userService.addUser(user);
		ByoChainApiResponse response = ByoChainApiResponseEnum.CONTROLLER_OK.getResponse(messageSource, locale);
		response.setData(user);
		return response;
	}
	
	/**
	 * Private method to retrieve the list of Users
	 * @param pageable Pageable
	 * @param locale Locale
	 * @return ByoChainApiResponse
	 */
	private ByoChainApiResponse getUsers(Pageable pageable, Locale locale) {
		Page<User> users = userService.getUsers(pageable);
		ByoChainApiResponse response = ByoChainApiResponseEnum.CONTROLLER_OK.getResponse(messageSource, locale);
		response.setData(users);
		return response;
	}

}
