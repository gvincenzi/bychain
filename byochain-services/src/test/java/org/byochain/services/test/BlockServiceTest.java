package org.byochain.services.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.byochain.model.entity.Block;
import org.byochain.model.repository.BlockRepository;
import org.byochain.services.AppServices;
import org.byochain.services.exception.ByoChainServiceException;
import org.byochain.services.service.IBlockService;
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
	IBlockService serviceUnderTest;
	
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
		serviceUnderTest.mineBlock("", null);
	}
	
	@Test
	public void test() throws ByoChainServiceException {
		Set<Block> blockchain = new HashSet<>();
		
		long now = System.currentTimeMillis();
		Block block = serviceUnderTest.mineBlock("Genesis block", null);
		System.out.println("Block mined in "+ (System.currentTimeMillis()-now) + "ms >> " + block);
		
		now = System.currentTimeMillis();
		Block block1 = serviceUnderTest.mineBlock("Block 1", block);
		System.out.println("Block mined in "+ (System.currentTimeMillis()-now) + "ms >> " + block1);
		
		now = System.currentTimeMillis();
		Block block2 = serviceUnderTest.mineBlock("Block 2", block1);
		System.out.println("Block mined in "+ (System.currentTimeMillis()-now) + "ms >> " + block2);
		
		blockchain.add(block);
		blockchain.add(block1);
		blockchain.add(block2);
		
		System.out.println(serviceUnderTest.validateChain(blockchain)?"Blockchain validated":"Blockchain invalid");
	}
}
