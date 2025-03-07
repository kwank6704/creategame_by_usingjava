package potion;

import java.util.Random;

import player.Player;

public class Potion implements PotionEffect {	
	private PotionList potionType;
	private int potency;

	public Potion(PotionList type) {
		setType(type);
		setDefaultValues();
	}

	public Potion(PotionList type, int potency) {
		setType(type);
		setPotency(potency);
	}

	private void setDefaultValues() {
		switch (potionType) {
		case SMALL_HEAL:
			this.potency = 1;
			break;
		case LARGE_HEAL:
			this.potency = 2;
			break;
		case SMALL_ENERGY:
			this.potency = 1;
			break;
		case LARGE_ENERGY:
			this.potency = 2;
			break;
		case INCREASE_SPEED:
			this.potency = 2;
			break;
		case DECREASE_SPEED:
			this.potency = -2;
			break;
		case RANDOM_POTION:
			this.potency = 0;
			break;
		default:
			throw new IllegalArgumentException("Unknown potion type: " + potionType);
		}
	}
	
	private void useRandomPotionType(Player player) {
		Random rand = new Random();
		PotionList[] validPotions = {
				PotionList.SMALL_HEAL, PotionList.LARGE_HEAL,
				PotionList.SMALL_ENERGY, PotionList.LARGE_ENERGY,
				PotionList.INCREASE_SPEED, PotionList.DECREASE_SPEED
		};

		PotionList randomPotion = validPotions[rand.nextInt(validPotions.length)];
		Potion potion = new Potion(randomPotion);
		potion.applyEffect(player);
	}

	public PotionList getType() {
		return potionType;
	}

	public void setType(PotionList type) {
		this.potionType = type;
	}

	public int getPotency() {
		return potency;
	}

	public void setPotency(int potency) {
		this.potency = potency;
	}

	@Override
	public String toString() {
		return "Potion{" + "type = " + potionType + ", potency = " + potency + "}";
	}

	@Override
	public void applyEffect(Player player) {
		switch (potionType) {
		case SMALL_HEAL:
		case LARGE_HEAL:
			player.changeHealth(potency);
			break;
		case SMALL_ENERGY:
		case LARGE_ENERGY:
			player.changeEnergy(potency);
			break;
		case INCREASE_SPEED:
		case DECREASE_SPEED:
			player.changeSpeed(potency);
			break;
		case RANDOM_POTION:
			useRandomPotionType(player);
			break;
		default:
			throw new IllegalArgumentException("Unknown potion type: " + potionType);
		}
	}
}
