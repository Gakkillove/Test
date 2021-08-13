package base;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
//短信接口
public class I_Sms {
    //阿里云短信验证码接口
    public static boolean sendms_aliyun(String code,String phone) {
    	//System.out.println(code+" + "+phone);
    	boolean b=false;
	    String host = "https://fesms.market.alicloudapi.com";
	    String path = "/sms/";
	    String method = "GET";
	    String appcode = "2159394bedcc4b4885300d1da8de3a08";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("code", code);
	    querys.put("phone", phone);
	    //querys.put("sign", "51694");//签名-[桉光云]
	    querys.put("sign", "160831");//签名-[桉光科技]
	    querys.put("skin", "900695");//您的本次短信验证码为：@，30分钟内有效且仅可使用一次。
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                * 或者直接下载：
                * http://code.fegine.com/HttpUtils.zip
	    	* 下载
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                * 相关jar包（非pom）直接下载：
                * http://code.fegine.com/aliyun-jar.zip
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	//System.out.println(response.toString());//如不输出json, 请打开这行代码，打印调试头部状态码。
                //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
	    	//获取response的body
	    	String result =EntityUtils.toString(response.getEntity());
            JSONObject json = JSONObject.parseObject(result);
            if (json.getString("Code").equals("OK")) {
				b=true;
			}
            System.out.println(json.getString("Code"));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return b;
	}
    //阿里云通知短信接口
    public static boolean sendsmsmsg_aliyun(String code,String phone) {
    	boolean b=false;
	    String host = "https://fesms.market.alicloudapi.com";
	    String path = "/smsmsg";
	    String method = "GET";
	    String appcode = "2159394bedcc4b4885300d1da8de3a08";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("param", code);
	    querys.put("phone", phone);
	    querys.put("sign", "160831");//签名-[桉光科技]
	    querys.put("skin", "900694");//您的点餐云账号@，初始登录密码为@，服务校验密码为@。请妥善保管。
            //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                * 或者直接下载：
                * http://code.fegine.com/HttpUtils.zip
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                * 相关jar包（非pom）直接下载：
                * http://code.fegine.com/aliyun-jar.zip
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	//System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
                //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
	    	//获取response的body
	    	String result =EntityUtils.toString(response.getEntity());
            JSONObject json = JSONObject.parseObject(result);
            if (json.getString("Code").equals("OK")) {
				b=true;
			}
            System.out.println(json.getString("Code"));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return b;
	}
    
    //极速数据短信接口
    public static boolean sendms_jisu(String phone,String content) throws Exception {
    	boolean b=false;
        String APPKEY = "668ad0eacba00e6d";// 你的appkey
        String URL = "https://api.jisuapi.com/sms/send";
        //String mobile = phone;// 手机号
        //String content = "用户您好。【极速数据】";// utf-8
     
        String result = null;
        String url = URL + "?mobile=" + phone + "&content=" + URLEncoder.encode(content, "utf-8") + "&appkey="
                + APPKEY;
        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.parseObject(result);
            if (json.getIntValue("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
            	b=true;
                JSONObject resultarr = json.getJSONObject("result");
                String count = resultarr.getString("count");
                String accountid = resultarr.getString("accountid");
                System.out.println(count + " " + accountid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
    
}
