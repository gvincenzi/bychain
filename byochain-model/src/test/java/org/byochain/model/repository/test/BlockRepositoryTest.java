package org.byochain.model.repository.test;

import java.util.LinkedHashSet;
import java.util.Set;

import org.byochain.model.AppModel;
import org.byochain.model.entity.Block;
import org.byochain.model.repository.BlockRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JUnit Test
 * 
 * @author Giuseppe Vincenzi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppModel.class })
@ActiveProfiles("test")
public class BlockRepositoryTest {
	@Autowired
	private BlockRepository serviceUnderTest;

	private Set<Block> blocks = new LinkedHashSet<>();

	@Before
	public void init() {
		serviceUnderTest.deleteAll();

		Block block1 = new Block("Genesis block", "0");
		block1.setHash("HASH1");
		Block block2 = new Block("Block#2", block1.getHash());
		block2.setHash("HASH2");
		Block block3 = new Block("Block#3", block2.getHash());
		block3.setHash("HASH3");

		blocks.add(block1);
		blocks.add(block2);
		blocks.add(block3);

		serviceUnderTest.save(blocks);
	}

	@Test
	public void count() {
		Assert.assertEquals(blocks.size(), serviceUnderTest.count());
	}

	@Test
	public void findById() {
		for (Block block : blocks) {
			Assert.assertEquals(block, serviceUnderTest.findOne(block.getId()));
		}

	}

	@Test
	public void findByHash() {
		for (Block block : blocks) {
			Assert.assertEquals(block, serviceUnderTest.find(block.getHash()));
		}
	}

	@Test
	public void findLast() {
		Assert.assertEquals("HASH3", serviceUnderTest.findLast().getHash());
	}
}
