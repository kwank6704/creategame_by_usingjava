package skill;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import player.Player;
import potion.Potion;
import potion.PotionList;

public class Alchemist extends Skill {
	@Override
	public boolean activate(Player player) {
		List<Potion> potions = Arrays.asList(new Potion[] {
				new Potion(PotionList.SMALL_HEAL),
				new Potion(PotionList.SMALL_ENERGY),
				new Potion(PotionList.INCREASE_SPEED)
		});
		Collections.shuffle(potions);

		return player.addPotion(potions.get(0)) || player.addPotion(potions.get(1));
	}
}
