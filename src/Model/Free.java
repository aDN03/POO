package src.Model;

import java.io.Serializable;

public class Free implements Subscription, Serializable {

	public void calculatePoints(User user) {
        double points = user.getPoints() + 5;
        user.setPoints(points);
    }

    @Override
    public String toString() {
        return "Free";
    }
}