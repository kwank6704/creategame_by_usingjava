package skill;

import player.Player;

public class SkillsGuild extends Skill {
    public static Skill getSkill(GuildList guildList) {
        switch (guildList) {
            case EXPLORER:
                return new Explorer();
            case HEALER:
                return new Healer();
            case ALCHEMIST:
                return new Alchemist();
            case MAGE:
                return new Mage();
            case HUNTER:
                return new Hunter();
            case NECROMANCER:
                return new Necromancer();
            case KNIGHT:
                return new Knight();
            case ARCHER:
                return new Archer();
            default:
                throw new IllegalArgumentException("Unknown class: ");
        }
    }

	@Override
	public boolean activate(Player player) {
		return true;
	}
}
