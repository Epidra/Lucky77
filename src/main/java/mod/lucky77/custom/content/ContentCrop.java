package mod.lucky77.custom.content;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public record ContentCrop(String id, Block crop, Item seed, Item drop) {

}
