package org.byochain.services.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.byochain.commons.utils.BlockchainUtils;
import org.byochain.model.entity.Block;
import org.byochain.model.repository.BlockRepository;
import org.byochain.services.exception.ByoChainServiceException;
import org.byochain.services.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * BlockService
 * 
 * @author Giuseppe Vincenzi
 *
 */
@Service
public class BlockService implements IBlockService {
	private static final String GENESIS = "GENESIS";

	@Autowired
	private BlockRepository blockRepository;

	@Value("${difficult.level}")
	private Integer difficultLevel;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Block> getBlocks() {
		Set<Block> blocks = new TreeSet<>();
		for (Block block : blockRepository.findAll()) {
			blocks.add(block);
		}

		return blocks;
	}

	@Override
	public Block mineBlock(String data, Block previousBlock) throws ByoChainServiceException {
		if (data == null || data.isEmpty()) {
			throw new ByoChainServiceException("Data is mandatory");
		}
		Block block = new Block(data, previousBlock != null ? previousBlock.getHash() : GENESIS);
		int token = 0;
		block.setToken(token);
		block.setHash(calculateHash(block));
		while (!isHashResolved(block, difficultLevel)) {
			token++;
			block.setToken(token);
			block.setHash(calculateHash(block));
		}

		return block;
	}

	private static boolean isHashResolved(Block block, Integer difficultLevel) {
		return BlockchainUtils.isHashResolved(difficultLevel, block.getHash());
	}

	private static String calculateHash(Block block) {
		return BlockchainUtils.calculateHash(block.getPreviousHash(), block.getTimestamp().getTimeInMillis(),
				block.getToken(), block.getData());
	}

	@Override
	public Boolean validateChain(Iterable<Block> blockchain) throws ByoChainServiceException {
		if (blockchain == null) {
			throw new ByoChainServiceException("Iterable blockchain object is mandatory");
		}
		Block currentBlock;
		Block previousBlock;

		List<Block> blocks = new ArrayList<>();

		blockchain.forEach(block -> blocks.add(block));
		Collections.sort(blocks);

		Boolean result = true;
		for (int i = 1; i < blocks.size(); i++) {
			previousBlock = blocks.get(i - 1);
			currentBlock = blocks.get(i);
			if (!currentBlock.getHash().equals(calculateHash(currentBlock))) {
				result = false;
			}
			if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
				result = false;
			}
			if (!isHashResolved(currentBlock, difficultLevel)) {
				result = false;
			}
		}

		return result;
	}

	@Override
	public Block getBlockByHash(String hash) throws ByoChainServiceException {
		if (hash == null || hash.isEmpty()) {
			throw new ByoChainServiceException("Hash is mandatory");
		}
		return blockRepository.find(hash);
	}

	@Override
	public Block addBlock(String data) throws ByoChainServiceException {
		if (data == null || data.isEmpty()) {
			throw new ByoChainServiceException("Data is mandatory");
		}
		Block previousBlock = blockRepository.findLast();
		Block newBlock = mineBlock(data, previousBlock);
		return blockRepository.save(newBlock);
	}
}
