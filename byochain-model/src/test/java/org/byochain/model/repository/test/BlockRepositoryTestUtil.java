package org.byochain.model.repository.test;

import java.util.Calendar;

import org.byochain.model.entity.Block;

public class BlockRepositoryTestUtil {
	public static Block getBlockMock(Block previous)
	{
		Block block = new Block();
		block.setData("data");
		block.setPreviousHash(previous!=null?previous.getHash():"genesis");
		block.setTimestamp(Calendar.getInstance());
		block.setHash("block:"+block.hashCode());
		return block;
	}
}
