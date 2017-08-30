<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen" type="text/css" />
<script type="text/javascript">
function Check(){
	var name = document.Register.registerName.value;
	var password = document.Register.registerPassword.value;
	var password2 = document.Register.registerPassword2.value;
	if(name == "" )//判断用户名中是否存在空格
		alert("请填写用户名!");
	else if( password == "")
		alert("请填写密码!");
	else if(password2 == "")
		alert("请填写密码!");
	else if(password2 != password)
		alert("两次密码不相同，请重新确认！")
	else 
		document.Register.submit();	
}
</script>
</head>
<body>
<!-- 该页面是注册页面，用户填表后判断是否信息在数据库中存在，
如不存在将信息传递给数据库，若存在，跳转到注册失败页面 -->
<form action="registerCheck" name="Register" method="post">
<h1 >注册页面</h1>
<p >用户名:<input name="registerName" type="text"></p>
<p >密&nbsp;&nbsp;&nbsp;码:<input name="registerPassword" type="password"></p>
<p >确认密码:<input name="registerPassword2" type="password"></p>
<p >
	<input name ="B1" value="重置" type="reset">
	<input name="B2" value="提交" type="button" onclick="Check()"> 
</p>
</form>
</body>
</html>