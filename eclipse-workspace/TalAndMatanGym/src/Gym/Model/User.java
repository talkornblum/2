package Gym.Model;

public class User {
    private String Password;
    private String Username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;
    public  User(){};

    public User (String username,String password){
        this.setUsername(username);
        this.setPassword(password);
    }
    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
