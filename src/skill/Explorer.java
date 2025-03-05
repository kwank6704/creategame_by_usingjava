package skill;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import player.Player;
import potion.Potion;
import potion.PotionList;

public class Explorer extends Skill {
	@Override
	public boolean activate(Player player) {
		player.setSpeed(Player.DefaultSpeed);

		List<Potion> potions = Arrays.asList(new Potion[] {
				new Potion(PotionList.INCREASE_SPEED),
				new Potion(PotionList.DECREASE_SPEED)
		});
		Collections.shuffle(potions);

		return player.addPotion(potions.get(0)) || player.addPotion(potions.get(1));
	}
}
