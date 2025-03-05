package skill;

import player.Player;

public class Archer extends Skill {
	@Override
	public boolean activate(Player player) {
		player.changeHealth(1);
		player.changeEnergy(1);
		
		return true;
	}
}
