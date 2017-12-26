package org.byochain.commons.utils.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.byochain.commons.utils.BlockchainUtils;
import org.junit.Test;
import org.springframework.util.Assert;

public class BlockchainUtilsTest {
	
	@Test
	public void calculateHashTest() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "26-12-2017 18:17:24";
		Date date = sdf.parse(dateInString);
		String hash = BlockchainUtils.calculateHash("9cfc43133eef5fcd363e144ca84b1a970584490fffbf3f412d6e014a0b47bfc4", date.getTime(), 3, "Test block GVI 3");
		System.out.println(hash);
		for(int i=0; i<100; i++)
		{
			String newHash = BlockchainUtils.calculateHash("9cfc43133eef5fcd363e144ca84b1a970584490fffbf3f412d6e014a0b47bfc4", date.getTime(), 3, "Test block GVI 3");
			Assert.isTrue(hash.equals(newHash), "Hashing error");
		}
	}
}
