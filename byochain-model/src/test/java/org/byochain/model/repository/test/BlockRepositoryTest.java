package org.byochain.model.repository.test;

import java.util.HashSet;
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

	private static Set<Block> blocks = new HashSet<>();

	@Before
	public void init() {
		Block block1 = BlockRepositoryTestUtil.getBlockMock(null);
		Block block2 = BlockRepositoryTestUtil.getBlockMock(block1);

		if (blocks.isEmpty()) {
			blocks.add(serviceUnderTest.save(block1));
			blocks.add(serviceUnderTest.save(block2));
		}
	}

	@Test
	public void count() {
		Assert.assertEquals("Assert count", blocks.size(), serviceUnderTest.count());
	}

	@Test
	public void find() {
		for (Block block : blocks) {
			Assert.assertEquals("Find by id", block, serviceUnderTest.findOne(block.getId()));
		}

	}
}
