package com.qf.common.util;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础的分页类
 * @author yup
 * @version1.0
 *
 * 2017年6月9日
 */
public abstract class BaseServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected String basePath;

	/**
	 * 分支处理增删改查
	 * @author yup
	 *
	 * @param servlet
	 * @param request
	 * @param response
	 * 2017年6月9日
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected void handle(Servlet servlet, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		basePath = request.getContextPath();
		String action = request.getParameter("action");
		String path = null;
		try {
			Method method = servlet.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			path = (String)method.invoke(servlet, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(path == null) {
			return;
		}
		
		//页面跳转
		String[] type = path.split(":");
		if("redirect".equals(type[0])) {
			response.sendRedirect(type[1]);
		}else {
			if(type.length == 1) {
				request.getRequestDispatcher(path).forward(request, response);
			}else {
				request.getRequestDispatcher(type[1]).forward(request, response);
			}
		}
	}

	/**
	 * 文件上传
	 * @param request
	 * @param savePath
	 * @param param
	 * @return
	 */
	protected boolean upload(HttpServletRequest request, String savePath, Map<String, String> param) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart) {
			return false;
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload uploade = new ServletFileUpload(factory);

		String encoding = request.getCharacterEncoding();

		//设置上传的字符编码
		uploade.setHeaderEncoding(encoding);

		try {
			RequestContext ctx = new ServletRequestContext(request);
			List<FileItem> list= uploade.parseRequest(ctx);
			for(FileItem item : list) {
				if(item.isFormField()) {
					param.put(item.getFieldName(), item.getString(encoding));
				}else {
					String fileName = item.getName();
					if(fileName == null || "".equals(fileName)) {
						continue;
					}
					param.put("fileName", fileName);
					String suffix = fileName.substring(fileName.lastIndexOf("."));
					String newFileName = new Date().getTime() + suffix;
					savePath =  savePath + "\\" + newFileName;
					param.put("path", savePath);
					InputStream input = item.getInputStream();
					FileOutputStream output = new FileOutputStream(savePath);
					byte[] data = new byte[1024];
					int length = 0;
					while((length = input.read(data)) != -1) {
						output.write(data,0, length);
					}
					output.close();
					input.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 文件下载
	 * @param downPath
	 * @param fileName
	 * @param response
	 * @return
	 */

	protected boolean downFile(String downPath, String fileName, HttpServletResponse response) {
		try{
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, response.getCharacterEncoding()));
			response.setContentType("application/octet-stream");
			
			OutputStream output = response.getOutputStream();
			
			FileInputStream input = new FileInputStream(downPath);
			byte[] data = new byte[1024];
			int length = 0;
			while((length = input.read(data)) != -1) {
				output.write(data, 0, length);
			}
			input.close();
			output.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 提示页面
	 * @author yup
	 * 2018年4月23日
	 * @param path
	 * @param info
	 * @param url
	 * @return
	 */
	protected String prompt(String path, String info, String url) {
		StringBuilder URL = new StringBuilder();
		URL.append(basePath);
		try {
			info = URLEncoder.encode(info, "UTF-8");
			url = basePath + "/" + url;
			url = URLEncoder.encode(url, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		URL.append("/");
		URL.append(path);
		URL.append("?info=");
		URL.append(info);
		URL.append("&url=");
		URL.append(url);
		return URL.toString();
	}
	
	/**
	 * 显示本地图片
	 * @author yup
	 * 2018年4月23日
	 * @param response
	 * @param path
	 */
	protected void showImg(HttpServletResponse response, String path) {
		response.setContentType("image/png"); 
		response.setHeader("Pragma", "No-cache"); 
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		InputStream in = null;
		BufferedInputStream buffIn= null;
		BufferedOutputStream buffout= null;
		try {
			in = new FileInputStream(path);
			buffIn = new BufferedInputStream(in);
			buffout = new BufferedOutputStream(response.getOutputStream());
			 byte[] buff = new byte[2048];  
			 int length;  
		     while ((length = buffIn.read(buff, 0, buff.length)) != -1) {  
		    	buffout.write(buff, 0, length);  
		     }  
		     buffout.flush(); 
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try{
				if(buffout!=null){  
					buffout.close();  
				}  
				if(buffIn!=null){  
					buffIn.close();  
				}  
				if(in!=null){  
					in.close();  
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 文件删除
	 * @author yup
	 *
	 * @param path
	 * @return
	 * 2021年4月16日
	 */
	protected boolean deleteFile(String path) {
		File file = new File(path);
		if(file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 把字符串输出到浏览器中
	 * @author yup
	 * @param str, response
	 * @return void
	 * @date 2022/4/29 17:49
	*/
	protected void print(String str, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(str);
		out.close();
	}

	protected void downExcel(String sheetName, List<String> column, List<Map<String,Object>> data, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//调用导出工具类
		ExportExcel.exportExcel(sheetName,column,data,request,response);
	}

}
