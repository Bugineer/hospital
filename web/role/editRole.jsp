<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

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
</head>
<body>
<form id="editRoleForm" action="roleServlet?action=edit" method="post" class="definewidth m20" >
<table class="table table-bordered table-hover definewidth m10">
    <input type="hidden" name="roleId" value="${role.roleId}"/>
    <tr>
        <td width="10%" class="tableleft">角色名称</td>
        <td><input type="text" name="roleName" id="roleName" value='${role.roleName}'/><span id="roleName_span"></span></td>
    </tr>
    <tr>
        <td class="tableleft">状态</td>
        <td>
            <input type="radio" name="status" value="1" ${role.status == 1 ? "checked":""} /> 启用
           <input type="radio" name="status" value="0"  ${role.status == 0 ? "checked":""} /> 禁用
        </td>
    </tr>
    <tr>
        <td class="tableleft">权限</td>
        <td>
            <c:forEach items="${modalList}" var="modal">
                <ul><label class='checkbox inline'><input type='checkbox' <c:forEach items="${role.modalList }" var="own">${own.id == modal.id ? "checked":"" }</c:forEach> name='modals[]' value="${modal.id}"/>${modal.name}</label></ul>
            </c:forEach>
		</td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">更新</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    $(function () {
        $(':checkbox[name="modals[]"]').click(function () {
            $(':checkbox', $(this).closest('li')).prop('checked', this.checked);
        });

		$('#backid').click(function(){
				window.location.href="roleServlet?action=list";
		 });

        //判断角色名是否唯一
        $("#roleName").blur(function () {
            var roleName = this.value;
            if(roleName == null || roleName.trim() == "") {
                $("#roleName_span").text("角色名不能为空!");
                return false;
            }
            //判断 roleName 是否改变
            if(roleName != '${role.roleName}') {
                checkedRoleName(roleName);
            }
        }).focus(function () {
            $("#roleName_span").text("");
        });




        //表单提交校验
        $("#editRoleForm").submit(function () {
            var roleName = $("#roleName").val();
            if(roleName == null || roleName.trim() == "") {
                $("#roleName_span").text("角色名不能为空!");
                return false;
            }
            if(roleName != '${role.roleName}') {
                checkedRoleName(roleName);
            }
            var span = $("#roleName_span").text();
            if(span != "") {
                return false;
            }
        });
        
    });

    function checkedRoleName(roleName) {
        $.ajax({
            url:"roleServlet?action=checkedRoleName",
            data:"roleName="+roleName,
            type:"post",
            success:function (data) {
                $("#roleName_span").text(data);
            }
        })
    }

</script>