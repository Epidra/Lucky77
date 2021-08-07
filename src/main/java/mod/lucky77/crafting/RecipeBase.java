package mod.lucky77.crafting;

import mod.lucky77.blockentity.BlockEntityBase;
import net.minecraft.world.item.crafting.Recipe;

public abstract class RecipeBase implements Recipe<BlockEntityBase> {

    public RecipeBase(int cooktime){
        cookTime = cooktime;
    }

    protected final int cookTime;

    /**
     * Gets the cook time in ticks
     */
    public int getCookTime() {
        return this.cookTime;
    }
}
