package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//过滤&验证&权限
public class C_Filter{
	//servlet编码设置为UTF-8
	public static void Encoding(HttpServletRequest request, HttpServletResponse response) {
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		//response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	}
	
	//servlet获得string
	public static String get(HttpServletRequest request,String name) {
		return request.getParameter(name).trim().replace(",", "，").replace("#equal#", "=").replace("#apostrophe#", "'").replace("#percent#", "%");
	}
	
	//servlet获得int
	public static int getInt(HttpServletRequest request,String name) {
		return Integer.parseInt(request.getParameter(name).trim());
	}
	
	//servlet获得double
	public static double getDouble(HttpServletRequest request,String name) {
		return Double.valueOf(request.getParameter(name).trim());
	}
	
	//servlet向前端输出字符串
	public static void put(String text, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
		    out.print(text);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//servlet判断用户登录状态
	public static boolean isLogin(HttpServletRequest request,String AttributeName) {
		boolean b=false;
		HttpSession session=request.getSession();
		String admin=(String) session.getAttribute(AttributeName);
		if (admin!=null) {
			b=true;
		}
		return b;
	}

}
