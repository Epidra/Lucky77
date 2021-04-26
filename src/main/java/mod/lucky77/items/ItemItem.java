package mod.lucky77.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemItem extends Item {

    // ...




    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Default Constructor */
    public ItemItem(ItemGroup group){
        super(new Properties().tab(group));
    }

}
