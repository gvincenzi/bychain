package org.byochain.services.service.impl.base;

import org.byochain.commons.utils.BlockchainUtils;
import org.byochain.model.entity.Block;
import org.byochain.services.service.impl.BlockService;
import org.springframework.stereotype.Service;

/**
 * Base implementation for {@link BlockService}
 * @author Giuseppe Vincenzi
 *
 */
@Service
public class BaseBlockService extends BlockService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isHashResolved(Block block, Integer difficultLevel) {
		return BlockchainUtils.isHashResolved(difficultLevel, block.getHash());
	}
	
}
