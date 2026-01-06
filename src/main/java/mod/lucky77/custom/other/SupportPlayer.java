package mod.lucky77.custom.other;

import mod.lucky77.client.screen.ScreenBook;
import mod.lucky77.common.item.ItemBook;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class SupportPlayer {
	
	// ...
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public static void openBookScreen(ItemBook book){
		Minecraft.getInstance().setScreen(new ScreenBook(book));
	}
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public static boolean dcreaseInventory(Inventory inv, ItemStack stack, int amount){
		boolean hasDecreased = false;
		int amountLeft = amount;
		if(stack.isEmpty() || amount <= 0){
			return false;
		}
		for(int j = 0; j < inv.getContainerSize(); ++j){
			if(amountLeft > 0){
				ItemStack currentStack = inv.getItem(j);
				if(stack.getItem() == currentStack.getItem()){
					hasDecreased = true;
					int count = currentStack.getCount();
					if(amountLeft - count >= 0){
						inv.setItem(j, ItemStack.EMPTY);
						amountLeft -= count;
					} else {
						currentStack.shrink(amountLeft);
						if(currentStack.isEmpty()){
							inv.setItem(j, ItemStack.EMPTY);
						}
						amountLeft = 0;
					}
				}
			}
		}
		return hasDecreased;
	}
	
}
