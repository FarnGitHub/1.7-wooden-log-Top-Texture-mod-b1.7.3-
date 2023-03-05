package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class BlockLogProxy extends BlockLog {
	private mod_BirchAndSpruceTexture customTexture;

	public BlockLogProxy(int id) {
		super(id);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		if(side == 0 || side == 1) {
			if(metadata == 1) {
				return customTexture.SpruceTopTexture;
			} else if(metadata == 2) {
				return customTexture.BirchTopTexture;
			} else {
				return 21;
			}
		} else {
			if(metadata == 1) {
				return 116;
			} else if(metadata == 2) {
				return 117;
			} else {
				return 20;
			}
		}
	}    
}