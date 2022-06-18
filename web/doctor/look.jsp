<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>查看--理博软件2016</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/ckeditor/ckeditor.js"></script>
 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
            window.location.href='doctorServlet?action=list';
		 });
    });
    </script>
</head>
<body>
<form action="index.jsp" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${doctor.name}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td>身份证</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td>${doctor.cardno}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td>${doctor.phone}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>${doctor.sex == 1 ? "男":"女"}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>${doctor.age}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td>${doctor.birthday}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td>${doctor.email}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td>${doctor.policlinic.pname}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td>${doctor.edu.ename}</td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${doctor.remark}</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button"  class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>