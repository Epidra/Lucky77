package mod.lucky77.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

public class BlockXP extends Block {
	
	private final IntProvider xpRange;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public BlockXP(MapColor color, float hardness, float resistance, SoundType sound, int minXP, int maxXP){
		super(BlockBehaviour.Properties.of().mapColor(color).destroyTime(hardness).explosionResistance(resistance).sound(sound));
		this.xpRange = UniformInt.of(minXP, maxXP);
	}
	
	/** Constructor with Property extracted from Block **/
	public BlockXP(Block block, int minXP, int maxXP){
		super(BlockBehaviour.Properties.ofFullCopy(block));
		this.xpRange = UniformInt.of(minXP, maxXP);
	}
	
	/** Constructor with predefined Property **/
	public BlockXP(BlockBehaviour.Properties properties, int minXP, int maxXP) {
		super(properties);
		this.xpRange = UniformInt.of(minXP, maxXP);
	}
	
	
	
	
	
	//   -------- -------- -------- --------     PLACEMENT     -------- -------- -------- --------   //
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     INTERACTION     -------- -------- -------- --------   //
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Drops EXP orbs on destruction - based on a neoforge-patch-in **/
	@Override
	public int getExpDrop(BlockState state, net.minecraft.world.level.LevelAccessor level, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity breaker, ItemStack tool) {
		return this.xpRange.sample(level.getRandom());
	}
	
	
	
}
