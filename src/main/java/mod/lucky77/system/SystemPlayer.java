package mod.lucky77.system;

import mod.lucky77.item.ItemBook;
import mod.lucky77.screen.ScreenBook;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class SystemPlayer {

    // ...




    //----------------------------------------INVENTORY----------------------------------------//
    
    public static void openBookScreen(ItemBook book){
        Minecraft.getInstance().setScreen(new ScreenBook(book));
    }
    
    
    
    
    
    //----------------------------------------INVENTORY----------------------------------------//

    public static boolean decreaseInventory(Inventory inv, ItemStack item, int amount){
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
