package base;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

//实现常用文本处理，图像处理、内容加密解密、json处理
public class B_text {

	//表情符号过滤
	public String Removeface(String str) {
	    if (str.trim().isEmpty()) {
	        return str;
	    }
	    String pattern = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
	    String reStr = "";
	    Pattern emoji = Pattern.compile(pattern);
	    Matcher emojiMatcher = emoji.matcher(str);
	    str = emojiMatcher.replaceAll(reStr);
	    return str;
	}

	
    //字符串md5加密，返回加密后的32位MD5码
	public static String getMD5(String str) {  
        try {  
            // 生成一个MD5加密计算摘要  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            // 计算md5函数  
            md.update(str.getBytes());  
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符  
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值  
            return new BigInteger(1, md.digest()).toString(16);  
        } catch (Exception e) {  
           e.printStackTrace();  
           return null;  
        }
    }
}
