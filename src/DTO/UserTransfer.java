package src.DTO;

public class UserTransfer {
    private String name;
    private String email;
    private String address;
    private String subscription;

    public UserTransfer(){
        this.name = null;
        this.email = null;
        this.address = null;
        this.subscription = null;
    }

    public UserTransfer(String name, String email, String address, String subscription) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.subscription = subscription;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSubscription() {
        return subscription;
    }
    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }
}
