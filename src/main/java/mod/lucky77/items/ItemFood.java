package mod.lucky77.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemFood extends Item {

    // ...





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Default Constructor */
    public ItemFood(int hunger, float saturation, boolean isMeat){
        super(isMeat ? new Properties().tab(ItemGroup.TAB_FOOD).stacksTo(64).food(new Food.Builder().nutrition(hunger).saturationMod(saturation).meat().build())
                     : new Properties().tab(ItemGroup.TAB_FOOD).stacksTo(64).food(new Food.Builder().nutrition(hunger).saturationMod(saturation).build()));
    }





    //----------------------------------------SUPPORT----------------------------------------//

    // ...



}