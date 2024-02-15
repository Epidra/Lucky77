package mod.lucky77.item;

import mod.lucky77.register.RegisterSeed;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class ItemSeed extends Item {
	
	private String crop;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	/** Default Constructor */
	public ItemSeed(String cropID) {
		super(new Properties());
		crop = cropID;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	/** Called when this item is used when targetting a Block */
	public InteractionResult useOn(UseOnContext context) {
		InteractionResult actionresulttype = this.place(new BlockPlaceContext(context));
		return !actionresulttype.consumesAction() && this.isEdible() ? this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult() : actionresulttype;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	public InteractionResult place(BlockPlaceContext context) {
		if (!context.canPlace()) {
			return InteractionResult.FAIL;
		} else {
			BlockPlaceContext blockplacecontext = this.updatePlacementContext(context);
			if (blockplacecontext == null) {
				return InteractionResult.FAIL;
			} else {
				BlockState blockstate = this.getPlacementState(blockplacecontext);
				if (blockstate == null) {
					return InteractionResult.FAIL;
				} else if (!this.placeBlock(blockplacecontext, blockstate)) {
					return InteractionResult.FAIL;
				} else {
					BlockPos blockpos   = blockplacecontext.getClickedPos();
					Level level         = blockplacecontext.getLevel();
					Player player       = blockplacecontext.getPlayer();
					ItemStack itemstack = blockplacecontext.getItemInHand();
					BlockState blockstate1 = level.getBlockState(blockpos);
					if (blockstate1.is(blockstate.getBlock())) {
						blockstate1 = this.updateBlockStateFromTag(blockpos, level, itemstack, blockstate1);
						this.updateCustomBlockEntityTag(blockpos, level, player, itemstack, blockstate1);
						blockstate1.getBlock().setPlacedBy(level, blockpos, blockstate1, player, itemstack);
						if (player instanceof ServerPlayer) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
						}
					}
					level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
					SoundType soundtype = blockstate1.getSoundType(level, blockpos, context.getPlayer());
					level.playSound(player, blockpos, this.getPlaceSound(blockstate1, level, blockpos, context.getPlayer()), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					if (player == null || !player.getAbilities().instabuild) {
						itemstack.shrink(1);
					}
					return InteractionResult.sidedSuccess(level.isClientSide);
				}
			}
		}
	}
	
	@Deprecated //Forge: Use more sensitive version {@link BlockItem#getPlaceSound(BlockState, IBlockReader, BlockPos, Entity) }
	protected SoundEvent getPlaceSound(BlockState p_40588_) {
		return p_40588_.getSoundType().getPlaceSound();
	}
	
	//Forge: Sensitive version of BlockItem#getPlaceSound
	protected SoundEvent getPlaceSound(BlockState state, Level world, BlockPos pos, Player entity) {
		return state.getSoundType(world, pos, entity).getPlaceSound();
	}
	
	@Nullable
	public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
		return context;
	}
	
	protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, @Nullable Player player, ItemStack itemStack, BlockState state) {
		return updateCustomBlockEntityTag(level, player, pos, itemStack);
	}
	
	@Nullable
	protected BlockState getPlacementState(BlockPlaceContext context) {
		BlockState blockstate = this.getBlock().getStateForPlacement(context);
		return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
	}
	
	protected boolean canPlace(BlockPlaceContext context, BlockState state) {
		Player player = context.getPlayer();
		CollisionContext collisioncontext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
		return (!this.mustSurvive() || state.canSurvive(context.getLevel(), context.getClickedPos())) && context.getLevel().isUnobstructed(state, context.getClickedPos(), collisioncontext);
	}
	
	protected boolean mustSurvive() {
		return true;
	}
	
	private BlockState updateBlockStateFromTag(BlockPos pos, Level level, ItemStack itemStack, BlockState state) {
		BlockState blockstate = state;
		CompoundTag compoundtag = itemStack.getTag();
		if (compoundtag != null) {
			CompoundTag compoundtag1 = compoundtag.getCompound("BlockStateTag");
			StateDefinition<Block, BlockState> statedefinition = state.getBlock().getStateDefinition();
			
			for(String s : compoundtag1.getAllKeys()) {
				Property<?> property = statedefinition.getProperty(s);
				if (property != null) {
					String s1 = compoundtag1.get(s).getAsString();
					blockstate = updateState(blockstate, property, s1);
				}
			}
		}
		if (blockstate != state) {
			level.setBlock(pos, blockstate, 2);
		}
		return blockstate;
	}
	
	protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
		return context.getLevel().setBlock(context.getClickedPos(), state, 11);
	}
	
	private static <T extends Comparable<T>> BlockState updateState(BlockState state, Property<T> property, String text) {
		return property.getValue(text).map((p_40592_) -> {
			return state.setValue(property, p_40592_);
		}).orElse(state);
	}
	
	public static boolean updateCustomBlockEntityTag(Level level, @Nullable Player player, BlockPos pos, ItemStack itemStack) {
		MinecraftServer minecraftserver = level.getServer();
		if (minecraftserver == null) {
			return false;
		} else {
			CompoundTag compoundtag = itemStack.getTagElement("BlockEntityTag");
			if (compoundtag != null) {
				BlockEntity blockentity = level.getBlockEntity(pos);
				if (blockentity != null) {
					if (!level.isClientSide && blockentity.onlyOpCanSetNbt() && (player == null || !player.canUseGameMasterBlocks())) {
						return false;
					}
					CompoundTag compoundtag1 = blockentity.saveWithoutMetadata();
					CompoundTag compoundtag2 = compoundtag1.copy();
					compoundtag1.merge(compoundtag);
					compoundtag1.putInt("x", pos.getX());
					compoundtag1.putInt("y", pos.getY());
					compoundtag1.putInt("z", pos.getZ());
					if (!compoundtag1.equals(compoundtag2)) {
						blockentity.load(compoundtag1);
						blockentity.setChanged();
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public Block getBlock() {
		return this.getBlockRaw() == null ? null : this.getBlockRaw();
	}
	
	private Block getBlockRaw() {
		return RegisterSeed.GetCropByMap(crop);
	}
	
	
	
}
