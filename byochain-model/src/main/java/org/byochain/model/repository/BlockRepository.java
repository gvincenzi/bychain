package org.byochain.model.repository;

import org.byochain.model.entity.Block;
import org.springframework.data.repository.CrudRepository;

public interface BlockRepository extends CrudRepository<Block, Long> {

}
