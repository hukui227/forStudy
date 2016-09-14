<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
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
  	<form action="doubleColorBall/add.action">
    <table width="80%" border="solid 1px " bordercolor="green">
    	<tr align="center">
    		<td>期号</td>
    		<td colspan="6" style="color: red">红色球</td>
    		<td style="color: blue">蓝色球</td>
    	</tr>
    	<tr align="center">
    		<td><input type="text" name="issue" size="10px"/>  </td>
    		<td><input type="text" name="redOne" size="5px"/>  </td>
    		<td><input type="text" name="redTwo" size="5px"/>  </td>
    		<td><input type="text" name="redThree" size="5px"/>  </td>
    		<td><input type="text" name="redFour" size="5px"/>  </td>
    		<td><input type="text" name="redFive" size="5px"/>  </td>
    		<td><input type="text" name="redSix" size="5px"/>  </td>
    		<td><input type="text" name="blue" size="5px"/>  </td>
    	</tr>
    </table>
    <table width="80%">
    	<tr><td align="right">
    		<input type="button" onclick="add()"  value="提交">
    	</td></tr>	
    </table>
    </form>
  </body>
  <script type="text/javascript">
  	function add(){
  		
  		document.forms[0].submit();
  	}
  </script>
</html>
