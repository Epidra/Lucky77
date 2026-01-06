package mod.lucky77.common.block.entity;

import mod.lucky77.custom.logic.LogicBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityBase<T extends LogicBase> extends BlockEntity implements Container {
	
	public T logic;
	
	protected NonNullList<ItemStack> inventory;
	protected ContainerData data = null;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Constructor with dummied out logic **/
	// public BlockEntityBase(BlockEntityType<?> type, BlockPos pos, BlockState state, int inventorySize){
	// 	this(type, pos, state, inventorySize, (T)new LogicBase());
	// }
	
	/** Default Constructor **/
	public BlockEntityBase(BlockEntityType<?> type, BlockPos pos, BlockState state, int inventorySize, T logic){
		super(type, pos, state);
		inventory = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
		this.logic = logic;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SERVER TICK     -------- -------- -------- --------   //
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     SAVE / LOAD     -------- -------- -------- --------   //
	
	/** Load the basic Information about this BlockEntity from Disk **/
	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries){
		super.loadAdditional(tag, registries);
		this.inventory = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, this.inventory, registries);
		logic.load(tag.getString("Content"));
	}
	
	/** Saves the basic Information about this BlockEntity to Disk **/
	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries){
		super.saveAdditional(tag, registries);
		ContainerHelper.saveAllItems(tag, this.inventory, registries);
		tag.putString("Content", logic.save());
	}
	
	
	
	
	
	//   -------- -------- -------- --------     NETWORK     -------- -------- -------- --------   //
	
	/** Creates a tag containing the BlockEntity information, used by vanilla to transmit from server to client **/
	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries){
		CompoundTag tag = new CompoundTag();
		saveAdditional(tag, registries);
		return tag;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     INVENTORY     -------- -------- -------- --------   //
	
	/** Return the stack in the given slot **/
	@Override
	public ItemStack getItem(int slot){
		return this.inventory.get(slot);
	}
	
	/** Removes up to a specific number of items from an inventory slot and returns them in a new stack **/
	@Override
	public ItemStack removeItem(int slot, int amount){
		return ContainerHelper.removeItem(this.inventory, slot , amount);
	}
	
	/** Removes a itemstack from the given slot and returns it **/
	@Override
	public ItemStack removeItemNoUpdate(int slot){
		return ContainerHelper.takeItem(this.inventory, slot);
	}
	
	/** Sets the given itemstack to the specified slot in the inventory **/
	@Override
	public void setItem(int slot, ItemStack stack){
		ItemStack itemstack = this.inventory.get(slot);
		boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameComponents(itemstack, stack);
		this.inventory.set(slot, stack);
		if(stack.getCount() > this.getMaxStackSize()){
			stack.setCount(this.getMaxStackSize());
		}
		// setItemAdditional(slot, stack, flag);
		// lead to an empty custom class
	}
	
	/** Toggles automatic Inventory change through other blocks **/
	@Override
	public boolean canPlaceItem(int slot, ItemStack stack){
		return true;
		
		// CasinoCraft should set this off
		// GUIs use Slot.isItemValid
		
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Return the size of the Inventory **/
	@Override
	public int getContainerSize(){
		return this.inventory.size();
	}
	
	/** Checks every Slot if it is empty or not **/
	@Override
	public boolean isEmpty(){
		for(ItemStack stack : this.inventory){
			if(!stack.isEmpty()){
				return false;
			}
		}
		return true;
	}
	
	/** ??? **/
	@Override
	public boolean stillValid(Player player){
		if(this.level.getBlockEntity(this.worldPosition) != this){
			return false;
		} else {
			return player.distanceToSqr( (double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}
	
	@Override
	public void clearContent(){
		this.inventory.clear();
	}
	
	public ContainerData getDataToSync(){
		return data;
	}
	
	
	
}
