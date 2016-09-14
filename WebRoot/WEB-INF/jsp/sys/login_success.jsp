<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>home for study</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		.header-class{
			background:#96b97d;
			height:34px; 
			margin:0px;
			display: block;
			padding-left: 10px;
			padding-right: 10px;
		}
		.row{
			height: 34px;
			max-width: 1140px;
			min-width: 755px;
			padding:0px;
			margin-left:auto;
			margin-right:auto;
			margin-top:0px;
			margin-bottom:0px;
		}
		.col-nav{
			height: 24px;
			float: left;
			padding-left: 20px;
			padding-top: 10px;
			margin-right:1%;
			text-transform: uppercase;
			border-style: none;
		}
		.ul-class{
			list-style-position: outside;
			list-style-type: none;
			margin:0px;
			padding:0px;
		}
		.current{
			font-size: 13px;
			color:rgb(255, 255, 255);
			text-align: left;
			text-decoration: none solid rgb(255, 255, 255);
		}
		.container-main{
			margin-top: 20px;
			padding-left: 10px;
			padding-right: 10px;
			border:solid 0px;
			overflow:auto;<!-- 里面的内容自动撑开外层大小 -->
		}
		.left-content{
			float: left;
			padding:0px;
			margin-right: 1%;
			width:16%;
			border:solid 0px;
			background: rgb(242, 242, 242);
		}
		.middle-content{
		
		}
		.left-menu-list{
			margin-bottom: 20px;
			margin-left: 0px;
			margin-right: 0px;
			margin-top: 0px;
			height: 410px;
			border:solid 0px;
		}
		.left-menu{
			padding:4px;
			margin:0px;
			height: 32px;
			border:solid 1px rgba(0, 0, 0, 0.0980392);
		}
		.navto-nav{
			margin-bottom: 8px;
			margin-left: 2px;
			margin-right: 2px;
			margin-top: 8px;
			padding:0px;
			height: 16px;
		}
		
	</style>
  </head>
  <%
  	String defaultUrl = (String)session.getAttribute("defaultUrl");
  %>
  <frameset rows="59,*" frameborder="no" border="0" framespacing="0">
  	<frame src="sys/toForward_top.action" noresize="noresize" frameborder="0" name="topFrame" marginwidth="0" marginheight="0" scrolling="no">
  	<frameset  rows="*" cols="195,*" id="frame">
  	<frame src="sys/toForward_left.action" noresize="noresize" frameborder="1" name="topFrame" marginwidth="0" marginheight="0" scrolling="no">
  	<frame src="<%=defaultUrl %>" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="yes">
  	</frameset>
  <noframes>
	<body></body>
  </noframes>
  
  </frameset>
  
</html>
