package struts.servlet;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet{

	public Map<String,String> context;

	
	//利用反射机制，对对象属性设值
		public Object paramToEntity(Map param,Object obj) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
			Class<?> c = obj.getClass();
			Field[] fields = c.getDeclaredFields();
			for(Field f: fields){
				f.setAccessible(true);
				String fieldName= f.getName();
				if(param.containsKey(fieldName)){
					Object value = param.get(fieldName);
					if(value!=null&&value.toString().length()>0)
					f.set(obj, value.toString());
				}
			}
			return obj;
		}
	
}
