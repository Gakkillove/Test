package base;

import com.alibaba.fastjson.JSONObject;

public class I_Qrcode {
	public static final String APPKEY = "668ad0eacba00e6d";// 你的appkey
    public static final String URL = "https://api.jisuapi.com/qrcode/generate";
    public static final int margin = 10;
    public static final String bgcolor = "FFFFFF";
    public static final String fgcolor = "000000";
    public static final String oxlevel = "L";
 
    public static String Get(String text,int width,int tempid,String logo) {
    	String qrcode=null;
        String result = null;
        String url = URL + "?text=" + text + "&width=" + width + "&tempid=" + tempid + "&margin=" + margin + "&appkey="
                + APPKEY;
 
        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.parseObject(result);
            if (json.getIntValue("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject resultarr = json.getJSONObject("result");
                qrcode = resultarr.getString("qrcode");
                //System.out.println(qrcode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrcode;
    }
}
