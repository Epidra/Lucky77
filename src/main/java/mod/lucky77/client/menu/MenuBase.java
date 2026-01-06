package mod.lucky77.client.menu;

import mod.lucky77.common.block.entity.BlockEntityBase;
import mod.lucky77.custom.logic.LogicBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.level.Level;

public abstract class MenuBase extends AbstractContainerMenu {
	
	public Container container;
	
	protected Level level;
	protected ContainerData data;
	protected BlockPos pos = new BlockPos(0, 0, 0);
	protected LogicBase logic; //  = new LogicBase();
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, BlockPos pos){
		this(type, windowID, playerInventory, pos, (BlockEntityBase) playerInventory.player.getCommandSenderWorld().getBlockEntity(pos));
		// this.pos = pos;
		// this.container = (BlockEntityBase) playerInventory.player.getCommandSenderWorld().getBlockEntity(pos);
		// this.level = playerInventory.player.level();
		// this.data = (BlockEntityBase) (BlockEntityBase) playerInventory.player.getCommandSenderWorld().getBlockEntity(pos).getDataToSync();
		// this.logic = tile.logic;
		// createInventory(container, playerInventory);
	}
	
	public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, BlockPos pos, BlockEntityBase blockEntity){
		this(type, windowID, playerInventory, pos, blockEntity, blockEntity.getDataToSync(), blockEntity.logic);
		// this.pos = pos;
		// this.container = blockEntity;
		// this.level = playerInventory.player.level();
		// this.data = blockEntity.getDataToSync();
		// this.logic = blockEntity.logic;
		// createInventory(container, playerInventory);
	}
	
	public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, BlockEntityBase blockEntity){
		this(type, windowID, playerInventory, BlockPos.ZERO, blockEntity, blockEntity.getDataToSync(), blockEntity.logic);
		// this.pos = pos;
		// this.container = blockEntity;
		// this.level = playerInventory.player.level();
		// this.data = blockEntity.getDataToSync();
		// this.logic = blockEntity.logic;
		// createInventory(container, playerInventory);
	}
	
	// public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, FriendlyByteBuf packetBuffer){
	// 	this(type, windowID, playerInventory, BlockPos.ZERO, new SimpleContainer(), new SimpleContainerData(1), new LogicBase());
	// 	// this.pos = pos;
	// 	// this.container = (BlockEntityBase) playerInventory.player.getCommandSenderWorld().getBlockEntity(pos);
	// 	// this.level = playerInventory.player.level();
	// 	// this.data = (BlockEntityBase) container.getDataToSync();
	// 	// this.logic = tile.logic;
	// 	// createInventory(container, playerInventory);
	// }
	
	// public MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, SimpleContainer simpleContainer){
	// 	this(type, windowID, playerInventory, BlockPos.ZERO, simpleContainer, new SimpleContainerData(1), new LogicBase());
	// 	// this.pos = pos;
	// 	// this.container = (BlockEntityBase) playerInventory.player.getCommandSenderWorld().getBlockEntity(pos);
	// 	// this.level = playerInventory.player.level();
	// 	// this.data = (BlockEntityBase) container.getDataToSync();
	// 	// this.logic = tile.logic;
	// 	// createInventory(container, playerInventory);
	// }
	
	/** Default Constructor **/
	private MenuBase(MenuType<?> type, int windowID, Inventory playerInventory, BlockPos pos, Container container, ContainerData data, LogicBase logic){
		super(type, windowID);
		this.pos = pos;
		this.container = container;
		this.data = data;
		this.logic = logic;
		createInventory(container, playerInventory);
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	protected abstract void createInventory(Container container, Inventory playerInventory);
	
	/** Adds slots for the player inventory at the default position **/
	protected void addPlayerSlots(Inventory playerInventory){
		addPlayerSlots(playerInventory, 8, 56);
	}
	
	/** Adds slots for the player inventory at a custom position **/
	protected void addPlayerSlots(Inventory playerInventory, int posX, int posY){
		
		// --- Slots for the hotbar --- //
		for(int row = 0; row < 9; ++row){
			int x = posX + row * 18;
			int y = posY + 86;
			addSlot(new Slot(playerInventory, row, x, y));
		}
		
		// --- Slots for the main inventory --- //
		for(int row = 1; row < 4; ++row){
			for(int col = 0; col < 9; ++col){
				int x = posX + col * 18;
				int y = row * 18 + posY + 10;
				addSlot(new Slot(playerInventory, col + row * 9, x, y));
			}
		}
	}
	
	// public SimpleContainer generateSimpleContainer(Inventory playerInventory, FriendlyByteBuf packetBuffer){
	// 	return new SimpleContainer();
	// }
	
	@Override
	public boolean stillValid(Player player){
		return this.container.stillValid(player);
	}
	
}
