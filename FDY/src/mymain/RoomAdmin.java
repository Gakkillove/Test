package mymain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import base.B_Base;
import base.C_Filter;
import base.I_Sms;
import database.D_Dao;


@WebServlet("/RoomAdmin")
public class RoomAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RoomAdmin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		C_Filter.Encoding(request, response);
		if (C_Filter.isLogin(request, "UserNow")) {
			//********************************************
			HttpSession session =request.getSession();
			String UserID=(String) session.getAttribute("UserID");
			String type=C_Filter.get(request, "type");
			String sql="";
			String par="";
			String res="";
			switch (type) {
				case "get_roomlist":{//获取登录验证码
					sql="select * from rooms where uid=? and stete=?";
					res=D_Dao.SelecttoJson(sql, UserID);
					C_Filter.put(res, response);
				}
				break;
			}
			
			//***********************************************
		} else {
			C_Filter.put("n_login", response);
		}
	}

}
