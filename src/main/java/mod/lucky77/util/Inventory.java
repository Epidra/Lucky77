package mod.lucky77.util;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class Inventory {

    public static boolean decreaseInventory(PlayerInventory inv, ItemStack item, int amount){
        boolean decreased = false;

        int leftAmount = amount;

        if(item.isEmpty() || amount <= 0){
            return false;
        }

        for(int j = 0; j < inv.getContainerSize(); ++j) {
            if(leftAmount > 0){
                ItemStack itemstack = inv.getItem(j);
                if (item.getItem() == itemstack.getItem()) {
                    decreased = true;
                    int count = itemstack.getCount();
                    if(leftAmount - count >= 0){
                        inv.setItem(j, ItemStack.EMPTY);
                        leftAmount -= count;
                    } else {
                        itemstack.shrink(leftAmount);
                        if(itemstack.isEmpty()){
                            inv.setItem(j, ItemStack.EMPTY);
                        }
                        leftAmount = 0;
                    }
                }
            }
        }

        return decreased;
    }

}
