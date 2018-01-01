package org.byochain.services.service.impl;

import java.util.Calendar;

import org.byochain.commons.encoder.BYOChainPasswordEncoder;
import org.byochain.model.entity.Role;
import org.byochain.model.entity.User;
import org.byochain.model.repository.RoleRepository;
import org.byochain.model.repository.UserRepository;
import org.byochain.services.exception.ByoChainServiceException;
import org.byochain.services.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService
 * @author Giuseppe Vincenzi
 *
 */
@Service
public class UserService implements IUserService {
	
	private static final String ROLE_USER = "ROLE_USER";

	/**
	 * UserRepository
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * UserRepository
	 */
	@Autowired
	private RoleRepository roleRepository;
	
	private BYOChainPasswordEncoder encoder;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public User addUser(User user) throws ByoChainServiceException{
		/** Check mandatory fields **/
		if(user==null){
			throw new ByoChainServiceException("User is mandatory");
		}
		
		if(user.getUsername()==null || user.getUsername().isEmpty()){
			throw new ByoChainServiceException("Username is mandatory");
		}
		
		if(user.getPassword()!=null && !user.getPassword().isEmpty()){
			throw new ByoChainServiceException("Password must be null : it will be autogenerated");
		}
		/** ---------------------- **/
		
		/** The password is returned after creation : it's possible to implement other more secure solutions **/
		user.setTemporaryPassword(getEncoder().generateTemporaryPassword());
		/** ------------------------------------------------------------------------------------------------ **/
		user.setPassword(getEncoder().encode(user.getTemporaryPassword()));
		
		/** Set a default if it is not present **/
		if(user.getRoles().isEmpty()){
			Role role = null;
			role = roleRepository.find(ROLE_USER);
			if(role == null){
				throw new ByoChainServiceException("BYOChain database has not been correctly initialized : ROLE_USER does not exist");
			}
			
			user.getRoles().add(role);
		}
		
		/** Set a creation date **/
		user.setCreationDate(Calendar.getInstance());

		return userRepository.save(user);
	}

	/**
	 * @return the encoder
	 */
	public BYOChainPasswordEncoder getEncoder() {
		if(encoder == null){
			encoder = new BYOChainPasswordEncoder();
		}
		return encoder;
	}

}
