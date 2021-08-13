package base;

public class B_monitor {
	private static String web_id="2";//点餐云-后端
	private static String url="http://supersystem.anguangkeji.com/Getinfo";
	//提交操作日志
	//nowtype:数据插入Data insertion/数据删除/数据修改Data modification/接口调用Interface call/登录log in/注销Logout
	public static void Log_behavior(String uid,String nowtype,String remarks) {
		String type="behavior";
		String par="?type="+type+"&web_id="+web_id+"&uid="+uid+"&nowtype="+nowtype+"&remarks="+remarks;
		I_Http.Get(url+par);
	}
	//提交错误日志
	//nowtype:数据错误data error/程序异常Program exception/其它异常Other errors
	public static void Log_error(String nowtype,String remarks,String text) {
		String type="error";
		String par="?type="+type+"&web_id="+web_id+"&text="+text+"&nowtype="+nowtype+"&remarks="+remarks;
		I_Http.Get(url+par);
	}
}
