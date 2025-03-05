package skill;

import player.Player;
import potion.Potion;
import potion.PotionList;

public class Mage extends Skill {
	@Override
	public boolean activate(Player player) {
		return player.addPotion(new Potion(PotionList.RANDOM_POTION)) || player.addPotion(new Potion(PotionList.RANDOM_POTION));
	}
}
