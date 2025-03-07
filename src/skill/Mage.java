package skill;

import player.Player;
import potion.Potion;
import potion.PotionList;

public class Mage extends Skill {
	@Override
	public boolean activate(Player player) {		
		boolean add1 = player.addPotion(new Potion(PotionList.RANDOM_POTION));
		boolean add2 = player.addPotion(new Potion(PotionList.RANDOM_POTION));		
		return add1 || add2;
	}
}
