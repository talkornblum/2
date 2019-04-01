package Gym.Model;

import java.util.Date;
import java.util.List;

public interface IGymDAO {
	boolean checkexists(String username,String password);
    public User getUser(String usrname);
    public void AddUser(User user);
    public void UpdateUser(User user);
    public void DeleteUser(User user);
    public List<Activity> getActivities(User user);
    public Activity getActivity(int id);
    public void AddActivity(Activity activity);
    public void UpdateActivity(Activity activity);
    public void DeleteActivity(Activity activity);
}
