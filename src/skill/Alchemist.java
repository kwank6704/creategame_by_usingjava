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
		
		boolean add1 = player.addPotion(potions.get(0));
		boolean add2 = player.addPotion(potions.get(1));		
		boolean add3 = player.addPotion(potions.get(2));		
		return add1 || add2 || add3;
	}
}
