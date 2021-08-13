package base;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alibaba.fastjson.JSONObject;

public class I_IPinfo {
	public static String getIPinfo_Aliyun(String ip) {
		String restr="isnull";
        //API产品路径
        String requestUrl = "http://ipaddr.market.alicloudapi.com/ip_addr_search/v1";
        //阿里云APPCODE
        String appcode = "2159394bedcc4b4885300d1da8de3a08";

        try {
            HttpClient client = new HttpClient();
            GetMethod getMethod = new GetMethod(requestUrl);
            //添加HEADER
            getMethod.addRequestHeader("Authorization","APPCODE "+ appcode);
            getMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            NameValuePair[] meta_list = new NameValuePair[1];
            //设置变量数据
            NameValuePair param1 = new NameValuePair("IP_ADDR",ip);
            meta_list[0]=param1;
            getMethod.setQueryString(meta_list);
            int code = client.executeMethod(getMethod);
            //System.out.println(code);
            if (code == 200) {
                String res = getMethod.getResponseBodyAsString();
                JSONObject json = JSONObject.parseObject(res);
                JSONObject json1 = JSONObject.parseObject(json.getString("ENTITY"));
                JSONObject json2 = JSONObject.parseObject(json1.getString("INPUT_IP_ADDRESS"));
                String pro=json2.getString("PROVINCE");
                String city=json2.getString("CITY");
                //System.out.println(pro+"&"+city);
                restr=pro+"&"+city;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restr;
	}
}
