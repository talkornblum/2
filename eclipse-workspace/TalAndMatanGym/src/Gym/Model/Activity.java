package Gym.Model;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Activity {
	//members
	private int idactivity;
	 
    private Calendar date;
    private String Username;
    private String Exercise;
    private int Running_time;

    private int Avg_speed;
    private String Muscle;
    private int Sets;
    private int Repetition;
    private int Weight;

    public  Activity(){};
    public Activity (String username,String muscle,int running_time,int avg_speed , Calendar date){
        this.setUsername(username);
        this.setMuscle(muscle);
        this.setRunning_time(running_time);
        this.setAvg_speed(avg_speed);
        this.setdate(date);

    }
    public Activity (String username,String muscle,int sets,int repetition,int weight, Calendar date){
        this.setUsername(username);
        this.setMuscle(muscle);
        this.setSets(sets);
        this.setRepetition(repetition);
        this.setWeight(weight);
        this.setdate(date);
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public String getExercise() {
        return Exercise;
    }
    public void setExercise(String exercise) {
        Exercise = exercise;
    }
    public int getRunning_time() {
        return Running_time;
    }
    public void setRunning_time(int running_time) {
        Running_time = running_time;
    }
    public int getAvg_speed() {
        return Avg_speed;
    }
    public void setAvg_speed(int avg_speed) {
        Avg_speed = avg_speed;
    }
    public String getMuscle() {
        return Muscle;
    }
    public void setMuscle(String muscle) {
        Muscle = muscle;
    }
    public int getSets() {
        return Sets;
    }
    public void setSets(int sets) {
        Sets = sets;
    }
    public int getRepetition() {
        return Repetition;
    }
    public void setRepetition(int repetition) {
        Repetition = repetition;
    }
    public int getWeight() {
        return Weight;
    }
    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getId() {
        return idactivity;
    }

    public void setId(int idactivity) {
        this.idactivity = idactivity;
    }
	public Calendar getdate() {
		return date;
	}
	public void setdate(Calendar date) {
		this.date = date;
	}
}
