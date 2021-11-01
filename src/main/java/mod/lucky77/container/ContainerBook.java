package mod.lucky77.container;

import mod.lucky77.tileentities.TileBase;
import mod.lucky77.util.LogicBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ContainerBook extends Container {

    public IInventory inventory;
    protected World world;
    protected IIntArray data;
    protected BlockPos pos = new BlockPos(0, 0, 0);
    protected LogicBase logic;





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public ContainerBook(ContainerType<?> type, int windowID, PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        this(type, windowID, playerInventory, BlockPos.of(packetBuffer.readLong()));
    }

    public ContainerBook(ContainerType<?> type, int windowID, PlayerInventory playerInventory, BlockPos pos) {
        this(type, windowID, playerInventory, (TileBase) playerInventory.player.getCommandSenderWorld().getBlockEntity(pos));
        this.pos = pos;
    }

    public ContainerBook(ContainerType<?> type, int windowID, PlayerInventory playerInventory, TileBase tile) {
        super(type, windowID);
        this.inventory = tile;
        this.world = playerInventory.player.level;
        this.data = tile.getIntArray();
        this.logic = tile.logic;
        createInventory(tile, playerInventory);
    }





    //----------------------------------------SUPPORT----------------------------------------//

    protected abstract void createInventory(TileBase tile, PlayerInventory playerInventory);

    /** Adds Slots from Player Inventory at the default Position **/
    protected void addPlayerSlots(PlayerInventory playerInventory) {
        addPlayerSlots(playerInventory, 8, 56);
    }

    /** Adds Slots from Player Inventory at a specific Position **/
    protected void addPlayerSlots(PlayerInventory playerInventory, int inX, int inY) {
        // Slots for the hotbar
        for (int row = 0; row < 9; ++ row) {
            int x = inX + row * 18;
            int y = inY + 86;
            addSlot(new Slot(playerInventory, row, x, y));
        }
        // Slots for the main inventory
        for (int row = 1; row < 4; ++ row) {
            for (int col = 0; col < 9; ++ col) {
                int x = inX + col * 18;
                int y = row * 18 + (inY + 10);
                addSlot(new Slot(playerInventory, col + row * 9, x, y));
            }
        }
    }

    public LogicBase logicbase(){
        return logic;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.inventory.stillValid(player);
    }



}
