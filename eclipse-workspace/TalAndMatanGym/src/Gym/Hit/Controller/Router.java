package Gym.Hit.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Router
 */
@WebServlet("/controller/*")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Router() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Class clazz = null;
		Object ob = null;
		Method method = null;
		try {
			String sb = request.getRequestURI();
			StringTokenizer tokenizer = new StringTokenizer(sb, "/"); // '/' Separates between tokens
			int numberOfTokens = tokenizer.countTokens();
			tokenizer.nextToken();
			numberOfTokens--;//do not need this token
			String controller = "empty";//Initial startup
			String action = "empty";
			String id="empty";
			controller = tokenizer.nextToken();//Every time you read a token you take down token
			numberOfTokens--;
			if(numberOfTokens>0)
			{
				action = tokenizer.nextToken();
				numberOfTokens--;
			}
			System.out.println("  "+ numberOfTokens);
			if(numberOfTokens>0)//there is another string to read
			{
				id=tokenizer.nextToken();
				clazz = Class.forName("Gym.Hit.Controller." + controller);
				ob = clazz.newInstance();
				method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class,String.class);
				method.invoke(ob, request, response,id);//Only functions of type (action,request,response,id(activity))
				
			}
			else
			{
			clazz = Class.forName("Gym.Hit.Controller." + controller);
			ob = clazz.newInstance();
			method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(ob, request, response);//Only functions of type (action,request,response) no need of id string
			}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
