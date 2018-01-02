package org.byochain.model.repository;

import org.byochain.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * UserRepository
 * @author Giuseppe Vincenzi
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    public User find(@Param("username") String username, @Param("password") String password);
}
