package mymain;

import base.B_Base;
import base.B_time;
import base.I_Sms;

public class Mymain {
	public static void main(String[] args) {
		System.out.println(I_Sms.sendms_aliyun("123456", "15087305027"));
	}
}
