package mod.lucky77.block;

import mod.lucky77.block.base.BlockBase;
import mod.lucky77.block.entity.BlockEntityBase;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("unused")
public class BlockOre extends BlockBase {
	
	private final IntProvider xpRange;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public BlockOre(MapColor color, float hardness, float resistance, SoundType sound, int minXP, int maxXP) {
		super(Properties.of().mapColor(color).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(hardness, resistance).sound(sound));
		this.xpRange = UniformInt.of(minXP, maxXP);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  PLACEMENT  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	@Override
	public void interact(Level world, BlockPos pos, Player player, BlockEntityBase tile) {
	
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	@Override
	public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
		return silkTouchLevel == 0 ? this.xpRange.sample(randomSource) : 0;
	}
	
	
	
}
