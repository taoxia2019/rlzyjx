package com.tcrl.utils;

import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;


public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
	
	public static  void export(HttpServletRequest request,HttpServletResponse response, Workbook wb, String fileName)throws Exception{
		OutputStream output = response.getOutputStream();
		response.reset();
		//解决火狐浏览器下载错误问题
		String s = request.getHeader("USER-AGENT").toLowerCase();
		if (s.indexOf("firefox") > 0) {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xls");
		} else {
			response.setHeader("Content-disposition", "attachment; filename="
					+ java.net.URLEncoder.encode(fileName, "utf-8") + ".xls");
		}

		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();
	}
	
	
}
