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
    
     <script type="text/javascript">
     $(function () {
		$('#newNav').click(function(){
				window.location.href="roleServlet?action=toAdd";
		 });
         showInfo();
    });
	
	
    function checkall(){
			var alls=document.getElementsByName("check");
			var ch=document.getElementById("checkall");
			if(ch.checked){
				for(var i=0;i<alls.length;i++){
					alls[i].checked=true;	
				}	
			}else{
				for(var i=0;i<alls.length;i++){
					alls[i].checked=false;	
				}	
			}
		}

        //批量删除
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认删除?")){
                    delMore(ids);
				}
			}else{
				alert("请选中要删除的项");
			}
		}
    </script>   
    
</head>
<body>
<form id="roleListForm" class="form-inline definewidth m20" action="roleServlet" method="get">
    角色名称：
    <input type="text" name="roleName" id="roleName" class="abc input-default" placeholder="" value="${roleName}">&nbsp;&nbsp;
    <input type="hidden" name="action" value="list">
    <input type="hidden" name="pageNo" value="1">
    <input type="hidden" name="pageSize" value="10">
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
    	<th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>角色名称</th>
        <th>状态</th>
        <th width="10%">操作</th>
    </tr>
    </thead>

        <c:forEach items="${rolePage.eList}" var="role">
            <tr>
                <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${role.roleId}"></td>
                <td>${role.roleName}</td>
                <td>${role.status == 1 ? "启用":"禁用"}</td>
                <td><a href="javascript:toEdit(${role.roleId})">编辑</a>&nbsp;&nbsp;&nbsp;<a href="javascript:delByRoleId('${role.roleId}','${role.roleName}');">删除</a> </td>
            </tr>
        </c:forEach>
</table>
        
        
   <table class="table table-bordered table-hover definewidth m10" >
  	<tr>
        <th colspan="5">
            <div class="inline pull-right page">
                <span>${rolePage.totalPageCount} 条记录 ${rolePage.currentPageNo}/${rolePage.totalPageSize} 页</span>
                <span>
                    每页显示
                    <select name="pageSize" style="width: 50px" id="pageSize">
                        <option value="1" ${rolePage.pageSize == 1? "selected":""}>1</option>
                        <option value="5" ${rolePage.pageSize == 5? "selected":""}>5</option>
                        <option value="10" ${rolePage.pageSize == 10? "selected":""}>10</option>
                        <option value="20" ${rolePage.pageSize == 20? "selected":""}>20</option>
                    </select>条
                </span>
                <c:if test="${rolePage.currentPageNo > 1}">
                    <a href="javaScript:submitForm(1,${rolePage.pageSize})">首页</a>
                    <a href="javaScript:submitForm('${rolePage.currentPageNo-1}','${rolePage.pageSize}')">上一页</a>
                </c:if>
                转到
                <select name="pageNo" style="width: 50px" id="pageNo">
                   <c:forEach begin="1" end="${rolePage.totalPageSize}" var="pageNo">
                       <option value="${pageNo}" ${rolePage.currentPageNo == pageNo? "selected":""}>${pageNo}</option>
                   </c:forEach>
                </select>页
                <c:if test="${rolePage.currentPageNo < rolePage.totalPageSize}">
                    <a href="javaScript:submitForm('${rolePage.currentPageNo+1}','${rolePage.pageSize}')">下一页</a>
                    <a href="javaScript:submitForm('${rolePage.totalPageSize}','${rolePage.pageSize}')">尾页</a>
                </c:if>
            </div>
            <div>
                <button type="button" class="btn btn-success" id="newNav">添加角色 </button>&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button>
            </div>
        </th>
    </tr>
  </table>     
 </body>
<script>
    $("#pageSize").change(function () {
        var pageSize = this.value;
        //设置分页参数
        $("input[name='pageSize']").val(pageSize);
        $("#roleListForm").submit();
        // window.location.href="roleServlet?action=list&pageNo=1&pageSize="+pageSize;
    });
    $("#pageNo").change(function () {
        var pageNo = this.value;
        var pageSize = $("#pageSize").val();
        //设置分页参数
        $("input[name='pageNo']").val(pageNo);
        $("input[name='pageSize']").val(pageSize);
        $("#roleListForm").submit();
        // window.location.href="roleServlet?action=list&pageNo="+pageNo+"&pageSize="+pageSize;
    });

    //提交查询表单
    function submitForm(pageNo,pageSize) {
        $("input[name='pageNo']").val(pageNo);
        $("input[name='pageSize']").val(pageSize);
        $("#roleListForm").submit();
    }

    //根据roleId 删除
    function delByRoleId(roleId,roleName) {
        if(!confirm("你确定要删除:["+roleName+"]?")) {
            return;
        }
        $.ajax({
            url:"roleServlet?action=del",
            data:"roleId="+roleId,
            type:"post",
            success:function (data) {
                if(data) {
                    alert("["+roleName+"] 删除成功!");
                    //删除成功刷新页面
                    submitForm(1,10);
                } else {
                    alert("["+roleName+"] 删除失败!");
                }
            }
        })
    }
    
    function delMore(ids) {
        $.ajax({
            url:"roleServlet?action=delMore",
            data:"roleIds="+ids,
            type:"post",
            success:function (data) {
                if(data) {
                    alert("全部删除成功!");
                    //删除成功刷新页面
                    submitForm(1,10);
                } else {
                    alert("全部删除失败!");
                }
            }
        })
    }


    //根据 roleId 进行回显
    function toEdit(roleId) {
        window.location.href="roleServlet?action=toEdit&roleId="+roleId;
    }

    //更新提示框
    function showInfo() {
        ${param.info == true ? "alert('更新成功!')":""};
        ${param.info == false ? "alert('更新失败!')":""};
    }

</script>
</html>