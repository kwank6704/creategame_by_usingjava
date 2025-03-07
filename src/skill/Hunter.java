package skill;

import player.Player;
import potion.Potion;
import potion.PotionList;

public class Hunter extends Skill {
	@Override
	public boolean activate(Player player) {
		player.changeSpeed(2);
		player.addPotion(new Potion(PotionList.INCREASE_SPEED));
		
		return true;
	}
}
