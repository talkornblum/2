package Gym.Model;

import Gym.Model.Activity;
import Gym.Model.User;
import Gym.Model.HibernateGymDAO;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class mainTest {
    public static void main(String [] args){
    	Calendar cal=Calendar.getInstance();
        HibernateGymDAO hiber = HibernateGymDAO.getInstance();
        Calendar d=Calendar.getInstance();
        Activity activity;
        int a=20;
        int b=10;
        int c=25;
				String[] Days = new String[7];
				int[] dayTemp=new int[7];
				d.add(Calendar.DAY_OF_WEEK, -7);
				for (int i = 7; i >=0 ; i--) {
					activity=new Activity("tal","leg",a-i,b-i,c-i,d);
					d.add(Calendar.DAY_OF_WEEK, +1);
					hiber.AddActivity(activity);
				}
        activity=hiber.getActivity(4);
//        System.out.println(activity.getId());
//        System.out.println(activity.getId());
//        User matan=new User("matan","matan");
//          User tal =new User("tal","12345");
//        System.out.print(tal.getUsername() +" "+tal.getPassword() );
//        hiber.AddUser(matan);
//          hiber.AddUser(tal);
//        matan.setUsername("Matan123");
//        matan.setPassword("mat1");
//        hiber.UpdateUser(matan);
//          User user=hiber.getUser("tal");
//        System.out.print(user.getUsername() +" "+user.getPassword() );
//        List<User> users=hiber.getAllUsers();
//        Iterator<User> i = users.iterator();
//        while (i.hasNext()) {
//           i.next().toString();
//        }
//        User Tal =new User("t","t");
//        hiber.AddUser(Tal);
//        System.out.print(Tal.getUsername() +" "+Tal.getPassword() );
//          Date d=new Date();
//         Activity activity = new Activity(user.getUsername(),"leg",50,5,5,d);
//         System.out.print(activity.getUsername()+"          ");
//         System.out.print(activity.getMuscle()+"          ");
//         System.out.print(activity.getRepetition() + "      ");
//         System.out.print(activity.getWeight() + "      ");
//         System.out.print(activity.getdate()+"         ");
//          hiber.AddActivity(activity);
//         
//          List<Activity> l= (List<Activity>)hiber.getActivitys(user);
//          System.out.println("dsjogdsg");
//          Activity a=l.get(0);

//          System.out.println(a.getRunning_time()+" "+ a.getAvg_speed()+" "+a.getdate());
          
       // System.out.print(activity.getExercise() +" "+activity.getRunning_time()+" "+ activity.getAvg_speed() );
       // System.out.println("Deleting the user from DB... ");
//        hiber.DeleteUser(matan);
//        hiber.DeleteUser(tal);

    }
}
