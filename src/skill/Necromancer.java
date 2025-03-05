package skill;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import player.Player;

public class Necromancer extends Skill {
	@Override
	public boolean activate(Player player) {
		List<Integer> status = Arrays.asList(new Integer[] { 3, 4 });
		Collections.shuffle(status);

		player.setHealth(status.get(0));
		player.setEnergy(status.get(1));
		player.changeSpeed(-2);
		
		return true;
	}
}