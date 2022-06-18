<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>收费项目管理</title>
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
				window.location.href="charge-new.jsp";
		 });

         $('#clearBtn').click(function(){
             window.location.href="charge2Servlet?action=list";
         });
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
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认操作?")){
					alert("成功!");
				}
			}else{
				alert("请选中要操作的项");
			}
		}
    $(function () {       
		$('#backid').click(function(){
				window.location.href="dispensing.html";
		 });
        showInfo();
    });
    </script>
</head>
<body>
   
<form id="charge2Form" action="charge2Servlet?action=list" method="post" class="definewidth m20">
    <input type="hidden" name="pageNo" value="1">
    <input type="hidden" name="pageSize" value="10">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" name="id" value="${id}"/></td>
		
        <td width="10%" class="tableleft">姓名：</td>
        <td><input type="text" name="name" value="${name}"/></td>
    </tr>
    <tr>
		
		
		  <td colspan="4">
			<center>
				<button type="submit" class="btn btn-primary" type="button">查询</button> 
				<button id="clearBtn" class="btn btn-primary" type="button">清空</button>
			</center>
        </td>
    </tr>
</table>
</form>
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
        <th>病历号</th>
        <th>姓名</th>
        <th>住院日期</th>        
        <th>操作</th>
    </tr>
    </thead>
        <c:forEach items="${page.eList}" var="r">
            <tr >
                <td style="vertical-align:middle;">${r.rid}</td>
                <td style="vertical-align:middle;">${r.name}</td>
                <td style="vertical-align:middle;">${r.registerDate}</td>
                <td style="vertical-align:middle;">
                    <a href="charge2Servlet?action=look&id=${r.rid}">详情</a>
                    <a href="charge2Servlet?action=toAdd&id=${r.rid}&name=${r.name}">添加收费项</a>
                </td>
            </tr>
        </c:forEach>

		
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr>
        <th colspan="5">
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
        </th>
    </tr>
  </table>

</body>
<script>
    $("#pageSize").change(function () {
        var pageSize = this.value;
        //设置分页参数
        $("input[name='pageSize']").val(pageSize);
        $("#charge2Form").submit();
        // window.location.href="roleServlet?action=list&pageNo=1&pageSize="+pageSize;
    });
    $("#pageNo").change(function () {
        var pageNo = this.value;
        var pageSize = $("#pageSize").val();
        //设置分页参数
        $("input[name='pageNo']").val(pageNo);
        $("input[name='pageSize']").val(pageSize);
        $("#charge2Form").submit();
        // window.location.href="roleServlet?action=list&pageNo="+pageNo+"&pageSize="+pageSize;
    });

    //提交查询表单
    function submitForm(pageNo,pageSize) {
        $("input[name='pageNo']").val(pageNo);
        $("input[name='pageSize']").val(pageSize);
        $("#charge2Form").submit();
    }

    function showInfo() {
       ${param.info != false ? "":"alert('未查询到收费项目!')"}
        ${param.saveInfo == true ? "alert('添加收费项目成功!')":""};
        ${param.saveInfo == false ? "alert('添加收费项目失败!')":""};
    }

</script>
</html>
