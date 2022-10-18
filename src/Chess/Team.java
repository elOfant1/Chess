package Chess;

public enum Team {
	white, black;

	public static Team oppositeTeam(Team t) {
		if (t == Team.white)
			return Team.black;
		return Team.white;
	}
}
