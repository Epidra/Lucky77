package mod.lucky77.util.system;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("unused")
public class SystemInventory {
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INVENTORY  ---------- ---------- ---------- ---------- //
	
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
