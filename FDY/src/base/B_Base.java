package base;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//支持基础操作例如Array、list、Vector、数字处理、随机算法等的常用操作
public class B_Base {
	//生成随机码,字母或数字
	public static String Code(int length){
		String s="";
		for (int i = 0; i < length; i++) {
			s+=C_Cmd.lettersNow[Rand(0,42)];
		}
		return s;
	}
	//生成相对于arr序列唯一的字符码
	public static String Code(int length,String[] arr) {
		String code=Code(length);
		if (Arr_isHave(arr, code)) {
			code=Code(length,arr);
		}
		return code;
	}
	//生成6位验证码
	public static String Code(){
		int i=0;
		Random random=new Random();
		i=random.nextInt(899999)+100000;
		String s=Integer.toString(i);
		return s;
	}
	
	//生成指定整数之间的一个随机数(包含star，不包含end)
	public static int Rand(int star,int end){
		int i=0;
		Random random=new Random();
		int x=end-star;
		i=random.nextInt(x)+star;
		return i;
	}
	
	//生成指定整数之间的一个随机数(包含star，不包含end，相对arr唯一)
	public static int Rand(int star,int end,String[] arr){
		int i=0;
		Random random=new Random();
		int x=end-star;
		i=random.nextInt(x)+star;
		if (Arr_isHave(arr, i+"")) {
			i=Rand(star,end,arr);
		}
		return i;
	}

	//精准加法
	public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
	}
	
	//精准减法，vi为被减数
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    
    //精准乘法
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    
	//精准除法运算，v1为被除数，当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("精确位数必须大于等于0");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    //精确的四舍五入处理
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException("精确位数必须大于等于0");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
	
	//数组转字符串,e为元素间分隔符
	public String Arr_toString(int[] arr,String e) {
		String string="";
		for (int i = 0; i < arr.length; i++) {
			if (i==0) {
				string=string+arr[i];
			} else {
				string=string+e+arr[i];
			}
		}
		return string;
	}

	//蛮力法查找数组是否含有对象元素,e为元素
	public static boolean Arr_isHave(String[] arr,String str) {
		boolean b=false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(str)) {
				b=true;
				return b;
			} 
		}
		return b;
	}
	
	//蛮力法查找数组元素下标,e为元素
	public static int Arr_getIndex(String[] arr,char e) {
		int index=0;
		String str=String.valueOf(e);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(str)) {
				index=i;
			} 
		}
		return index;
	}
	
	//数组转list
	/**list支持但不限于以下操作：
	 * 添加方法是：.add(e)末尾添加， .add(index, element);指定位置插入；
	 * 获取方法是：.get(index)；　　
	 * 删除方法是：.remove(index)； 按照索引删除；.remove(Object o)； 按照元素内容删除；
	 * list中是否包含某个元素；.contains（Object o）； 返回true或者false
	 * 根据索引将元素数值改变(替换)：.set(index, element); 和 .add(index, element); 的不同；set时设置并替换，add相当于插入，时设置并将原元素及其后元素后移
	 * **/
	public List<String> Arr_tolist(String[] arr) {
		List<String> list=Arrays.asList(arr);
		return list;
	}
	public List<Integer> Arr_tolist(Integer[] arr) {
		List<Integer> list=Arrays.asList(arr);
		return list;
	}
	public List<Double> Arr_tolist(Double[] arr) {
		List<Double> list=Arrays.asList(arr);
		return list;
	}
	
	//list转数组
	public String[]  List_toArrstr(List<String> list) {
		String[] arr=(String[]) list.toArray();
		return arr;
	}
	public Integer[]  List_toArrint(List<Integer> list) {
		Integer[] arr=(Integer[]) list.toArray();
		return arr;
	}
	public Double[]  List_toArrdou(List<Double> list) {
		Double[] arr=(Double[]) list.toArray();
		return arr;
	}
    
    /*
    //测试专用
	public static void main(String[] args) {
		System.out.println(round(3.141592653,2));
	}*/

}
