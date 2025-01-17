package vazkii.quark.content.tools.config;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import vazkii.quark.base.module.config.Config;
import vazkii.quark.base.module.config.type.AbstractConfigType;
import vazkii.quark.content.tools.entity.rang.AbstractPickarang;

public class PickarangType<T extends AbstractPickarang<T>> extends AbstractConfigType {

	public final Item repairMaterial;
	public final Item pickaxeEquivalent;
	
	@Config(description = "How long it takes before the Pickarang starts returning to the player if it doesn't hit anything.")
	public int timeout;
	
	@Config(description = "Pickarang harvest level. 2 is Iron, 3 is Diamond, 4 is Netherite.")
	public int harvestLevel;
	
	@Config(description = "Pickarang durability. Set to -1 to have the Pickarang be unbreakable.")
	public int durability;
	
	@Config(description = "Pickarang max hardness breakable. 22.5 is ender chests, 25.0 is monster boxes, 50 is obsidian. Most things are below 5.")
	public double maxHardness;
	
	@Config(description = "How much damage the Pickarang deals when swung as an item")
	public int attackDamage;
	
	@Config(description = "How many ticks do you have to wait between using the pickarang again")
	public int cooldown;
	
	private EntityType<T> entityType;
	private PickarangConstructor<T> pickarangConstructor;
	
	public PickarangType(Item repairMaterial, Item pickaxeEquivalent, int timeout, int harvestLevel, int durability, double maxHardness, int attackDamage, int cooldown) {
		this.repairMaterial = repairMaterial;
		this.pickaxeEquivalent = pickaxeEquivalent;
		
		this.timeout = timeout;
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.maxHardness = maxHardness;
		this.attackDamage = attackDamage;
		this.cooldown = cooldown;
	}
	
	public boolean isFireResistant() {
		return pickaxeEquivalent != null && pickaxeEquivalent.isFireResistant();
	}
	
	public EntityType<T> getEntityType() {
		return entityType;
	}
	
	public void setEntityType(EntityType<T> entityType, PickarangConstructor<T> cons) {
		this.entityType = entityType;
		this.pickarangConstructor = cons;
	}
	
	public AbstractPickarang<T> makePickarang(Level level, Player thrower) {
		return pickarangConstructor.makePickarang(entityType, level, thrower);
	}
	
	public interface PickarangConstructor<T extends AbstractPickarang<T>> {
		T makePickarang(EntityType<T> entityType, Level level, Player thrower);
	}
	
}
