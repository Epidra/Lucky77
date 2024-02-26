package mod.lucky77.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ItemFood extends Item {

    // ...





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Default Constructor */
    public ItemFood(int hunger, float saturation, boolean isMeat){
        super(isMeat ? new Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(64).food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).meat().build())
                     : new Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(64).food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build()));
    }





    //----------------------------------------SUPPORT----------------------------------------//

    // ...



}