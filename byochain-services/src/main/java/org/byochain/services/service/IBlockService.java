package org.byochain.services.service;

import java.util.Set;

import org.byochain.model.entity.Block;
import org.byochain.services.exception.ByoChainServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * IBlockService
 * @author Giuseppe Vincenzi
 *
 */
public interface IBlockService {

	Set<Block> getAllBlocks();
	Page<Block> getBlocks(Pageable pageable);
	Block getBlockByHash(String hash) throws ByoChainServiceException;
	Block addBlock(String data) throws ByoChainServiceException;
	Block mineBlock(String data, Block previousBlock) throws ByoChainServiceException;
	Boolean validateChain(Iterable<Block> blockchain) throws ByoChainServiceException;
}
