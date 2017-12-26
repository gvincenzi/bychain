package org.byochain.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.byochain.commons.utils.BlockchainUtils;
import org.byochain.model.entity.Block;
import org.byochain.model.repository.BlockRepository;
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
		Set<Block> blocks = new HashSet<>();
		for (Block block : blockRepository.findAll()) {
			blocks.add(block);
		}

		return blocks;
	}

	@Override
	public Block mineBlock(String data, Block previousBlock) {
		Block block = new Block(data, previousBlock != null ? previousBlock.getHash() : GENESIS);
		int token = 0;
		block.setToken(token);
		block.setHash(block.calculateHash());
		while (!BlockchainUtils.isHashResolved(difficultLevel, block.getHash())) {
			block.setToken(++token);
			block.setHash(block.calculateHash());
		}

		return block;
	}

	@Override
	public Boolean validateChain(Iterable<Block> blockchain) {
		Block currentBlock;
		Block previousBlock;

		List<Block> blocks = new ArrayList<>();

		blockchain.forEach(block -> blocks.add(block));
		Collections.sort(blocks);

		for (int i = 1; i < blocks.size(); i++) {
			previousBlock = blocks.get(i - 1);
			currentBlock = blocks.get(i);
			if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
				return false;
			}
			if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
				return false;
			}
			if (!BlockchainUtils.isHashResolved(difficultLevel, currentBlock.getHash())) {
				return false;
			}
		}
		return true;
	}

}
