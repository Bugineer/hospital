<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>门诊医生--理博软件2016</title>
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
				window.location.href="doctorServlet?action=toAdd";
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
		function excelAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认导出信息?")){
					excel(ids);
				}
			}else{
				alert("请选中要操作的项");
			}
		}
    </script>
</head>
<body>

<form id="doctorForm" action="doctorServlet?action=list" method="post" class="definewidth m20">
    <input type="hidden" name="pageNo" value="1">
    <input type="hidden" name="pageSize" value="10">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">医生编号：</td>
        <td><input type="text" name="did" value="${findParam.did}"/></td>
		
        <td width="10%" class="tableleft">医生姓名：</td>
        <td><input type="text" name="name" value="${findParam.name}"/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td>
            <select name="pid">
                <option value="">请选择</option>
                <c:forEach items="${pList}" var="p">
                    <option value="${p.pid}" ${findParam.department == p.pid ? "selected":""} >${p.pname}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
		  <td colspan="6"><center>
            <button type="submit" class="btn btn-primary" type="button">查询</button> 
            <button type="button" onclick="window.location.href='doctorServlet?action=list'" class="btn btn-primary" type="button">清空</button>
			</center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>医生编号</th>
        <th>医生姓名</th>
        <th>入院时间</th>
        <th>所属科室</th>
        <th>操作</th>
    </tr>
    </thead>
        <c:forEach items="${page.eList}" var="doctor">
            <tr >
                <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${doctor.did}"></td>
                <td style="vertical-align:middle;">${doctor.did}</td>
                <td style="vertical-align:middle;">${doctor.name}</td>
                <td style="vertical-align:middle;">${doctor.hireDate}</td>
                <td style="vertical-align:middle;">${doctor.policlinic.pname}</td>
                <td style="vertical-align:middle;"><a href="doctorServlet?action=look&did=${doctor.did}&url=look.jsp">详情>>></a>&nbsp;&nbsp;&nbsp;
                    <a href="doctorServlet?action=look&did=${doctor.did}&url=edit.jsp">更改</a></td>
            </tr>
        </c:forEach>

  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">

        <div class="inline pull-right page">
            <span>${page.totalPageCount} 条记录 ${page.currentPageNo}/${page.totalPageSize} 页</span>
            <span>
                    每页显示
                    <select name="pageSize" style="width: 50px" id="pageSize">
                        <option value="1" ${page.pageSize == 1? "selected":""}>1</option>
                        <option value="5" ${page.pageSize == 5? "selected":""}>5</option>
                        <option value="10" ${page.pageSize == 10? "selected":""}>10</option>
                        <option value="20" ${page.pageSize == 20? "selected":""}>20</option>
                    </select>条
                </span>
            <c:if test="${page.currentPageNo > 1}">
                <a href="javaScript:submitForm(1,${page.pageSize})">首页</a>
                <a href="javaScript:submitForm('${page.currentPageNo-1}','${page.pageSize}')">上一页</a>
            </c:if>
            转到
            <select name="pageNo" style="width: 50px" id="pageNo">
                <c:forEach begin="1" end="${page.totalPageSize}" var="pageNo">
                    <option value="${pageNo}" ${page.currentPageNo == pageNo? "selected":""}>${pageNo}</option>
                </c:forEach>
            </select>页
            <c:if test="${page.currentPageNo < page.totalPageSize}">
                <a href="javaScript:submitForm('${page.currentPageNo+1}','${page.pageSize}')">下一页</a>
                <a href="javaScript:submitForm('${page.totalPageSize}','${page.pageSize}')">尾页</a>
            </c:if>
        </div>


        <div><button type="button" class="btn btn-success" id="newNav">添加新医生</button>
		 <button type="button" class="btn btn-success" id="delPro" onclick="excelAll()">导出Excel</button>
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
<script>
    $("#pageSize").change(function () {
        var pageSize = this.value;
        //设置分页参数
        $("input[name='pageSize']").val(pageSize);
        $("#doctorForm").submit();
        // window.location.href="roleServlet?action=list&pageNo=1&pageSize="+pageSize;
    });
    $("#pageNo").change(function () {
        var pageNo = this.value;
        var pageSize = $("#pageSize").val();
        //设置分页参数
        $("input[name='pageNo']").val(pageNo);
        $("input[name='pageSize']").val(pageSize);
        $("#doctorForm").submit();
        // window.location.href="roleServlet?action=list&pageNo="+pageNo+"&pageSize="+pageSize;
    });

    //提交查询表单
    function submitForm(pageNo,pageSize) {
        $("input[name='pageNo']").val(pageNo);
        $("input[name='pageSize']").val(pageSize);
        $("#doctorForm").submit();
    }

    //更新提示框
    function showInfo() {
        ${param.info == true ? "alert('更新成功!')":""};
        ${param.info == false ? "alert('更新失败!')":""};
        ${param.add == true ? "alert('添加成功!')":""};
        ${param.add == false ? "alert('添加失败!')":""};
    }

    //导出 excel 请求
    function excel(ids) {
        window.location.href = "doctorServlet?action=export&ids="+ids;
    }

</script>
</html>
