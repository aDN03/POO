package src.Model;

import java.io.Serializable;
import java.util.*;

import src.DTO.UserTransfer;
import src.Exceptions.*;
import src.View.UI;

public class UserManager implements Serializable{
    private Map<String, User> userLibrary;
    private int newUserId = 1;
    private static final long serialVersionUID = 1L;

    public UserManager(){
        userLibrary = new HashMap<>();
    }

    public User getUser(String id) {
        if(!userLibrary.containsKey(id)){
            throw new UserNotFoundException("User with Id " + id + " not Found");
        }
        return userLibrary.get(id);
    }

    public String userIdGenerator(){
        return "U" + newUserId++;
    }

    public void addUser(User user){
        if(user == null || user.getId() == null){
            throw new InvalidUserException("User or User ID cannot be null.");
        }

        if (userLibrary.containsKey(user.getId())) {
            throw new DuplicateUserException("User with ID " + user.getId() + " already exists.");
        }

        userLibrary.put(user.getId(), user);
    }

    public void deleteUser(String id){
        if (userLibrary.containsKey(id)){
            userLibrary.remove(id);
        } else {
            throw new InvalidUserException("User with ID " + id + " does not exist");
        }
    }

    public List<User> getUsersByName (String name){

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidUserException("User name cannot be null or empty.");
        }

        List<User> users = new ArrayList<>();
        for (User user : userLibrary.values()){
            if(user.getName().equalsIgnoreCase(name)){
                users.add(user);
            }
        }

        if (users.isEmpty()) {
            throw new SongNotFoundException("No users found with name: " + name);
        }

        return users;
    }

    public void assignSubscription(User user, Subscription subscription){

        if(user == null || subscription == null){
            throw new IllegalArgumentException("User or subscription can't be null");
        }

        user.setSubscription(subscription);

		if(subscription instanceof PremiumTop){
			user.setPoints(user.getPoints() + ((PremiumTop)subscription).getInitialPoints());
            ((PremiumTop)subscription).setBonusGiven(true);
		}

	}

    public Collection<User> getAllUsers() {
        Collection<User> users = userLibrary.values();
        return users;
    }

    public void applyChangesFrom(String uid, UserTransfer ut){
        User user = userLibrary.get(uid);

        if (user == null) {
            throw new UserNotFoundException("User with the id " + uid + " not found");
        }

        if (ut.getName() != null) user.setName(ut.getName());
        if (ut.getEmail() != null) user.setEmail(ut.getEmail());
        if (ut.getAddress() != null) user.setAddress(ut.getAddress());
        if (ut.getSubscription() != null){
            switch (ut.getSubscription()) {
                    case "Free":
                        user.setSubscription(new Free());
                        break;
                    case "PremiumBase":
                        user.setSubscription(new PremiumBase());
                        break;
                    case "PremiumTop":
                        PremiumTop premiumTop = new PremiumTop();
                        premiumTop.giveInitialPoints(user);
                        user.setSubscription(premiumTop);
                        break;
                    default:
                        UI.printError("Invalid subscription type");
                        break;
                }
        }
    }

}
