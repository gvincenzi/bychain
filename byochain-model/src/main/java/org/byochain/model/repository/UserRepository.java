package org.byochain.model.repository;

import org.byochain.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * UserRepository
 * @author Giuseppe Vincenzi
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
