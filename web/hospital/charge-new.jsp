<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加收费项目--理博软件2016</title>
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

        #searchDiv {
            background-color: white;
            width: 210px;
            position: absolute;
            top: 156px;
            display: none;
        }

        .searchItem:hover {
            background-color: yellow;
            font-weight: bolder;
        }

    </style>
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
				window.location.href="charge2Servlet?action=list";
		 });
    });
    </script>
</head>
<body>
<form action="charge2Servlet?action=saveCharge" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td><input name="rid" value="${id}" type="text" readonly /></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input name="name" value="${name}" type="text"  readonly /></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费项目</td>
        <td><input type="text" name="cname" id="cname" oninput="search(this)" value=""/>输入自动匹配出来
            <input type="hidden" name="cid" id="cid" value="">
            <div id="searchDiv">

            </div>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费金额</td>
        <td><input type="text" id="money" value="" readonly /></td>
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
<script>

    function search(inp) {
        //发送ajax
        $("#money").val("");
        $("#searchDiv").html("");
        $.ajax({
            url:"charge2Servlet?action=search",
            data:"name="+inp.value,
            type:"post",
            success:function (data) {
                if(data.length > 0) {
                    $("#searchDiv").css({"display":"block"});
                }
                for(var i = 0;i < data.length; i++) {
                    var p = $("<p class='searchItem'></p>");
                    p.attr("cid",data[i].cid);
                    p.text(data[i].cname);
                    $("#searchDiv").append(p);
                }
            },
            dataType:"json"
        })
    }

    //选择 收费项
    $(document).on("click",".searchItem",function () {
        //先改变输入框
        var cname = $(this).text();
        var cid = $(this).attr("cid");
        $("#cname").prop("value",cname);
        $("#cid").prop("value",cid);
        clearSearch();
        //发送ajax查询 价格
        findMoney(cid);
    })
    
    function clearSearch() {
        $("#searchDiv").css({"display":"none"});
    }

    function findMoney(cid) {
        $("#money").val("");
        $.ajax({
            url:"charge2Servlet?action=findMoney",
            data:"cid="+cid,
            type:"post",
            success:function (data) {
                $("#money").val(data);
            },
            dataType:"json"
        })
    }
</script>
</html>