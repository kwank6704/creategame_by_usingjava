package skill;

import player.Player;
import potion.Potion;
import potion.PotionList;

public class Knight extends Skill {
	@Override
	public boolean activate(Player player) {
		player.changeEnergy(2);
		player.addPotion(new Potion(PotionList.SMALL_ENERGY));
		
		return true;
	}
}
