package com.qf.hospital.servlet;

import com.qf.common.util.BaseServlet;
import com.qf.common.util.PageUtils;
import com.qf.doctor.pojo.Doctor;
import com.qf.hospital.pojo.Charge;
import com.qf.hospital.pojo.ChargeAndRegister;
import com.qf.hospital.pojo.Register;
import com.qf.hospital.service.RegisterService;
import com.qf.hospital.service.impl.RegisterServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查收费项目
 * @author Salted Fish
 * @create 2022-06-17 9:47
 */
@WebServlet(name = "charge2Servlet",urlPatterns = "/hospital/charge2Servlet")
public class Charge2Servlet extends BaseServlet {

    private RegisterService registerService = new RegisterServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(this,request,response);
    }


    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //收集表单参数
        String id = request.getParameter("id"); //查询条件
        String name = request.getParameter("name");
        String pageNo = request.getParameter("pageNo");     //当前第几页
        if(pageNo == null || "".equals(pageNo)) {   //没有获取为默认第一页
            pageNo = "1";
        }
        //每页显示几条 写死 10条 或者 从 前端获取
        String pageSize = request.getParameter("pageSize");
        if(pageSize == null || "".equals(pageSize)) {   //没有获取为默认 一页显示 10 条
            pageSize = "10";
        }
        PageUtils<Register> page = registerService.queryByPage(id, name, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        request.setAttribute("page",page);
        request.setAttribute("id",id);
        request.setAttribute("name",name);
        request.getRequestDispatcher("charge2.jsp").forward(request,response);
    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"); //查询条件
        String name = request.getParameter("name");
        request.setAttribute("id",id);
        request.setAttribute("name",name);
        request.getRequestDispatcher("charge-new.jsp").forward(request,response);
    }


    protected void look(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("id"); //查询条件
        String pageNo = request.getParameter("pageNo");     //当前第几页
        if(pageNo == null || "".equals(pageNo)) {   //没有获取为默认第一页
            pageNo = "1";
        }
        //每页显示几条 写死 10条 或者 从 前端获取
        String pageSize = request.getParameter("pageSize");
        if(pageSize == null || "".equals(pageSize)) {   //没有获取为默认 一页显示 10 条
            pageSize = "10";
        }
        PageUtils<ChargeAndRegister> page = registerService.queryCRByPage(rid, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        if(page.geteList() == null || page.geteList().size() == 0) {
            response.sendRedirect("charge2Servlet?action=list&info=false");
            return;
        }
        request.setAttribute("page",page);
        //计算收费项目 花费
        Double countMoney = registerService.countMoney(rid);
        request.setAttribute("totalMoney",countMoney);
        request.setAttribute("id",rid);
        request.getRequestDispatcher("account-look2.jsp").forward(request,response);
    }

    //导出 excel表格
    protected void export(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("id"); //查询条件

        List<ChargeAndRegister> list = registerService.queryAll(rid);
        //设置表头
        List<String> title = new ArrayList<>();
        title.add("病历号");
        title.add("姓名");
        title.add("收费项目");
        title.add("收费金额");
        title.add("检查日期");
        //设置 输出到 excel 数据
        List<Map<String,Object>> data = new ArrayList<>();
        for(ChargeAndRegister cr:list) {
            System.out.println(cr);
            HashMap<String, Object> dataMap = new HashMap<>();
            dataMap.put(title.get(0),cr.getRegister().getRid());
            dataMap.put(title.get(1),cr.getRegister().getName());
            dataMap.put(title.get(2),cr.getCharge().getCname());
            dataMap.put(title.get(3),cr.getCharge().getChargeAmount());
            dataMap.put(title.get(4),cr.getCreateDate());
            data.add(dataMap);
        }
        downExcel("收费项目表",title,data,request,response);
    }

    //模糊查询 响应一个json 数组
    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name"); //获取前端 收费项目 名
        List<Charge> chargeList = registerService.queryByName(name);
        JSONArray jsonArray = JSONArray.fromObject(chargeList);
        String json = jsonArray.toString();
        print(json,response);
    }

    //模糊查询 响应一个json 数组
    protected void findMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid"); //获取前端 收费项目 名
        Charge charge = registerService.queryByCid(Integer.parseInt(cid));
        Double chargeAmount = charge.getChargeAmount();
        print(chargeAmount+"",response);
    }

    //添加收费项
    protected void saveCharge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid"); //获取前端 收费项目 名
        String rid = request.getParameter("rid");
        //添加收费项
        boolean flag = registerService.saveCharge(rid,Integer.parseInt(cid));
        response.sendRedirect("charge2Servlet?action=list&saveInfo="+flag);
    }

}
