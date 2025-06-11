package src.Model;

import java.io.Serializable;

public class PremiumBase implements Subscription, Serializable {

	public void calculatePoints(User user) {
        double points = user.getPoints() + 10;
        user.setPoints(points);
    }

    @Override
    public String toString() {
        return "PremiumBase";
    }
}