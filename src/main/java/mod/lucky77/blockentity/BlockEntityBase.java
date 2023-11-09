package mod.lucky77.blockentity;

import mod.lucky77.util.Dummy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityBase<T extends Dummy> extends BlockEntity implements Container {

    protected NonNullList<ItemStack> inventory;
    public T logic;





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public BlockEntityBase(BlockEntityType<?> tileEntityTypeIn, BlockPos blockpos, BlockState blockstate, int inventorySize) {
        this(tileEntityTypeIn, blockpos, blockstate, inventorySize, (T)new Dummy());
    }

    public BlockEntityBase(BlockEntityType<?> tileEntityTypeIn, BlockPos blockpos, BlockState blockstate, int inventorySize, T logic) {
        super(tileEntityTypeIn, blockpos, blockstate);
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
        return ContainerHelper.removeItem(this.inventory, val1, val2);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemstack = this.inventory.get(slot);
        boolean flag = !stack.isEmpty() && stack.isSameItemSameTags(itemstack, stack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
    }

    public boolean canPlaceItem(int slot, ItemStack stack) {
        return true;
    }





    //----------------------------------------NETWORK----------------------------------------//

    /* This has to be copied over to the actual Entity implementation
     * Is now obsolete
    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket(){
        CompoundNBT nbtTagCompound = new CompoundNBT();
        save(nbtTagCompound);
        return new SUpdateTileEntityPacket(this.worldPosition, ShopKeeper.TILE_FOUNDRY.get().hashCode(), nbtTagCompound);
    }
    */





    //----------------------------------------NETWORK----------------------------------------//

    /** Creates a tag containing the TileEntity information, used by vanilla to transmit from server to client
     * @return*/
    @Override
    public CompoundTag getUpdateTag(){
        CompoundTag nbtTagCompound = new CompoundTag();
        saveAdditional(nbtTagCompound);
        return nbtTagCompound;
    }

    /** Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client */
    @Override
    public void handleUpdateTag(CompoundTag tag){
        this.load(tag);
    }





    //----------------------------------------READ/WRITE----------------------------------------//

    /** Loads the basic Information about this BlockEntity from Disc */
    public void load(CompoundTag nbt){
        super.load(nbt);
        this.inventory = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
    }

    /** Saves the basic Information about this BlockEntity to Disc */
    public void saveAdditional(CompoundTag compound){
        super.saveAdditional(compound);
        ContainerHelper.saveAllItems(compound, this.inventory);
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
    public boolean stillValid(Player player) {
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

    public abstract ContainerData getIntArray();

    // public abstract TextComponent getName();



}
