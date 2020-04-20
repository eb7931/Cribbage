package assignment;
public class Peg{
	int totalScore;
	int playerID;
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
}