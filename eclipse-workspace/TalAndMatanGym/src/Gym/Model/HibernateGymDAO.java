package Gym.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
public class HibernateGymDAO implements IGymDAO {
    private static HibernateGymDAO ourInstance ;
    private SessionFactory getSession() {
        return SessionFactoryDao.getSession();
    }

    public synchronized static HibernateGymDAO getInstance() {
        if (ourInstance == null) {
            ourInstance = new HibernateGymDAO();
        }
        return ourInstance;
    }
    public boolean checkuser(String username) {//check user by user name only
        Session session = null;
        Transaction transaction = null;
        List<User> users = null;
        boolean flag=false;
        Query q=null;
        try {
        	User user=null;
            session = getSession().openSession();
            transaction = session.beginTransaction();
            q = session.createQuery("from User U where U.username = :username"); //sql query
            q.setParameter("username", username);
            transaction.commit();
            users = q.list(); //get query list
			if(!users.isEmpty()) //check if users list is not empty
            {
            user=users.get(0);
			}
           
            if(user!=null)
            {
                flag= true;
            }
        }
        catch(HibernateException e){
            if (session.getTransaction() != null)
                System.out.println("getUser");
            session.getTransaction().rollback();
        } finally{
            session.close();
        }
        return flag;

    }

    private HibernateGymDAO() {
    }

    @SuppressWarnings({"null", "unchecked" })
	@Override
	public boolean checkexists(String username,String password) { //check user by user name and password
        Session session = null;
        Transaction transaction = null;
        List<User> users = null;
        boolean flag=false;
        Query q=null;
        try {
        	User user=null;
            session = getSession().openSession();
            transaction = session.beginTransaction();
            q = session.createQuery("from User U where U.username = :username AND U.password = :password");
            q.setParameter("username", username);
            q.setParameter("password", password);
            transaction.commit();
            users = q.list();
			if((users.isEmpty())==false)
            {
            user=users.get(0);
            flag=true;
			}
        }
        catch(HibernateException e){
            if (session.getTransaction() != null)
                System.out.println("getUser");
            session.getTransaction().rollback();
        } finally{
            session.close(); 
        }
        return flag;
        

    }
    @Override
    public User getUser(String username) { //get the user name of user
        Session session = null;
        Transaction transaction = null;
        Query q=null;
        User user=null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            q = session.createQuery("from User U where U.username = :username");
            q.setParameter("username", username);
            transaction.commit();
            List<User> users = q.list();
            if(!users.isEmpty())
            {
            user=users.get(0);
			}
        }
        catch(HibernateException e){
            if (session.getTransaction() != null)
                System.out.println("getUser");
            session.getTransaction().rollback();
        } finally{
            session.close();
        }
        return user;
            
    }

    @Override
    public void AddUser(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            session.save(user);//add new user to sql table
            transaction.commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null)
                System.out.println("AddUser");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }


    @Override
    public void UpdateUser(User user) { //Each user has an id so an update can be made
        Session session = null;
        Transaction transaction = null;
        try {

            session= getSession().openSession();
            transaction = session.beginTransaction();
            session.update(user);//save new user at sql table
            transaction.commit();
        }
        catch (HibernateException e) {
            if (session.getTransaction() != null)
                System.out.println(" UpdateUser");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void DeleteUser(User user) {
        Session session = null;
        Transaction transaction=null;
        try {
            session= getSession().openSession();
            transaction = session.beginTransaction();
            session.delete(user);// delete from table
            transaction.commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null)
                System.out.println("DeleteUser");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    @Override
	public List<Activity> getActivities(User user) {
    	{
            String username = user.getUsername();
            Session session = null;
            Transaction transaction=null;
            List<Activity> activitys = null;
            Query q=null;
            try {
                session = getSession().openSession();
                transaction=session.beginTransaction();
                q= session.createQuery("from Activity activity where activity.username = :username");
                q.setParameter("username", username);
                transaction.commit();
                activitys = q.list();
            } catch (HibernateException e) {
                if (session.getTransaction() != null)
                    System.out.println("somthing Went wrong.. sorry");
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
            return activitys;
        }
	}

    @Override
    public Activity getActivity(int id) {
        List<Activity> activitys = null;
        Activity activity=null;
        Session session = null;
        Transaction transaction=null;
        Query q=null;
        try {
            session = getSession().openSession();
            transaction=session.beginTransaction();
            q = session.createQuery("from Activity activity where activity.id = :id");
            q.setParameter("id",id);
            transaction.commit();
            activitys = q.list();
            if(!activitys.isEmpty())
            	activity=activitys.get(0);
        } catch (HibernateException e) {
            if (session.getTransaction() != null)
                System.out.println("getActivity");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return activity;
    }

    @Override
    public void AddActivity(Activity activity) {
        Session session = null;
        Transaction transaction=null;
        try {
            session = getSession().openSession();
            transaction=session.beginTransaction();
            session.save(activity);
            transaction.commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null)
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void UpdateActivity(Activity activity) {
        Session session = null;
        Transaction transaction=null;
        try {
            session = getSession().openSession();
            transaction=session.beginTransaction();
            session.update(activity);
            transaction.commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null)
                System.out.println("UpdateActivity");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }


    @Override
    public void DeleteActivity(Activity activity) {
        Session session = null;
        Transaction transaction=null;
        try {
            session= getSession().openSession();
            transaction=session.beginTransaction();
            session.delete(activity);
            transaction.commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null)
                System.out.println("DeleteActivity");
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}


