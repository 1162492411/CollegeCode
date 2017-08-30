<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入bootStrap源文件 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-tdeme.min.css"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- 引入bootStrap Validatery源文件 -->
<link href="https://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<title>Insert title here</title>
</head>
<body>
    <form style="width:400px"> 
        <div class="form-group"> 
            <label>Username</label> 
            <input type="text" class="form-control" name="username" /> 
        </div> 
        <div class="form-group"> 
            <label>Email address</label> 
            <input type="text" class="form-control" name="email" /> 
        </div> 
    </form> 
 <script type="text/javascript">
 $(document).ready(function() { 
     $('.registerForm').bootstrapValidator({ 
         message: 'This value is not valid', 
         feedbackIcons: { 
             valid: 'glyphicon glyphicon-ok', 
             invalid: 'glyphicon glyphicon-remove', 
             validating: 'glyphicon glyphicon-refresh' 
         }, 
         fields: { 
             username: { 
                 message: 'The username is not valid', 
                 validators: { 
                     notEmpty: { 
                         message: 'The username is required and cannot be empty' 
                     }, 
                     stringLength: { 
                         min: 6, 
                         max: 30, 
                         message: 'The username must be more than 6 and less than 30 characters long' 
                     }, 
                     regexp: { 
                         regexp: /^[a-zA-Z0-9_]+$/, 
                         message: 'The username can only consist of alphabetical, number and underscore' 
                     } 
                 } 
             }, 
             email: { 
                 validators: { 
                     notEmpty: { 
                         message: 'The email is required and cannot be empty' 
                     }, 
                     emailAddress: { 
                         message: 'The input is not a valid email address' 
                     } 
                 } 
             } 
         } 
     }); 
 }); 
 </script>
</body>
</html>