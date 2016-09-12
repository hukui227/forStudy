package bean.common;

import java.util.HashMap;

public class ModeAndView {

	private String servletName;
	
	private HashMap<String,String> methodName;
	
	private HashMap<String,String> resultMap;
	
	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public HashMap<String,String> getMethodName() {
		return methodName;
	}

	public void setMethodName(HashMap<String,String> methodName) {
		this.methodName = methodName;
	}

	public HashMap<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, String> resultMap) {
		this.resultMap = resultMap;
	}
	
}
