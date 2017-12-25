package org.byochain.services;

import java.util.Set;

import org.byochain.model.entity.Block;

/**
 * IBlockService
 * @author Giuseppe Vincenzi
 *
 */
public interface IBlockService {

	Set<Block> getBlocks();
}
