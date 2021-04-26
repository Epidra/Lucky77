package mod.lucky77.crafting;

import mod.lucky77.tileentities.TileBase;
import net.minecraft.item.crafting.IRecipe;

public abstract class RecipeBase implements IRecipe<TileBase> {

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
