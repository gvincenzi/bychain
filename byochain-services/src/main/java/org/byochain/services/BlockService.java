package org.byochain.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
		Set<Block> blocks = new TreeSet<>();
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
		block.setHash(calculateHash(block));
		while (!isHashResolved(block,difficultLevel)) {
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
		return BlockchainUtils.calculateHash(block.getPreviousHash(), block.getTimestamp().getTimeInMillis(), block.getToken(), block.getData());
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
			if (!currentBlock.getHash().equals(calculateHash(currentBlock))) {
				return false;
			}
			if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
				return false;
			}
			if (!isHashResolved(currentBlock,difficultLevel)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Block getBlockByHash(String hash) {
		return blockRepository.find(hash);
	}

	@Override
	public Block addBlock(String data) {
		Block previousBlock = blockRepository.findLast();
		Block newBlock = mineBlock(data, previousBlock);
		return blockRepository.save(newBlock);
	}
}
