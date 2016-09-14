package struts.servlet;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.common.CommonName;
import bean.common.DoubleColorBall;

import common.util.MapUtil;
import common.util.file.DoubleColorBallTxtFileUtil;
import common.util.file.TxtFileUtil;

public class DoubleColorBallServlet extends BaseServlet{

	private String historyFile;
	
	private DoubleColorBall doubleColorBall;

	private DoubleColorBallTxtFileUtil tfu = new DoubleColorBallTxtFileUtil();

	public String queryHistory(HttpServletRequest req,HttpServletResponse res) throws FileNotFoundException{
		
		System.out.println("--DoubleColorBallServlet class,queryHistory method----");
		
		if(context==null){
			context = (Map<String, String>) req.getSession().getAttribute("context");
		}
		
		List<DoubleColorBall> l = new ArrayList<DoubleColorBall>();
		String file = context.get(CommonName.DOUBLE_COLOR_HISTORY_FILE);
		String fileName = context.get(CommonName.DOUBLE_COLOR_HISTORY_NAME);
		try{
			tfu.readFile(file+"/"+fileName, true, l);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		req.getSession().setAttribute("historyList", l);
		System.out.println();
		return "success";
	}
	
	public String add(HttpServletRequest req,HttpServletResponse res) throws FileNotFoundException{
		System.out.println("--DoubleColorBallServlet class,add method----");
		if(context==null){
			context = (Map<String, String>) req.getSession().getAttribute("context");
		}
		try{
			doubleColorBall = (DoubleColorBall)paramToEntity(new MapUtil().changeRequestParamterMapToNormal(req.getParameterMap()),new DoubleColorBall());
			System.out.println(doubleColorBall.getIssue());
			
			String file = context.get(CommonName.DOUBLE_COLOR_HISTORY_FILE);
			String fileName = context.get(CommonName.DOUBLE_COLOR_HISTORY_NAME);
			
			tfu.writeFile(file+"/"+fileName,doubleColorBall);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "toList";
	}
	
	public String getHistoryFile() {
		return historyFile;
	}

	public void setHistoryFile(String historyFile) {
		this.historyFile = historyFile;
	}
	
	public DoubleColorBall getDoubleColorBall() {
		return doubleColorBall;
	}

	public void setDoubleColorBall(DoubleColorBall doubleColorBall) {
		this.doubleColorBall = doubleColorBall;
	}
}
