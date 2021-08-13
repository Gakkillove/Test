package base;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

//实现常用文本处理，图像处理、内容加密解密、json处理
public class B_text {
	//判断字符串为空
	public static boolean isNull(String str){
		boolean b=false;
		if (str==null||str.equals("")||str.equals(" ")) {
			b=true;
		}
		return b;
	}

	//判断字符串为无效字符串
	public static boolean isBored(String str,String strArr,String word){
		boolean b=false;
		if (isNull(str)) {
			b=true;
		}else {
			String[] arr=strArr.split(word);
			for (int i = 0; i < arr.length; i++) {
				if (str.equals(arr[i])) {
					b=true;
				}
			}
		}
		return b;
	}
	
	//网页代码中获取图片文件名并压缩
	public String Htmlimg(String content){
		String srcList =""; //用来存储获取到的图片地址  
	    Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");//匹配字符串中的img标签  
	    Matcher matcher = p.matcher(content);  
	    boolean hasPic = matcher.find();  
	    if(hasPic == true)//判断是否含有图片  
	    {  
	        while(hasPic) //如果含有图片，那么持续进行查找，直到匹配不到  
	        {  
	            String group = matcher.group(2);//获取第二个分组的内容，也就是 (.*?)匹配到的  
	            Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");//匹配图片的地址  
	            Matcher matcher2 = srcText.matcher(group);  
	            if( matcher2.find() ){  
	            	if (srcList.equals("")) {
	            		String[] strings=matcher2.group(3).split("/");
	            		srcList=strings[strings.length-1];
					}else{
						String[] strings=matcher2.group(3).split("/");
		                srcList=srcList+","+strings[strings.length-1];//把获取到的图片地址添加到列表中 
					} 
	            }  
	            hasPic = matcher.find();//判断是否还有img标签  
	        }  
	              
	    }  
	    return srcList;
	}
	
	//文章关键描述截取
	public String Word(String allword,int length){
		String word=null;
		if (allword.length()<=length) {
			word=allword;
		} else {
			word=allword.substring(0,length);
		}
		return word;
	}

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
	/*
	public static void main(String[] args) {
		System.out.println(getMD5("123"));
	}*/
	
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
	
	//自定义加密算法，返回加密后的字符串
	public static String setCiphertext(String str,int length_end) {
		String text = "";
		int length=B_Base.Rand(1, 5);//字符替换长度1--4
		String time=B_time.Timerm().substring(10);
		System.out.println(time+"+++"+length);
		char[] code=time.toCharArray();//系统时间后四位作移位量
		char[] str1=str.toCharArray();//待加密字符数组
		for (int i = 0; i < str1.length; i++) {
			int x=B_Base.Arr_getIndex(C_Cmd.letter, str1[i]);//查得字符在字符集中的位置下标
			for (int j = 0; j < length; j++) {
				int in1=x+Integer.parseInt(String.valueOf(code[j]));
				int in2=x-Integer.parseInt(String.valueOf(code[j]));
				if (in1>C_Cmd.letter.length-1) {
					in1=x+Integer.parseInt(String.valueOf(code[j]))-(C_Cmd.letter.length-1);
				}
				if (in2<0) {
					in2=C_Cmd.letter.length-1-(Integer.parseInt(String.valueOf(code[j]))-x);
				}
				text+=C_Cmd.letter[in1]+C_Cmd.letter[in2];//正负移位拼接
			}	
		}
		//追加标记
		for (int i = 0; i < code.length; i++) {
			String aString=String.valueOf(code[i]);
			int y=Integer.parseInt(aString)*8;
			text+=C_Cmd.letter[y];
		}
		text+=C_Cmd.letter[length*8];
		return text;
	}
	
	//自定义解密算法，返回解密后的字符串
	public static String getCiphertext(String str) {
		String text = "";
		int leng=B_Base.Arr_getIndex(C_Cmd.letter, str.substring(str.length()-1).toCharArray()[0]);
		int length=Integer.parseInt(C_Cmd.letter[leng/8]);
		char[] co=str.substring(str.length()-5,str.length()-1).toCharArray();
		String[] code=new String[co.length];
		//还原code
		for (int i = 0; i < co.length; i++) {
			int x=B_Base.Arr_getIndex(C_Cmd.letter,co[i])/8;
			code[i]=C_Cmd.letter[x];
		}
		//System.out.println(code[0]+code[1]+"+++"+length);
		//切割密文
		String[] content=new String[(str.length()-5)/(length*2)];
		for (int i = 0; i < content.length; i++) {
			content[i]=str.substring(i*length*2, i*length*2+length*2);
		}
		//还原原文
		for (int i = 0; i < content.length; i++) {
			char[] cont=content[i].toCharArray();
			int j = 0;
			int incont1=B_Base.Arr_getIndex(C_Cmd.letter, cont[j])-Integer.parseInt(code[j]);
			if (incont1<0) {
				incont1=C_Cmd.letter.length-1+B_Base.Arr_getIndex(C_Cmd.letter, cont[j])-Integer.parseInt(code[j]);
			}
			text+=C_Cmd.letter[incont1];
		}
		return text;
	}
	
	//检查sql语句合法性
	public static boolean isSelect(String text) {
		text=text.toLowerCase();
		boolean b=isHavaStr(text, "select");
		boolean b1=isHavaStr(text, "delete");
		boolean b2=isHavaStr(text, "update");
		boolean b3=isHavaStr(text, "insert");
		boolean b4=isHavaStr(text, "set");
		boolean b5=isHavaStr(text, "create");
		boolean b6=isHavaStr(text, "drop");
		if (b==true&&b1==false&&b2==false&&b3==false&&b4==false&&b5==false&&b6==false) {
			return true;
		} else {
			return false;
		}
	}
	
	//检查某字符串中是否包含另一个字符串（text被操作字符串，str参考字符串）
	public static boolean isHavaStr(String text,String str) {
		int is = text.indexOf(str);
		if (is==-1) {//不包含
			return false;
		} else {//包含
			return true;
		}
	}

	//检查某标准分隔字符串中是否包含另一个子串，并返回字串脚标（text被操作字符串，str参考字符串,sign分隔符）
	public static int isHavaIndex(String text,String str,String sign) {
		int i=-1;
		String[] str1=text.split(sign);
		for (int j = 0; j < str1.length; j++) {
			if (str1[j].equals(str)) {
				i=j;
			}
		}
		return i;
	}
	//从一个键值对字符串中移除一个键值对字符串--键唯一（text被操作字符串，str参考字符串，sign分隔符）
	public static String reMoveKeyValue(String text,String str,String sign) {
		String[] strArr=text.split(sign);
		String retext="";
		for (int i = 0; i < strArr.length; i++) {
			if (!strArr[i].split(":")[0].equals(str.split(":")[0])) {
				if (retext.equals("")) {
					retext+=strArr[i];
				} else {
					retext+=sign+strArr[i];
				}
			}
		}
		return retext;
	}
	//从一个键值对字符串链中移除一个子项字符串（text被操作字符串，str参考字符串，sign分隔符）
	public static String reMoveValue(String text,String str,String sign) {
		String[] strArr=text.split(sign);
		String retext="";
		for (int i = 0; i < strArr.length; i++) {
			if (!strArr[i].equals(str)) {
				if (retext.equals("")) {
					retext+=strArr[i];
				} else {
					retext+=sign+strArr[i];
				}
			}
		}
		return retext;
	}

	//从一个键值对字符串中检查是否包含另一个键值对字符串（text被操作字符串，str参考字符串，sign1母分隔符，sign2子分隔符，参考键位）
	public static boolean isValue(String text,String str,String sign1,String sign2,int index) {
		boolean b=false;
		String[] strArr=text.split(sign1);
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].split(sign2)[index].equals(str)) {
				b=true;
			}
		}
		return b;
	}
}
