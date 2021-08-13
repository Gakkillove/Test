package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

//文件上传、excel解析、文件导出、前后端数据交互数据处理
public class B_Io {
	/*
	public static void main(String[] args) {
		readTxtFile("C:\\Users\\XQSD-2\\Desktop\\艺考云\\2019测试\\认证考生库.csv");
	}
	*/

	//txt文本读取，行间以\r\n分隔，被读文件必须为utf-8格式存储
	public static String readTxtFile(String filePath) {
		StringBuilder content = new StringBuilder("");
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					//System.out.println(lineTxt);//行输出
					content.append(lineTxt);
					content.append("\r\n");// txt换行
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return content.toString();
	}
	
	//txt文件全覆盖写入
	public static void printFile(String content,String filePath) {
		BufferedWriter bw = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//txt文件追加行,addtype追加方式：star前插入，end尾部追加
	public static boolean addFile(String content,String filePath,String addtype) {
		Boolean b=true;
		try {
			if (addtype.equals("star")) {
				printFile(content+"\r\n"+readTxtFile(filePath),filePath);	
			} else if(addtype.equals("end")){
				printFile(readTxtFile(filePath)+content+"\r\n",filePath);
			}
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
	
	//txt文件插入行,index目标行号
	public static void insertFile(String content,String filePath,int index) {
		String[] arr=readTxtFile(filePath).split("\r\n");
		String text="";
		for (int i = 0; i < arr.length; i++) {
			if ((i+1)==index) {
				text+=content+"\r\n"+arr[i]+"\r\n";
			} else {
				text+=arr[i]+"\r\n";
			}
		}
		printFile(text,filePath);
	}

	//txt键值对参数查询,key键名，word键值分隔符
	//文本必须顶格，参数格式为：id 间隔符 key 间隔符 value
	public static String getFile(String key,String word,String filePath) {
		String value="";
		String[] arr=readTxtFile(filePath).split("\r\n");
		for (int i = 0; i < arr.length; i++) {
			String[] str=arr[i].split(word);
			if (str[1].equals(key)) {
				value=str[2];
			}
		}
		return value;
	}
	
	//txt键值对参数修改,key键名，value值，word键值分隔符
	//文本必须顶格，参数格式为：id 间隔符 key 间隔符 value
	public static void setFile(String key,String value,String word,String filePath) {
		String[] arr=readTxtFile(filePath).split("\r\n");
		String text="";
		for (int i = 0; i < arr.length; i++) {
			String[] str=arr[i].split(word);
			if (str[1].equals(key)) {
				arr[i]=str[0]+word+str[1]+word+value;
			}
			text+=arr[i]+"\r\n";
		}
		printFile(text,filePath);
	}
}
