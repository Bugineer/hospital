<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改医生--理博软件2016</title>
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
<form action="doctorServlet" method="post" class="definewidth m20">
    <input type="hidden" name="action" value="save"/>
    <input type="hidden" name="did" value="${doctor.did}"/>
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="name" value="${doctor.name}" readonly /></td>
    </tr>
    
    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td><input type="text" name="cardno" value="${doctor.cardno}" readonly /></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td><input type="text" name="phone" value="${doctor.phone}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="sex" value="1" ${doctor.sex == 1 ? "checked":""}/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" ${doctor.sex == 0 ? "checked":""} value="0"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><input type="date" name="birthday" value="${doctor.birthday}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>${doctor.age}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><input type="text" name="email" value="${doctor.email}"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td><select name="department">
            <c:forEach items="${pList}" var="p">
                <option value="${p.pid}" ${doctor.department == p.pid ? "selected":""} >${p.pname}</option>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td><select name="education">
            <c:forEach items="${eList}" var="e">
                <option value="${e.eid}" ${doctor.education == e.eid ? "selected":""} >${e.ename}</option>
            </c:forEach>
        </select></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><textarea name="remark">${doctor.remark}</textarea></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>