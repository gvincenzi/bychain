package org.byochain.api.controller;

import java.util.Locale;

import org.byochain.api.enumeration.ByoChainApiExceptionEnum;
import org.byochain.api.enumeration.ByoChainApiResponseEnum;
import org.byochain.api.request.BlockCreationRequest;
import org.byochain.api.response.BlockChainApiResponse;
import org.byochain.commons.exception.ByoChainException;
import org.byochain.model.entity.Block;
import org.byochain.services.service.impl.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController : BlockController
 * @author Giuseppe Vincenzi
 *
 */
@RestController
@RequestMapping("/byochain")
public class BlockController extends ByoChainController {

	@Autowired
	private BlockService blockService;

	@RequestMapping(value = "/blocks", method = RequestMethod.GET)
	public BlockChainApiResponse blocks(Pageable pageable, Locale locale) {
		Page<Block> blocks = blockService.getBlocks(pageable);
		BlockChainApiResponse response = ByoChainApiResponseEnum.BLOCK_CONTROLLER_OK.getResponse(messageSource, locale);
		response.setData(blocks);
		return response;
	}

	@RequestMapping(value = "/blocks/{hash}", method = RequestMethod.GET)
	public BlockChainApiResponse blockByHash(@PathVariable("hash") String hash, Locale locale)
			throws ByoChainException {
		Block block = blockService.getBlockByHash(hash);
		BlockChainApiResponse response = null;
		if (block != null) {
			response = ByoChainApiResponseEnum.BLOCK_CONTROLLER_OK.getResponse(messageSource,
					locale);
			response.setData(block);
		} else {
			throw ByoChainApiExceptionEnum.BLOCK_CONTROLLER_HASH_NOT_EXIST.getExceptionAfterServiceCall(messageSource, locale, hash);
		}
		return response;
	}

	@RequestMapping(value = "/blocks", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public BlockChainApiResponse addBlock(@RequestBody BlockCreationRequest request, Locale locale)
			throws ByoChainException {
		if (request == null || request.getData() == null || request.getData().isEmpty()) {
			throw ByoChainApiExceptionEnum.BLOCK_CONTROLLER_DATA_MANDATORY.getExceptionBeforeServiceCall(messageSource, locale);
		}
		Block block = blockService.addBlock(request.getData());
		BlockChainApiResponse response = ByoChainApiResponseEnum.BLOCK_CONTROLLER_OK.getResponse(messageSource, locale);
		response.setData(block);
		return response;
	}

	@RequestMapping(value = "/blocks/validate", method = RequestMethod.GET)
	public BlockChainApiResponse validate(Locale locale) throws ByoChainException {
		Boolean validation = blockService.validateChain(blockService.getAllBlocks());
		BlockChainApiResponse response = null;
		if (validation) {
			response = ByoChainApiResponseEnum.BLOCK_CONTROLLER_VALIDATION_OK.getResponse(messageSource, locale);
		} else {
			throw ByoChainApiExceptionEnum.BLOCK_CONTROLLER_VALIDATION_KO.getExceptionAfterServiceCall(messageSource, locale);
		}
		return response;
	}
}
