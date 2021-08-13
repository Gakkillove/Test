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


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		C_Filter.Encoding(request, response);
		HttpSession session =request.getSession();
		String type=C_Filter.get(request, "type");
		String sql="";
		String par="";
		String res="";
		switch (type) {
			case "login_getcode":{//获取登录验证码
				String phone=C_Filter.get(request, "phone");
				sql="select id from users where phone=?";
				par=phone;
				if (D_Dao.NoExist(sql, par)) {
					C_Filter.put("n_have", response);
				} else {
					String code=B_Base.Code();
					if (I_Sms.sendms_aliyun(code, phone)) {
						session.setAttribute("Login_phone", phone);
						session.setAttribute("Login_code", code);
						C_Filter.put("yes", response);
					} else {
						C_Filter.put("n_do", response);
					}
				}
			}
			break;
			case "login_go":{
				String phone=C_Filter.get(request, "phone");
				String code=C_Filter.get(request, "code");
				String code1=(String) session.getAttribute("Login_code");
				String phone1=(String) session.getAttribute("Login_phone");
				session.removeAttribute("Login_code");
				session.removeAttribute("Login_phone");
				if (code.equals(code1)&&phone.equals(phone1)) {
					session.setAttribute("UserNow", phone);
					session.setAttribute("UserID", "123");
					C_Filter.put("yes", response);
				} else {
					C_Filter.put("n_code", response);
				}
			}
			break;
			case "exit":{
				session.invalidate();
				C_Filter.put("yes", response);
			}
			break;
		}
	}

}
