package org.byochain.api.controller;

import java.util.Set;

import org.byochain.model.entity.Block;
import org.byochain.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockController {

	@Autowired
	private BlockService blockService;
	
	@RequestMapping("/blocks")
    public Set<Block> blocks() {
        return blockService.getBlocks();
    }
}
