package mod.lucky77.menu;

import mod.lucky77.block.entity.BlockEntityBase;
import mod.lucky77.util.content.Dummy;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public abstract class MenuBase extends AbstractContainerMenu {
	
	public Container container;
	protected Level level;
	protected ContainerData data;
	protected BlockPos pos = new BlockPos(0, 0, 0);
	protected Dummy logic = new Dummy();
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, BlockPos pos) {
		this(type, windowID, playerInventory, (BlockEntityBase) playerInventory.player.getCommandSenderWorld().getBlockEntity(pos));
		this.pos = pos;
	}
	
	public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, BlockEntityBase tile) {
		super(type, windowID);
		this.container = tile;
		this.level = playerInventory.player.level();
		this.data = tile.getDataToSync();
		this.logic = tile.logic;
		createInventory(tile, playerInventory);
	}
	
	public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, FriendlyByteBuf packetBuffer) {
		super(type, windowID);
		this.container = generateSimpleContainer(playerInventory, packetBuffer);
		this.level = playerInventory.player.level();
		this.data = new SimpleContainerData(1);
		createInventory(container, playerInventory);
	}
	
	public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, SimpleContainer simpleContainer) {
		super(type, windowID);
		this.container = simpleContainer;
		this.level = playerInventory.player.level();
		this.data = new SimpleContainerData(1);
		createInventory(container, playerInventory);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	protected abstract void createInventory(Container tile, Inventory playerInventory);
	
	/** Adds Slots from Player Inventory at the default Position **/
	protected void addPlayerSlots(Inventory playerInventory) {
		addPlayerSlots(playerInventory, 8, 56);
	}
	
	/** Adds Slots from Player Inventory at a specific Position **/
	protected void addPlayerSlots(Inventory playerInventory, int inX, int inY) {
		
		// --- Slots for the hotbar --- //
		for (int row = 0; row < 9; ++ row) {
			int x = inX + row * 18;
			int y = inY + 86;
			addSlot(new Slot(playerInventory, row, x, y));
		}
		
		// --- Slots for the main inventory --- //
		for (int row = 1; row < 4; ++ row) {
			for (int col = 0; col < 9; ++ col) {
				int x = inX + col * 18;
				int y = row * 18 + (inY + 10);
				addSlot(new Slot(playerInventory, col + row * 9, x, y));
			}
		}
	}
	
	public SimpleContainer generateSimpleContainer(Inventory playerInventory, FriendlyByteBuf packetBuffer){
		return new SimpleContainer();
	}
	
	@Override
	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}
	
	
	
}
