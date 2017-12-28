package org.byochain.services.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.byochain.commons.utils.BlockchainUtils;
import org.byochain.model.entity.Block;
import org.byochain.model.repository.BlockRepository;
import org.byochain.services.exception.ByoChainServiceException;
import org.byochain.services.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * BlockService
 * 
 * @author Giuseppe Vincenzi
 *
 */
@Service
public class BlockService implements IBlockService {
	/**
	 * GENESIS
	 */
	private static final String GENESIS = "GENESIS";

	/**
	 * BlockRepository
	 */
	@Autowired
	private BlockRepository blockRepository;

	/**
	 * Level of hash algorithm difficulty
	 */
	@Value("${difficult.level}")
	private Integer difficultLevel;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Block> getAllBlocks() {
		Set<Block> blocks = new TreeSet<>();
		for (Block block : blockRepository.findAll()) {
			blocks.add(block);
		}

		return blocks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Block> getBlocks(Pageable pageable) {
		return blockRepository.findAll(pageable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Block mineBlock(String data, Block previousBlock) throws ByoChainServiceException {
		if (data == null || data.isEmpty()) {
			throw new ByoChainServiceException("Data is mandatory");
		}
		Block block = new Block(data, previousBlock != null ? previousBlock.getHash() : GENESIS);
		
		Random random = new Random(block.getTimestamp().getTimeInMillis());
		int token = Math.abs(random.nextInt());
		block.setToken(token);
		block.setHash(calculateHash(block));
		while (!isHashResolved(block, difficultLevel)) {
			token = Math.abs(random.nextInt());
			block.setToken(token);
			block.setHash(calculateHash(block));
		}

		return block;
	}

	/**
	 * Private method to verify is the hash is resolved by a Block
	 * @param block
	 * @param difficultLevel
	 * @return boolean true if resolved
	 */
	private static boolean isHashResolved(Block block, Integer difficultLevel) {
		return BlockchainUtils.isHashResolved(difficultLevel, block.getHash());
	}

	/**
	 * Private method to calculate the hash for a Block
	 * @param block Block
	 * @return hash
	 */
	private static String calculateHash(Block block) {
		return BlockchainUtils.calculateHash(block.getPreviousHash(), block.getTimestamp().getTimeInMillis(),
				block.getToken(), block.getData());
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Block getBlockByHash(String hash) throws ByoChainServiceException {
		if (hash == null || hash.isEmpty()) {
			throw new ByoChainServiceException("Hash is mandatory");
		}
		return blockRepository.find(hash);
	}

	/**
	 * {@inheritDoc}
	 */
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
