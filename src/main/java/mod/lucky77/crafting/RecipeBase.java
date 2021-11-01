package mod.lucky77.crafting;

import mod.lucky77.tileentities.TileBase;
import net.minecraft.item.crafting.IRecipe;

public abstract class RecipeBase implements IRecipe<TileBase> {

    protected final int cookTime;





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public RecipeBase(int cooktime){
        cookTime = cooktime;
    }





    //----------------------------------------SUPPORT----------------------------------------//

    /** Gets the cook time in ticks */
    public int getCookTime() {
        return this.cookTime;
    }



}
