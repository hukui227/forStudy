package struts.servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该JSP程序是用来测试与MySQL数据库的连接，
 * 需要一个数据库：LearnJSP，和其中一个表：userinfo
 * 表中有两个字段分别为：UserName varchar (20) not null,UserPwd varchar (20) not null
 */
public class LoginServlet extends HttpServlet implements Servlet{

	/**
	 *
	 */
	 private static final long serialVersionUID = 1L;
	 
	public LoginServlet ()
	 {
	 // TODO Auto-generated constructor stub
	 }
	 /*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	 @Override
	 protected void doGet ( HttpServletRequest arg0, HttpServletResponse arg1 )  throws ServletException, IOException{
		 
	 }
	 /*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	 @Override
	 protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		response.setContentType("text/html");
		String result = "";
		// 获取用户名
		String sUserName = request.getParameter("txtUserName");
		if (sUserName == "" || sUserName == null || sUserName.length() > 20) {
			try {
				result = "请输入用户名（不超过20字符）！";
				request.setAttribute("ErrorUserName", result);
				response.sendRedirect("login.html");
			} catch (Exception e) {
			}
		}
		// 获取密码
		String sPasswd = request.getParameter("txtPassword");
		if (sPasswd == "" || sPasswd == null || sPasswd.length() > 20) {
			try {
				result = "请输入密码（不超过20字符）！";
				request.setAttribute("ErrorPassword", result);
				response.sendRedirect("login.html");
			} catch (Exception e) {
			}
		}
		// 登记JDBC驱动程序
//		try {
//			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("InstantiationException");
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("IllegalAccessException");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("ClassNotFoundException");
//		}
//		// 连接参数与Access不同
//		String url = "jdbc:mysql://localhost/LearnJSP";
//		// 建立连接
//		java.sql.Connection connection = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			connection = DriverManager.getConnection(url, "root", "011124");
//			stmt = connection.createStatement();
//			// SQL语句
//			String sql = "select * from userinfo where username='" + sUserName
//					+ "' and userpwd = '" + sPasswd + "'";
//			rs = stmt.executeQuery(sql);// 返回查询结果
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			if (rs.next())// 如果记录集非空，表明有匹配的用户名和密码，登陆成功
//			{
//				// 登录成功后将sUserName设置为session变量的UserName
//				// 这样在后面就可以通过 session.getAttribute("UserName") 来获取用户名，
//				// 同时这样还可以作为用户登录与否的判断依据
//				request.getSession().setAttribute("UserName", sUserName);
//				response.sendRedirect("login_success.jsp");
//			} else {
//				// 否则登录失败
//				// response.sendRedirect ( "MyJsp.jsp" );
//				response.sendRedirect("login_failure.jsp");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			if (null != rs) {
//				rs.close();
//			}
//			if (null != stmt) {
//				stmt.close();
//			}
//			if (null != connection) {
//				connection.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		request.getSession().setAttribute("UserName", sUserName);
		request.getSession().setAttribute("defaultUrl", "login.html");
//		response.sendRedirect("login_success.jsp");
	}
	 
	 
	 public String login(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.doPost(request, response);
		return "success";
	 }
}
