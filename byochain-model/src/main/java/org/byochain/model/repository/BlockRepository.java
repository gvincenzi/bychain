package org.byochain.model.repository;

import org.byochain.model.entity.Block;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * BlockRepository
 * @author Giuseppe Vincenzi
 *
 */
public interface BlockRepository extends CrudRepository<Block, Long> {
	
	@Query("SELECT b FROM Block b WHERE b.hash = :hash")
    public Block find(@Param("hash") String hash);
	
	@Query("SELECT b FROM Block b WHERE b.id = (SELECT MAX(b.id) FROM Block b)")
    public Block findLast();
}
