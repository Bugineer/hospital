package com.qf.doctor.servlet;

import com.qf.common.util.BaseServlet;
import com.qf.common.util.PageUtils;
import com.qf.doctor.param.FindDoctorParam;
import com.qf.doctor.pojo.Doctor;
import com.qf.doctor.pojo.Education;
import com.qf.doctor.pojo.Policlinic;
import com.qf.doctor.service.DoctorService;
import com.qf.doctor.service.PoliclinicService;
import com.qf.doctor.service.impl.DoctorServiceImpl;
import com.qf.doctor.service.impl.PoliclinicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Salted Fish
 * @create 2022-06-16 10:00
 */
@WebServlet(name = "doctorServlet",urlPatterns = "/doctor/doctorServlet")
public class DoctorServlet extends BaseServlet {

    private DoctorService doctorService = new DoctorServiceImpl();
    private PoliclinicService policlinicService = new PoliclinicServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(this,request,response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String did = request.getParameter("did");
        String name = request.getParameter("name");
        String pid = request.getParameter("pid");
        FindDoctorParam findDoctorParam = new FindDoctorParam(did,name,pid);
        String pageNo = request.getParameter("pageNo");     //当前第几页
        if(pageNo == null || "".equals(pageNo)) {   //没有获取为默认第一页
            pageNo = "1";
        }
        //每页显示几条 写死 10条 或者 从 前端获取
        String pageSize = request.getParameter("pageSize");
        if(pageSize == null || "".equals(pageSize)) {   //没有获取为默认 一页显示 10 条
            pageSize = "10";
        }
        List<Policlinic> policlinicList = policlinicService.queryAll();
        PageUtils<Doctor> doctorPage = doctorService.queryDoctorByPage(findDoctorParam, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        request.setAttribute("pList",policlinicList);
        request.setAttribute("page",doctorPage);
        request.setAttribute("findParam",findDoctorParam);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    protected void look(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String did = request.getParameter("did");
        String url = request.getParameter("url");
        //根据did查询医生信息
        Doctor doctor = doctorService.queryById(Integer.parseInt(did));
        List<Policlinic> policlinicList = policlinicService.queryAll();
        List<Education> educationList = policlinicService.queryEduAll();
        request.setAttribute("doctor",doctor);
        request.setAttribute("pList",policlinicList);
        request.setAttribute("eList",educationList);
        request.getRequestDispatcher(url).forward(request,response);
    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Policlinic> policlinicList = policlinicService.queryAll();
        List<Education> educationList = policlinicService.queryEduAll();
        request.setAttribute("pList",policlinicList);
        request.setAttribute("eList",educationList);
        request.getRequestDispatcher("add.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String cardno = request.getParameter("cardno");
        String phone = request.getParameter("phone");
        String sexStr = request.getParameter("sex");
        String age = request.getParameter("age");
        int sex = Integer.parseInt(sexStr);
        String birthday = request.getParameter("birthday");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String email = request.getParameter("email");
        String department = request.getParameter("department");
        String education = request.getParameter("education");
        String remark = request.getParameter("remark");

        Doctor doctor = new Doctor(null,name,cardno,phone,sex,Integer.parseInt(age),date,new Date(),email,Integer.parseInt(department),Integer.parseInt(education),remark);
        String info = "false";
        boolean flag = doctorService.saveDoctor(doctor);
        if(flag){
            info = "true";
        }
        response.sendRedirect("doctorServlet?action=list&add="+info);
    }

    protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String didStr = request.getParameter("did");
        int did = Integer.parseInt(didStr);
        String phone = request.getParameter("phone");
        String sexStr = request.getParameter("sex");
        int sex = Integer.parseInt(sexStr);
        String birthday = request.getParameter("birthday");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String email = request.getParameter("email");
        String department = request.getParameter("department");
        String education = request.getParameter("education");
        String remark = request.getParameter("remark");

        Doctor doctor = new Doctor(did,null,null,phone,sex,null,date,null,email,Integer.parseInt(department),Integer.parseInt(education),remark);
        String info = "false";
        boolean flag = doctorService.modifyDoctor(doctor);
        if(flag){
            info = "true";
        }
        response.sendRedirect("doctorServlet?action=list&info="+info);
    }

    //导出 excel表格
    protected void export(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] dids = ids.split(",");
        List<Doctor> doctorList = new ArrayList<>();
        for(String did:dids) {
            Doctor doctor = doctorService.queryById(Integer.parseInt(did));
            doctorList.add(doctor);
        }
        //设置表头
        List<String> title = new ArrayList<>();
        title.add("编号");
        title.add("姓名");
        title.add("身份证");
        title.add("手机号");
        title.add("性别");
        title.add("年龄");
        title.add("出生年月");
        title.add("所在科室");
        title.add("学历");
        title.add("入职日期");
        title.add("评价");
        //设置 输出到 excel 数据
        List<Map<String,Object>> data = new ArrayList<>();
        for (Doctor doctor:doctorList) {
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put(title.get(0),doctor.getDid());
            dataMap.put(title.get(1),doctor.getName());
            dataMap.put(title.get(2),doctor.getCardno());
            dataMap.put(title.get(3),doctor.getPhone());
            dataMap.put(title.get(4),doctor.getSex() == 1? "男":"女");
            dataMap.put(title.get(5),doctor.getAge());
            dataMap.put(title.get(6),doctor.getBirthday());
            dataMap.put(title.get(7),doctor.getPoliclinic().getPname());
            dataMap.put(title.get(8),doctor.getEdu().getEname());
            dataMap.put(title.get(9),doctor.getHireDate());
            dataMap.put(title.get(10),doctor.getRemark());
            data.add(dataMap);
        }
        downExcel("医生表",title,data,request,response);
    }
}
