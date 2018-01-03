package org.byochain.services.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.byochain.model.entity.Block;
import org.byochain.model.entity.User;
import org.byochain.model.repository.BlockRepository;
import org.byochain.services.AppServices;
import org.byochain.services.exception.ByoChainServiceException;
import org.byochain.services.service.impl.BlockService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JUnit Test
 * 
 * @author Giuseppe Vincenzi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AppServices.class })
@ActiveProfiles("test")
public class BlockServiceTest {
	@Autowired
	BlockService serviceUnderTest;
	
	@MockBean
	BlockRepository blockRepositoryMock;
	
	@Before
	public void init(){
		Mockito.when(blockRepositoryMock.findAll())
	      .thenReturn(new Iterable<Block>() {
			
			@Override
			public Iterator<Block> iterator() {
				return new HashSet<Block>().iterator();
			}
		});
	}
	
	@Test(expected = ByoChainServiceException.class)
	public void testException() throws ByoChainServiceException{
		serviceUnderTest.mineBlock("", null, getUserMock());
	}
	
	@Test(expected = ByoChainServiceException.class)
	public void testMinerException() throws ByoChainServiceException{
		serviceUnderTest.mineBlock("Genesis block", null, null);
	}
	
	@Test
	public void test() throws ByoChainServiceException {
		Set<Block> blockchain = new HashSet<>();
		
		long now = System.currentTimeMillis();
		Block block = serviceUnderTest.mineBlock("Genesis block", null, getUserMock());
		System.out.println("Block mined in "+ (System.currentTimeMillis()-now) + "ms >> " + block);
		
		now = System.currentTimeMillis();
		Block block1 = serviceUnderTest.mineBlock("Block 1", block, getUserMock());
		System.out.println("Block mined in "+ (System.currentTimeMillis()-now) + "ms >> " + block1);
		
		now = System.currentTimeMillis();
		Block block2 = serviceUnderTest.mineBlock("Block 2", block1, getUserMock());
		System.out.println("Block mined in "+ (System.currentTimeMillis()-now) + "ms >> " + block2);
		
		blockchain.add(block);
		blockchain.add(block1);
		blockchain.add(block2);
		
		System.out.println(serviceUnderTest.validateChain(blockchain, getUserMock())?"Blockchain validated":"Blockchain invalid");
	}
	
	@Test
	public void calculateHashTest() throws Exception{
		Block block = new Block("9cfc43133eef5fcd363e144ca84b1a970584490fffbf3f412d6e014a0b47bfc4","Test block GVI 3", getUserMock());
		Random random = new Random(block.getTimestamp().getTimeInMillis());
		int nonce = Math.abs(random.nextInt());
		block.setNonce(nonce);
		
		String hash = serviceUnderTest.calculateHash(block);
		System.out.println(hash);
		for(int i=0; i<100; i++)
		{
			String newHash = serviceUnderTest.calculateHash(block);
			Assert.assertTrue(newHash.equals(hash));
		}
	}
	
	private User getUserMock(){
		User user = new User();
		user.setUserId(10L);
		return user;
	}
}
