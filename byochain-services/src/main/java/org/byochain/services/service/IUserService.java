package org.byochain.services.service;

import org.byochain.model.entity.User;
import org.byochain.services.exception.ByoChainServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * IUserService
 * @author Giuseppe Vincenzi
 *
 */
public interface IUserService {

	Page<User> getUsers(Pageable pageable);
	User addUser(User user) throws ByoChainServiceException;
}
