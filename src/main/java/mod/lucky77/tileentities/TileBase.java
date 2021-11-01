package mod.lucky77.tileentities;

import mod.lucky77.container.ContainerBase;
import mod.lucky77.util.LogicBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public abstract class TileBase<T extends LogicBase> extends TileEntity implements ITickableTileEntity, IInventory {

    protected NonNullList<ItemStack> inventory;
    public T logic;





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public TileBase(TileEntityType<?> tileEntityTypeIn, int inventorySize) {
        this(tileEntityTypeIn, inventorySize, (T)new LogicBase());
    }

    public TileBase(TileEntityType<?> tileEntityTypeIn, int inventorySize, T logic) {
        super(tileEntityTypeIn);
        inventory = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
        this.logic = logic;
    }





    //----------------------------------------UPDATE----------------------------------------//

    // ...





    //----------------------------------------INVENTORY----------------------------------------//

    @Override
    public ItemStack getItem(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int val1, int val2) {
        return ItemStackHelper.removeItem(this.inventory, val1, val2);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ItemStackHelper.takeItem(this.inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemstack = this.inventory.get(slot);
        boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
    }

    public boolean canPlaceItem(int slot, ItemStack stack) {
        return true;
    }





    //----------------------------------------NETWORK----------------------------------------//

    //@Override
    //@Nullable
    //public SUpdateTileEntityPacket getUpdatePacket(){
    //    CompoundNBT nbtTagCompound = new CompoundNBT();
    //    save(nbtTagCompound);
    //    return new SUpdateTileEntityPacket(this.worldPosition, ShopKeeper.TILE_FOUNDRY.get().hashCode(), nbtTagCompound);
    //}

    /** Creates a tag containing the TileEntity information, used by vanilla to transmit from server to client */
    @Override
    public CompoundNBT getUpdateTag(){
        CompoundNBT nbtTagCompound = new CompoundNBT();
        save(nbtTagCompound);
        return nbtTagCompound;
    }

    /** Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client */
    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag){
        this.load(state, tag);
    }





    //----------------------------------------READ/WRITE----------------------------------------//

    public void load(BlockState state, CompoundNBT nbt){
        super.load(state, nbt);

        this.inventory = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.inventory);
    }

    public CompoundNBT save(CompoundNBT compound){
        super.save(compound);
        ItemStackHelper.saveAllItems(compound, this.inventory);
        return compound;
    }





    //----------------------------------------SUPPORT----------------------------------------//

    @Override
    public int getContainerSize() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.inventory) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clearContent() {
        this.inventory.clear();
    }

    public abstract IIntArray getIntArray();

    public abstract ITextComponent getName();



}
