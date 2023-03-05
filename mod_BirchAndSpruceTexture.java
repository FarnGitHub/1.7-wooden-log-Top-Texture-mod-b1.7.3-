package net.minecraft.src;

import net.minecraft.client.Minecraft;

import java.lang.reflect.Field;

import java.util.Arrays;

public class mod_BirchAndSpruceTexture extends BaseMod {
	Minecraft mc;
	public static int SpruceTopTexture = ModLoader.addOverride("/terrain.png", "/FarnMod/log_spruce_top.png");;
	public static int BirchTopTexture = ModLoader.addOverride("/terrain.png", "/FarnMod/log_birch_top.png");

	public String Version() {
		return "2.2";
	}

	public String Name() {
		return "Farn's Birch And Spruce Top texture";
	}
	
	public String Description() {
		return "Add more wooden log top Texture";
	}

	public mod_BirchAndSpruceTexture() {
		this.OverrideBlock();		
	}

	private void OverrideBlock() {
		Block.blocksList[Block.wood.blockID] = null;
		Block wood = (new BlockLogProxy(Block.wood.blockID)).setBlockName("log").setHardness(2.0F).setStepSound(Block.soundWoodFootstep).disableNeighborNotifyOnMetadataChange();
		final Field blocksEffectiveAgainst = this.getField(ItemTool.class, "blocksEffectiveAgainst", "bk");
		if (blocksEffectiveAgainst != null && ToolEffective) {
			for(Item item : Item.itemsList) {
				if(item instanceof ItemAxe) {
					try {
						Block originalBlocks[] = (Block[])blocksEffectiveAgainst.get(item);

						Block newBlocks[] = Arrays.copyOf(originalBlocks, originalBlocks.length + 1);
						newBlocks[originalBlocks.length] = Block.blocksList[Block.wood.blockID];
						blocksEffectiveAgainst.set(item, newBlocks);
					} catch (IllegalAccessException e1) { 
						e1.printStackTrace(); 
					}
				}
			}
		}
	}

	private static final Field getField(Class<?> target, String ...names) {
		for (Field field : target.getDeclaredFields()) {
			for (String name : names) {
				if (field.getName() == name) {
					field.setAccessible(true);
					return field;
				}
			}
		}
		return null;
	}

	@MLProp(name = "Fix Tool Effective", info = "Fix axe not being effective against this mod 's wooden log")
	public static boolean ToolEffective = true;
}
