package struts.dispatcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.common.CommonName;
import bean.common.ModeAndView;

public class DispatcherServlet implements Filter{

	private Map<String,ModeAndView> mapping = null;
	
	private FilterConfig filterConfig;
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void destroy() {
		System.out.println("dispatcher Servlet destroy");
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("dispatcher Servlet doFilter");
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
		
        HttpSession se = req.getSession();
        if(se.getAttribute("context")==null){
        	Object o = this.filterConfig.getServletContext().getAttribute("context");
        	if(o!=null)
        		req.getSession().setAttribute("context", o);
        }
		String servletPath = req.getServletPath();
//		String uri = req.getRequestURI();
//		String contentPath = req.getContextPath();
//		String localAddr = req.getLocalAddr();
		
		if(servletPath.length()>0){
			String pathStr = servletPath.substring(1);
			String actionName = "";
			if(pathStr.contains(".")){
				actionName = pathStr.substring(pathStr.indexOf('.')+1);
				System.out.println(actionName);
			}
			if(actionName.toLowerCase().equals("do")||actionName.toLowerCase().equals("action")){//带do或action请求的，匹配控制，转处理类处理
				String reqMethodName = pathStr.substring(0,pathStr.indexOf('.'));
				String[] names = reqMethodName.split("/");
				if(names!=null&&names.length>=2)
					actionMapping(names,req,res);
			}else if(actionName.length()==0){//一切没带请求动作后缀的，都重定向到首页
				//页面重定向
				res.sendRedirect("http://localhost:8080/forStudy/index.jsp");
			}else{//一切jsp请求的，不做处理
				//交给过滤链继续处理
				
				chain.doFilter(req, res);
			}
		}
			
	}

	private void actionMapping(String[] names,HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		String packageCode = names[0];
		String actionName = names[1];
		ModeAndView mv = mapping.get(packageCode);
		if(mv==null){
			
		}else{
			String servletName = mv.getServletName();
			String methodName = mv.getMethodName().get(actionName);
			HashMap<String,String> resultMap = mv.getResultMap();
			if(servletName!=null){
				String result = doProcess(req,res,servletName,methodName,actionName);
				String urlRes = resultMap.get(result);
				req.getSession().setAttribute("appPath", req.getContextPath());
				req.getRequestDispatcher("/WEB-INF/jsp/"+urlRes).forward(req, res);
			}
		}
	}
	
	private String doProcess(HttpServletRequest req,HttpServletResponse res,String serviceName,String methodName,String actionName){
		String result = "success";
		//反射机制：
		try{
			Class<?> ser = Class.forName("struts.servlet."+serviceName);//1，需要知道实例化类的路径全称。使用Class.forName()方法。
			Object obj = ser.newInstance();//2，对这个class对象实例化后返回Object对象，
			if(methodName!=null){
				Method m = ser.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);//需要使用实例化后对象，获取指定方法对象
				result = (String)m.invoke(obj, req,res);//执行该实例化对象的知道方法使用invoke方法，注意参数一致，并且第一个参数是实例化后的的对象引用，
			}else{
				result = actionName.substring(10);//toForward_
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("dispatcher Servlet init");
		this.filterConfig = filterConfig;
		initLogging();
		
		mapping = new HashMap<String,ModeAndView>();
		
		ModeAndView mv = new ModeAndView();
		mv.setServletName("DoubleColorBallServlet");
		HashMap<String,String> actionAndMethod = new HashMap<String,String>();
		actionAndMethod.put("queryHistory", "queryHistory");
		mv.setMethodName(actionAndMethod);
		HashMap<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("success", "doubleColorBall/list.jsp");
		mv.setResultMap(resultMap);
		mapping.put("doubleColorBall", mv);
		
		mv = new ModeAndView();
		mv.setServletName("LoginServlet");
		actionAndMethod = new HashMap<String,String>();
		actionAndMethod.put("login", "login");
		mv.setMethodName(actionAndMethod);
		resultMap = new HashMap<String,String>();
		resultMap.put("success", "sys/login_success.jsp");
		resultMap.put("faile", "sys/login_failure.jsp");
		resultMap.put("top", "sys/top.jsp");
		resultMap.put("left", "sys/left.jsp");
		mv.setResultMap(resultMap);
		mapping.put("common", mv);
		
		//初始化配置文件配置信息
		ServletContext servletContext = filterConfig.getServletContext();
		String configFile = servletContext.getRealPath(servletContext.getInitParameter("configFile"));
		if(configFile!=null){
			Map<String,String> context = initConfigFile(configFile);
			filterConfig.getServletContext().setAttribute("context", context);
		}
		
	}

	private Map<String,String> initConfigFile(String fileUrl) {
		Map<String,String> result = new HashMap<String,String>();
		InputStream in = null;
		try{
			in = new FileInputStream(fileUrl);
			Properties p = new Properties();
			p.load(in);
			for(Object key:p.keySet()){
				result.put((String)key, p.getProperty((String)key));
			}
			
		}catch(IOException ie){
			ie.printStackTrace();
		}finally{
			if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		
		return result;
	}
	
	
	private void initLogging() {
		
	}
}
