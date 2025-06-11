package src.Model;

import java.io.Serializable;

public class PremiumTop implements Subscription, Serializable {
	private int initialPoints;
	private boolean bonusGiven;

	public PremiumTop() {
		this.initialPoints = 100;
		this.bonusGiven = false;
	}

	public PremiumTop(int initialPoints, boolean bonusGiven) {
		this.initialPoints = initialPoints;
		this.bonusGiven = bonusGiven;
	}

	public PremiumTop(PremiumTop pt) {
		this.initialPoints = pt.initialPoints;
		this.bonusGiven = pt.bonusGiven;
	}
	
	public int getInitialPoints() {
		return initialPoints;
	}
	public void setInitialPoints(int initialPoints) {
		this.initialPoints = initialPoints;
	}
	public boolean isBonusGiven() {
		return bonusGiven;
	}
	public void setBonusGiven(boolean bonusGiven) {
		this.bonusGiven = bonusGiven;
	}
	
	@Override
	public String toString() {
		return "PremiumTop";
	}

	public void calculatePoints(User user) {
		double userPoints = user.getPoints();
		double bonus = userPoints * 0.025;
        user.setPoints(userPoints + bonus);
    }

	public void giveInitialPoints(User user){
		user.setPoints(user.getPoints() + initialPoints);
		this.bonusGiven = true;
	}

}