package skill;

import player.Player;
import potion.Potion;
import potion.PotionList;

public class Healer extends Skill {
	@Override
	public boolean activate(Player player) {
		player.changeHealth(2);
		player.addPotion(new Potion(PotionList.SMALL_HEAL));
		
		return true;
	}
}
