package org.byochain.services;

import java.util.HashSet;
import java.util.Set;

import org.byochain.model.entity.Block;
import org.byochain.model.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BlockService
 * @author Giuseppe Vincenzi
 *
 */
@Service
public class BlockService implements IBlockService {
	@Autowired
	private BlockRepository blockRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Block> getBlocks() {
		Set<Block> blocks = new HashSet<>();
		for (Block block : blockRepository.findAll()) {
			blocks.add(block);
		}
		
		return blocks;
	}
	
	
}
