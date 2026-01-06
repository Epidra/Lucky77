package mod.lucky77.custom.other;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class SupportTrades {
	
	// ...
	
	//   -------- -------- -------- --------     GEM  TO  ITEM     -------- -------- -------- --------   //
	
	public static class GemsForItems implements VillagerTrades.ItemListing {
		private final Item item;
		private final Item gem;
		private final int cost;
		private final int amount;
		private final int maxUses;
		private final int villagerXP;
		private final float priceMultiplier;
		
		public GemsForItems(Block block, int cost, int maxUses, int villagerXP){
			this(block, cost, Items.EMERALD, 1, maxUses, villagerXP, 0.05F);
		}
		
		public GemsForItems(Block block, int cost, int maxUses, int villagerXP, float priceMultiplier){
			this(block, cost, Items.EMERALD, 1, maxUses, villagerXP, priceMultiplier);
		}
		
		public GemsForItems(Item item, int cost, int maxUses, int villagerXP){
			this(item, cost, Items.EMERALD, 1, maxUses, villagerXP, 0.05F);
		}
		
		public GemsForItems(Item item, int cost, int maxUses, int villagerXP, float priceMultiplier){
			this(item, cost, Items.EMERALD, 1, maxUses, villagerXP, priceMultiplier);
		}
		
		public GemsForItems(ItemLike item, int cost, ItemLike gem, int amount, int maxUses, int villagerXP, float priceMultiplier){
			this.item = item.asItem();
			this.gem = gem.asItem();
			this.cost = cost;
			this.amount = amount;
			this.maxUses = maxUses;
			this.villagerXP = villagerXP;
			this.priceMultiplier = priceMultiplier;
		}
		
		@Override
		public MerchantOffer getOffer(Entity entity, RandomSource random){
			return new MerchantOffer(new ItemCost(this.item, this.cost), new ItemStack(this.gem, this.amount), this.maxUses, this.villagerXP, this.priceMultiplier);
		}
	}
	
	//   -------- -------- -------- --------     ITEM  TO GEM     -------- -------- -------- --------   //
	
	public static class ItemsForGems implements VillagerTrades.ItemListing {
		private final Item item;
		private final Item gem;
		private final int gemCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXP;
		private final float priceMultiplier;
		
		public ItemsForGems(Block block, int gemCost, int numberOfItems, int maxUses, int villagerXP){
			this(block, gemCost, numberOfItems, Items.EMERALD, 1, maxUses, villagerXP, 0.05F);
		}
		
		public ItemsForGems(Block block, int gemCost, int numberOfItems, int maxUses, int villagerXP, float priceMultiplier){
			this(block, gemCost, numberOfItems, Items.EMERALD, 1, maxUses, villagerXP, priceMultiplier);
		}
		
		public ItemsForGems(Item item, int gemCost, int numberOfItems, int maxUses, int villagerXP){
			this(item, gemCost, numberOfItems, Items.EMERALD, 1, maxUses, villagerXP, 0.05F);
		}
		
		public ItemsForGems(Item item, int gemCost, int numberOfItems, int maxUses, int villagerXP, float priceMultiplier){
			this(item, gemCost, numberOfItems, Items.EMERALD, 1, maxUses, villagerXP, priceMultiplier);
		}
		
		public ItemsForGems(ItemLike item, int gemCost, int numberOfItems, ItemLike gem, int amount, int maxUses, int villagerXP, float priceMultiplier){
			this.item = item.asItem();
			this.gem = gem.asItem();
			this.gemCost = gemCost;
			this.numberOfItems = numberOfItems;
			this.maxUses = maxUses;
			this.villagerXP = villagerXP;
			this.priceMultiplier = priceMultiplier;
		}
		
		@Override
		public MerchantOffer getOffer(Entity entity, RandomSource random){
			return new MerchantOffer(new ItemCost(this.gem, this.gemCost), new ItemStack(this.item, this.numberOfItems), this.maxUses, this.villagerXP, this.priceMultiplier);
		}
	}
	
	//   -------- -------- -------- --------     ADD  TRADES     -------- -------- -------- --------   //
	
	public static void addTradeToProfession(VillagerProfession profession, int level, VillagerTrades.ItemListing[] newTrades){
		Int2ObjectMap<VillagerTrades.ItemListing[]> listing = VillagerTrades.TRADES.get(profession);
		VillagerTrades.ItemListing[] oldTrades = listing.get(level);
		
		VillagerTrades.ItemListing[] temp = new VillagerTrades.ItemListing[oldTrades.length + newTrades.length];
		for(int i = 0; i < oldTrades.length; i++){
			temp[i] = oldTrades[i];
		}
		for(int i = 0; i < newTrades.length; i++){
			temp[i + oldTrades.length] = newTrades[i];
		}
		listing.put(1, temp);
		VillagerTrades.TRADES.put(profession, listing);
	}
	
}
