<%@ page language="java" import="java.util.*,bean.common.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
  	Object o = request.getSession().getAttribute("historyList");
  	List<DoubleColorBall> l = new ArrayList<DoubleColorBall>();
  	if(o!=null){
  		l = (List<DoubleColorBall>)o;
  	}
  %>
  <table width="80%" border="solid 1px" bordercolor="green">
  	<tr><td width="10%" align="center">期号</td><td colspan="6" align="center" style="color: red">红色球</td><td width="10%" align="center" style="color: blue">蓝色球</td></tr>
  	
   <%
   	for(DoubleColorBall dcb:l){
   %>
   	<tr>
   	<td><%=dcb.getIssue() %></td><td style="color: red"><%=dcb.getRedOne() %></td><td style="color: red"><%=dcb.getRedTwo() %></td><td style="color: red"><%=dcb.getRedThree() %></td><td style="color: red"><%=dcb.getRedFour() %></td><td style="color: red"><%=dcb.getRedFive() %></td><td style="color: red"><%=dcb.getRedSix() %></td><td style="color: blue"><%=dcb.getBlue() %>
   	</tr>
   <%
   	}
   %>
   </table>
   <table width="80%">
   		<tr><td align="right"><button>添加</button></td></tr>
   </table>
  </body>
</html>
