package Gym.Hit.Controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.eclipse.jdt.internal.compiler.util.Util;

import Gym.Model.Activity;
import Gym.Model.User;
import Gym.Model.HibernateGymDAO;



public class controller {
	
	public void tologin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ //move to login page
		RequestDispatcher dispatcher=null;
		dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}
	
	public void tosingup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ //move to singup page
		RequestDispatcher dispatcher=null;
		dispatcher = request.getRequestDispatcher("/singup.jsp");
		dispatcher.forward(request, response);
	}
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException //treatment of login page
	{
		RequestDispatcher dispatcher=null;
		String username=request.getParameter("username");  
		String password=request.getParameter("password");
		HibernateGymDAO hiber = HibernateGymDAO.getInstance();
		boolean flag= hiber.checkexists(username, password); //check if the user is correct
			if(flag==true)
			{
				request.getSession().setAttribute("username", username);
				dispatcher = request.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
			}
			else
			{ 
				// this for the error page (all the if)
				request.setAttribute("UserNameExist", "1");
				request.setAttribute("ErrorPassWord", "1");
				request.setAttribute("ErrorActivity","1");
				request.setAttribute("ErrorUpDate","1");
				request.setAttribute("UserNameDontExist", "UserNameDontExist");
				dispatcher = request.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
	
	public void singup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //treatment of singup page
		final Logger logger = Logger.getLogger("controller");
		BasicConfigurator.configure();
		RequestDispatcher dispatcher=null;
		String username=request.getParameter("username");  
		String password=request.getParameter("password");
		logger.info("the new user is" +username);
		System.out.println(username);
		System.out.println(password);
		HibernateGymDAO hiber = HibernateGymDAO.getInstance();
		if(hiber.checkuser(username)==false && password.length()> 4)
		{	
		request.getSession().setAttribute("username", username);
		User user=new User(username,password);
		hiber.AddUser(user);
		dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
		}
		else if(password.length() <= 4) {
			request.setAttribute("UserNameExist", "1");
			request.setAttribute("UserNameDontExist", "1");
			request.setAttribute("ErrorActivity","1");
			request.setAttribute("ErrorUpDate","1");
			request.setAttribute("ErrorPassWord", "ErrorPassWord");
			dispatcher = request.getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("UserNameDontExist", "1");
			request.setAttribute("ErrorPassWord", "1");
			request.setAttribute("ErrorActivity","1");
			request.setAttribute("ErrorUpDate","1");
			request.setAttribute("UserNameExist", "UserNameExist");
			dispatcher = request.getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void logout (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(!session.isNew()) //delete session 
		{
			session.invalidate();
			session=request.getSession();
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}
	
	public void updateuser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ // treatment of update page
		
		RequestDispatcher dispatcher=null;
		List<Activity> activities=null;
		HibernateGymDAO hiber = HibernateGymDAO.getInstance();
		String username=request.getParameter("username");  
		String password=request.getParameter("password");
		String prevuser=(String) request.getSession().getAttribute("username");
		User user=hiber.getUser(prevuser);
		activities=hiber.getActivities(hiber.getUser(user.getUsername()));
		boolean flag= hiber.checkexists(username, password);
		if(flag==false)
		{
				for(int i=0;i<activities.size();i++)//change all activities by prev user-name to the new one
				{
					activities.get(i).setUsername(username);
					hiber.UpdateActivity(activities.get(i));
				}
			    user.setUsername(username); //set the new user
			    user.setPassword(password);
				hiber.UpdateUser(user);
				request.getSession().setAttribute("username", username);
				dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
		}
				else
				{
				request.setAttribute("UserNameDontExist", "1");	
				request.setAttribute("ErrorPassWord", "1");
				request.setAttribute("UserNameExist", "1");
				request.setAttribute("ErrorActivity","1");
				request.setAttribute("ErrorUpDate","ErrorUpdate");	
				dispatcher = request.getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				}
	}
	public void addactivityaerobic (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { //input into Hibernate new aerobic activity
		RequestDispatcher dispatcher=null;
		Calendar cal=Calendar.getInstance();
		String muscle="running";
		String running_time=request.getParameter("running_time");  
		String avg_speed=request.getParameter("avg_speed");
		String username=(String) request.getSession().getAttribute("username");
		HibernateGymDAO hiber = HibernateGymDAO.getInstance();
		if(!(running_time.isEmpty()) && !(avg_speed.isEmpty()))
		{
			if(avg_speed.matches("[0-9]+") && running_time.matches("[0-9]+"))//Verify whether the input activity is correct
				if(!((running_time.length() == 0 && running_time.matches("[0]+")) || (avg_speed.length() == 0 && avg_speed.matches("[0]+"))))
				{
				Integer running_time1=Integer.parseInt(running_time);//parsing to int
				Integer avg_speed1=Integer.parseInt(avg_speed);
				Activity activity= new Activity(username,muscle,running_time1, avg_speed1,cal);
				hiber.AddActivity(activity);
				dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
			    }
		}
				//else
					request.setAttribute("UserNameDontExist", "1");
					request.setAttribute("ErrorPassWord", "1");
					request.setAttribute("UserNameExist", "1");
					request.setAttribute("ErrorUpDate","1");
					request.setAttribute("ErrorActivity","ErrorActivity");
					dispatcher = request.getServletContext().getRequestDispatcher("/error.jsp");
					dispatcher.forward(request, response);
			        				
	}
	
	public void addactivityanaerobic (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //input into Hibernate new an aerobic activity
		RequestDispatcher dispatcher=null;
		Calendar cal=Calendar.getInstance();
		String muscle=request.getParameter("select-muscle");
		String sets=request.getParameter("sets");
		String repetition=request.getParameter("repetition");
		String weight=request.getParameter("weight");
		String username=(String) request.getSession().getAttribute("username");
		HibernateGymDAO hiber = HibernateGymDAO.getInstance();
		if(!(sets.isEmpty()) && !(repetition.isEmpty()) && !(weight.isEmpty()) && !(muscle.isEmpty() && muscle.compareTo("choose muscle")!=0))
		{
			if(sets.matches("[0-9]+") && repetition.matches("[0-9]+") && weight.matches("[0-9]+") ) //Verify whether the input activity is correct
				if(!(((sets.length() == 1 && sets.matches("[0]+")) || (repetition.length() == 1 && (repetition.matches("[0]+")) && weight.matches("[0]+")))))
				{
					Integer sets1=Integer.parseInt(sets);//parsing to int
				    Integer repetition1=Integer.parseInt(repetition);
				    Integer weight1=Integer.parseInt(weight);
				    Activity activity= new Activity(username,muscle,sets1,repetition1,weight1,cal);
					hiber.AddActivity(activity);
					dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
					dispatcher.forward(request, response);
			    }
		}
			else 
			{
				request.setAttribute("UserNameDontExist", "1");
				request.setAttribute("ErrorPassWord", "1");
				request.setAttribute("UserNameExist", "1");
				request.setAttribute("ErrorUpDate","1");
				request.setAttribute("ErrorActivity","ErrorActivity");//Session
				dispatcher = request.getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
			
			
	}

	public void movetoupdate (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/update.jsp");
		 dispatcher.forward(request, response);
	   }

    public void mydata (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HibernateGymDAO hiber = HibernateGymDAO.getInstance();
		String username=(String) request.getSession().getAttribute("username");
		List<Activity> activities=hiber.getActivities(hiber.getUser(username));
		request.setAttribute("activities", activities);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/data.jsp");
		dispatcher.forward(request, response);
	}
    public void deleteinfomydata (HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException { //deleting activity that exists
    	HibernateGymDAO hiber = HibernateGymDAO.getInstance();
    	Activity activity;
    	Integer integerId= Integer.parseInt(id);//parsing string to int
    	activity=hiber.getActivity(integerId);
    	hiber.DeleteActivity(activity);
    	String username=(String) request.getSession().getAttribute("username");
    	List<Activity> activities=hiber.getActivities(hiber.getUser(username));
    	request.setAttribute("activities", activities);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/data.jsp");
		dispatcher.forward(request, response);
    }
    public void movetoupdateactivity(HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException { // Selects between two activity options and Moves to the correct page
    	RequestDispatcher dispatcher=null;
		Activity activity=null;
		HibernateGymDAO hiber = HibernateGymDAO.getInstance();
		Integer integerId= Integer.parseInt(id);//parsing String to int
		activity=hiber.getActivity(integerId);
		request.setAttribute("activity", activity);
		if(activity.getRunning_time()>0)
		{
			dispatcher = request.getServletContext().getRequestDispatcher("/updateactivityaerobic.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			dispatcher = request.getServletContext().getRequestDispatcher("/updateactivityanaerobic.jsp");
			dispatcher.forward(request, response);
		}
    }
    public void updateactivityrunning (HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException { //Change existing activity
    	RequestDispatcher dispatcher=null;
		List<Activity> activities=null;
		HibernateGymDAO hiber = HibernateGymDAO.getInstance();
		String running_time=request.getParameter("running_time");  
		String avg_speed=request.getParameter("avg_speed");
		if(!(running_time.isEmpty()) && !(avg_speed.isEmpty()))
		{
		if(avg_speed.matches("[0-9]+") && running_time.matches("[0-9]+")) //Verify whether the input activity is correct 
			if(!((running_time.length() == 0 && running_time.matches("[0]+")) || (avg_speed.length() == 0 && avg_speed.matches("[0]+"))))
			{
				//parsing string to int
				Integer running_timeINT=Integer.parseInt(running_time);
				Integer avg_speedINT=Integer.parseInt(avg_speed);
				String username=(String) request.getSession().getAttribute("username");
				Integer integerId= Integer.parseInt(id);
				Activity activity=hiber.getActivity(integerId);
				activity.setAvg_speed(avg_speedINT);
				activity.setRunning_time(running_timeINT);
				hiber.UpdateActivity(activity);
				activities=hiber.getActivities(hiber.getUser(username));
				request.setAttribute("activities", activities);
				dispatcher = request.getServletContext().getRequestDispatcher("/data.jsp");
				dispatcher.forward(request, response);
			}
		}
		else
		{
		request.setAttribute("UserNameDontExist", "1");
		request.setAttribute("ErrorPassWord", "1");
		request.setAttribute("UserNameExist", "1");
		request.setAttribute("ErrorUpDate","1");
		request.setAttribute("ErrorActivity","ErrorActivity");
		dispatcher = request.getServletContext().getRequestDispatcher("/error.jsp");
		dispatcher.forward(request, response);
		}
    }
    public void updateactivitymuscle (HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException { //Change existing activity
    	RequestDispatcher dispatcher=null;
    	HibernateGymDAO hiber = HibernateGymDAO.getInstance();
    	List<Activity> activities=null;
		String muscle=request.getParameter("select-muscle");  
		String sets=request.getParameter("sets");
		String repetition=request.getParameter("repetition");
		String weight=request.getParameter("weight");
		String username=(String) request.getSession().getAttribute("username");
		if(!(sets.isEmpty()) && !(repetition.isEmpty()) && !(weight.isEmpty()) && !(muscle.isEmpty() && muscle.compareTo("choose muscle")!=0))
		{
			if(sets.matches("[0-9]+")&& repetition.matches("[0-9]+") && weight.matches("[0-9]+"))  //Verify whether the input activity is correct  
				if(!(((sets.length() == 1 && sets.matches("[0]+")) || (repetition.length() == 1 && (repetition.matches("[0]+")) && weight.matches("[0]+")))))
				{
					Integer integerId= Integer.parseInt(id);
					Activity activity=hiber.getActivity(integerId);
					Integer sets1=Integer.parseInt(sets);
				    Integer repetition1=Integer.parseInt(repetition);
				    Integer weight1=Integer.parseInt(weight);
				    activity.setMuscle(muscle);
				    activity.setRepetition(repetition1);
				    activity.setSets(sets1);
				    activity.setWeight(weight1);
					hiber.UpdateActivity(activity);
					activities=hiber.getActivities(hiber.getUser(username));
					request.setAttribute("activities", activities);
					dispatcher = request.getServletContext().getRequestDispatcher("/data.jsp");
					dispatcher.forward(request, response);
			    }
		}
			else 
			{
				request.setAttribute("UserNameDontExist", "1");
				request.setAttribute("ErrorPassWord", "1");
				request.setAttribute("UserNameExist", "1");
				request.setAttribute("ErrorUpDate","1");
				request.setAttribute("ErrorActivity","ErrorActivity");
				dispatcher = request.getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
    }
    public void backtohome (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //move back to home
    	
	   RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
	   dispatcher.forward(request, response);
       }
    
    public void movegraph (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // go to the graph
    	HibernateGymDAO hiber = HibernateGymDAO.getInstance();
    	Calendar d=Calendar.getInstance();
    	List<Activity> activitiesWeekly=new ArrayList<Activity>();
    	int days[]= new int[7];
    	Activity act=null;
    	Activity actTest=null;
    	String username=(String) request.getSession().getAttribute("username");
		List<Activity> activities=hiber.getActivities(hiber.getUser(username));
        System.out.println("  == "+Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
		for(int i=0;i<activities.size();i++)
		{
			actTest=activities.get(i);
			if(actTest.getdate().get(Calendar.DAY_OF_YEAR)>= Calendar.getInstance().get(Calendar.DAY_OF_YEAR)-7)//get all activities of the past week and aerobic Training
			{
				if(actTest.getAvg_speed()!=0)
				{
				act=activities.get(i);
				activitiesWeekly.add(act);
				}
			}
		}
		for(int i=0;i<activitiesWeekly.size();i++)
		{
			days[(activitiesWeekly.get(i).getdate().get(Calendar.DAY_OF_WEEK))-1]+=activitiesWeekly.get(i).getAvg_speed()*activitiesWeekly.get(i).getRunning_time();
		}
		request.setAttribute("days", days);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/graph.jsp");
		dispatcher.forward(request, response);
    }
    public void movegraphmuscle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HibernateGymDAO hiber = HibernateGymDAO.getInstance();
    	Calendar d=Calendar.getInstance();//date of today
    	List<Activity> activitiesWeekly=new ArrayList<Activity>();
    	int days[]= new int[7];//arry that contains all Calculations of the past week
    	Activity act=null;
    	String username=(String) request.getSession().getAttribute("username");
		List<Activity> activities=hiber.getActivities(hiber.getUser(username));
        System.out.println("  == "+Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
		for(int i=0;i<activities.size();i++)
		{
			if(activities.get(i).getdate().get(Calendar.DAY_OF_YEAR)> Calendar.getInstance().get(Calendar.DAY_OF_YEAR)-7)//get all activities of the past week and anaerobic Training
			{
				if(activities.get(i).getAvg_speed()==0)
				{
				act=activities.get(i);
				activitiesWeekly.add(act);
				}
			}
		}
		for(int i=0;i<activitiesWeekly.size();i++)
		{
			days[(activitiesWeekly.get(i).getdate().get(Calendar.DAY_OF_WEEK))-1]+=activitiesWeekly.get(i).getRepetition()*activitiesWeekly.get(i).getSets()*activitiesWeekly.get(i).getWeight();//Calculations
		}
		request.setAttribute("days", days);//save the arry Calculations
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/graphmuscle.jsp");
		dispatcher.forward(request, response);
    }
}



