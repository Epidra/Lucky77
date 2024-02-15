package mod.lucky77.util.system;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class SystemTrades {
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  GEM TO ITEM  ---------- ---------- ---------- ---------- //
	
	public static class GemsForItems implements VillagerTrades.ItemListing {
		private final Item item;
		private final Item gem;
		private final int cost;
		private final int amount;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;
		
		public GemsForItems(Block block, int cost, int maxUses, int villagerXP) {
			this(block, cost, Items.EMERALD, 1, maxUses, villagerXP, 0.05F);
		}
		
		public GemsForItems(Block block, int cost, int maxUses, int villagerXP, float priceMultiplier) {
			this(block, cost, Items.EMERALD, 1, maxUses, villagerXP, priceMultiplier);
		}
		
		public GemsForItems(Item item, int cost, int maxUses, int villagerXP) {
			this(item, cost, Items.EMERALD, 1, maxUses, villagerXP, 0.05F);
		}
		
		public GemsForItems(Item item, int cost, int maxUses, int villagerXP, float priceMultiplier) {
			this(item, cost, Items.EMERALD, 1, maxUses, villagerXP, priceMultiplier);
		}
		
		public GemsForItems(ItemLike item, int cost, ItemLike gem, int amount, int maxUses, int villagerXP, float priceMultiplier) {
			this.item            = item.asItem();
			this.gem             = gem.asItem();
			this.cost            = cost;
			this.amount          = amount;
			this.maxUses         = maxUses;
			this.villagerXp      = villagerXP;
			this.priceMultiplier = priceMultiplier;
		}
		
		public MerchantOffer getOffer(Entity entity, RandomSource random) {
			return new MerchantOffer(new ItemStack(this.item, this.cost), new ItemStack(this.gem, this.amount), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  ITEM TO GEM  ---------- ---------- ---------- ---------- //
	
	public static class ItemsForGems implements VillagerTrades.ItemListing {
		private final ItemStack itemStack;
		private final Item gem;
		private final int gemCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;
		
		public ItemsForGems(Block block, int gemCost, int numberOfItems, int maxUses, int villagerXp) {
			this(new ItemStack(block), gemCost, numberOfItems, maxUses, villagerXp);
		}
		
		public ItemsForGems(Block block, int gemCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier) {
			this(new ItemStack(block), gemCost, numberOfItems, maxUses, villagerXp, priceMultiplier);
		}
		
		public ItemsForGems(Item item, int gemCost, int numberOfItems, int maxUses, int villagerXp) {
			this(new ItemStack(item), gemCost, numberOfItems, maxUses, villagerXp);
		}
		
		public ItemsForGems(Item item, int gemCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier) {
			this(new ItemStack(item), gemCost, numberOfItems, maxUses, villagerXp, priceMultiplier);
		}
		
		public ItemsForGems(ItemStack itemstack, int emeraldCost, int numberOfItems, int maxUses, int villagerXp) {
			this(itemstack, emeraldCost, numberOfItems, maxUses, villagerXp, 0.05F, Items.EMERALD);
		}
		
		public ItemsForGems(ItemStack itemstack, int emeraldCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier) {
			this(itemstack, emeraldCost, numberOfItems, maxUses, villagerXp, priceMultiplier, Items.EMERALD);
		}
		
		public ItemsForGems(ItemStack itemStack, int gemCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier, Item gem) {
			this.itemStack       = itemStack;
			this.gemCost         = gemCost;
			this.numberOfItems   = numberOfItems;
			this.maxUses         = maxUses;
			this.villagerXp      = villagerXp;
			this.priceMultiplier = priceMultiplier;
			this.gem             = gem;
		}
		
		public MerchantOffer getOffer(Entity entity, RandomSource random) {
			return new MerchantOffer(new ItemStack(this.gem, this.gemCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  ADD TRADES  ---------- ---------- ---------- ---------- //
	
	public static void addTradeToProfession(VillagerProfession profession, int level, VillagerTrades.ItemListing[] newTrades){
		Int2ObjectMap<VillagerTrades.ItemListing[]> listing =  VillagerTrades.TRADES.get(profession);
		VillagerTrades.ItemListing[] oldTrades = listing.get(level);
		
		VillagerTrades.ItemListing[] temp = new VillagerTrades.ItemListing[oldTrades.length + newTrades.length];
		for(int i = 0; i < oldTrades.length; i++)
			temp[i] = oldTrades[i];
		for(int i = 0; i < newTrades.length; i++)
			temp[i + oldTrades.length] = newTrades[i];
		listing.put(1, temp);
		VillagerTrades.TRADES.put(profession, listing);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  MAPPER  ---------- ---------- ---------- ---------- //
	
	public static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_35631_) {
		return new Int2ObjectOpenHashMap<>(p_35631_);
	}
	
	
	
}
