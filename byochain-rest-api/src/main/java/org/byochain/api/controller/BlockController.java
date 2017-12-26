package org.byochain.api.controller;

import java.util.Set;

import org.byochain.api.request.BlockCreationRequest;
import org.byochain.api.response.BlockChainValidationResponse;
import org.byochain.model.entity.Block;
import org.byochain.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/byochain")
public class BlockController {

	@Autowired
	private BlockService blockService;
	
	@RequestMapping(value="/blocks", method = RequestMethod.GET)
    public Set<Block> blocks() {
        return blockService.getBlocks();
    }
	
	@RequestMapping(value="/blocks/{hash}", method = RequestMethod.GET)
    public Block blockByHash(@PathVariable("hash") String hash) {
        return blockService.getBlockByHash(hash);
    }
	
	@RequestMapping(value="/blocks", method = RequestMethod.PUT)
    public Block addBlock(@RequestBody BlockCreationRequest request) {
		return blockService.addBlock(request.getData());
    }
	
	@RequestMapping(value="/validate", method = RequestMethod.PUT)
    public BlockChainValidationResponse validate() {
		BlockChainValidationResponse response = new BlockChainValidationResponse();
		response.setResult(blockService.validateChain(blockService.getBlocks()));
		response.setMessage(response.getResult()?"The BlockChain has been succesfully validated":"The Blockchain is not valid");
		return response;
    }
}
