package org.byochain.model.repository;

import org.byochain.model.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * RoleRepository
 * @author Giuseppe Vincenzi
 *
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
	@Query("SELECT r FROM Role r WHERE r.role = :roleName")
    public Role find(@Param("roleName") String roleName);
}
