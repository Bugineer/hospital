package com.qf.role.servlet;

import com.qf.common.util.BaseServlet;
import com.qf.common.util.PageUtils;
import com.qf.role.pojo.Modal;
import com.qf.role.pojo.Role;
import com.qf.role.service.ModalService;
import com.qf.role.service.RoleService;
import com.qf.role.service.impl.ModalServiceImpl;
import com.qf.role.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-14 12:06
 */
@WebServlet(name ="roleServlet",urlPatterns = "/role/roleServlet")
public class RoleServlet extends BaseServlet {
    private RoleService roleService = new RoleServiceImpl();
    private ModalService modalService = new ModalServiceImpl();

    // 找方法的请求参数 是 action
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(this,request,response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //收集表单参数
        String roleName = request.getParameter("roleName"); //查询条件 roleName
        String pageNo = request.getParameter("pageNo");     //当前第几页
        if(pageNo == null || "".equals(pageNo)) {   //没有获取为默认第一页
            pageNo = "1";
        }
        //每页显示几条 写死 10条 或者 从 前端获取
        String pageSize = request.getParameter("pageSize");
        if(pageSize == null || "".equals(pageSize)) {   //没有获取为默认 一页显示 10 条
            pageSize = "10";
        }
        PageUtils<Role> rolePage = roleService.queryRoleByPage(roleName, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        //转发给 显示页面 记得把 index.jsp 改成 index.jsp 加个 jsp的标签头
        request.setAttribute("rolePage",rolePage);
        request.setAttribute("roleName",roleName);  //查询参数回显
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    protected void checkedRoleName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleName = request.getParameter("roleName");
        Role role = roleService.queryByRoleName(roleName);
        String info = "";
        if(role != null) {
            // role 存在 不可用
            info = "角色名已存在!";
        }
        print(info,response);
    }

    //去添加页面 展示 modal信息
    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Modal> modalList = modalService.queryAll();
        request.setAttribute("modalList",modalList);
        request.getRequestDispatcher("addRole.jsp").forward(request,response);
    }

    //添加角色
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //收集表单参数
        String roleName = request.getParameter("roleName");
        String status = request.getParameter("status");
        String[] modals = request.getParameterValues("modals[]");
        //创建 role 对象
        Role role = new Role(roleName, Integer.parseInt(status));
        //插入 role对象
        boolean saveRole = roleService.saveRole(role);
        //查询出id
        role = roleService.queryByRoleName(role.getRoleName());
        //插入成功则赋权
        if(saveRole) {
            roleService.saveModal(role.getRoleId(),modals);
        }
        response.sendRedirect("roleServlet?action=list");
    }

    //删除
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleId = request.getParameter("roleId");
        String info = "false";
        if(roleId != null) {
            if(!("".equals(roleId.trim()))) {
                boolean delFlag = roleService.removeByRoleId(Integer.parseInt(roleId.trim()));
                if(delFlag) {
                    info = "true";
                }
            }
        }
        print(info,response);
    }

    //去编辑页面 展示 modal信息
    protected void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleId = request.getParameter("roleId");
        List<Modal> modalList = modalService.queryAll();
        if(roleId != null) {
            Role role = roleService.queryByRoleId(Integer.parseInt(roleId));
            request.setAttribute("role",role);
        }
        request.setAttribute("modalList",modalList);
        request.getRequestDispatcher("editRole.jsp").forward(request,response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //收集表单参数
        String info = "false";
        String roleId = request.getParameter("roleId");
        String roleName = request.getParameter("roleName");
        String status = request.getParameter("status");
        String[] modals = request.getParameterValues("modals[]");
        //创建 role 对象
        Role role = new Role(roleName, Integer.parseInt(status));
        role.setRoleId(Integer.parseInt(roleId));
        //更新 role对象
        boolean modifyRole = roleService.modifyRole(role,modals);
        if(modifyRole) {
            info = "true";
        }
        response.sendRedirect("roleServlet?action=list&info="+info);
    }


    //批量删除
    protected void delMore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String params = request.getParameter("roleIds");
        String[] roleIds = params.split(",");
        String info = "false";
        boolean flag = roleService.removeByRoleIds(roleIds);
        if(flag) {
            info = "true";
        }
        print(info,response);
    }
}
