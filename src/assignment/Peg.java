package assignment;
public class Peg{
	private int totalScore;
	private int playerID;
	public int getPlayerID() {
		return playerID;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int n) {
		totalScore = n;
	}
	public void addPoints(int n) {
		totalScore += n;
	}
	public Peg(int p) {
		playerID = p;
		totalScore = 0;
	}
	/*
	 * didn't add a setter for playerID since it does not change.
	 * Determined at instantiation
	 */
}