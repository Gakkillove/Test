package base;
//支付宝基础信息
public class AlipayConfig {

	/**
	 * 正式环境请求地址
	 */
	 public static String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";
    /**
      * 支付宝分配给开发者的应用ID
      * 
     */
    public static String APP_ID = "2019081666245643";
	
    /**
      * 支付接口名称
     */
    public static String PAY_METHOD = "alipay.trade.app.pay";
    /**
      * 提现接口名称
     */
    public static String TRANSFER_METHOD = "alipay.fund.trans.toaccount.transfer";
    /**
     * 仅支持JSON
     */
    public static String FORMAT = "JSON";
    /**
      * 请求使用的编码格式
     */
    public static String CHARSET = "utf-8";
    /**
      * 商户生成签名字符串所使用的签名算法类型
     */
    public static String SIGN_TYPE = "RSA2";
    /**
      * 支付宝公钥
     */
   public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArpE98StIsjAXke0t0b3arHR2AEgclyGce6GjD7vgHaMDC7zyOfbcKW/pQKi5T+UetBk1CaRUv4OgEe/mZ0Ja4dJ5l8OtqtF641Bh5oAKTQVBj4mFI3oYP7IvCPiLpzR1jseqVqjRb/3yBN3V1W5ObRWGUwonFSSwv55uWrt69QjJQwV/47zx2RsAHRB6cIf8EUMADjp1Bqwk8T7k5Hx75z5PZkCEtd0SoQDI5oCeDzr8ylToaouEJJY9xoczzZgCilssf3goQEyfGhJGE2QmnSPUQZDGb0TUN6vLzMglq7xMf5fHoWcrLugbUDc1fSYNGPybG7rBcwlZjLEHH9xvpQIDAQAB";
    /**
      * 商户私钥
     */
    public static String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC/Qgs5PhFndMplEzhlIVM3wj6TdO9CKqGogfbZ4eeYXGlLbILpEjBqhsc9gOYNCcg2YuV93KpZ3IhGbvL0fgayXqg5pKVC1R+w+pAlH6EC+wOBn9W8UdtfjhQpYKsdTATsjyqkGHwDlwioltpL92SR1sI44Qow3TMxKsv7MZx+FGhO5X6SX9OmM4nanhA8azhRoYY0ciyod8uHxxyfkRYpv/1bZsQDEFA9QQTRND12jBbvr/N0+uHBcxwX9fP9ebTkpx0W1c4XU4/HcqGFM6nk/MotQaB89UH71x58cf2CyOnylS5bOlUc5P4hB7WNjMQGo5jpoQaR2KuMHqplUYr/AgMBAAECggEAQysGlgveQN8bVXPyklGp0CM/prmibTJbo7pA3zQfCbsqn3p0XzYsGr1Kq6bhJCMTVUZWImWkzfpNQa8lVAgOggYMPnI8N8RxZM4aYW9CNLHDAXsIrSJyUJStL/rrHVbdQ9bjus1+gCRCvjJ8Z8FyaGVrwF4IHjEREWteR9GcjAozHHrvxaBRJzLooR6FHJRQx6vA3qoImvaO6RtK/bpbu9mx2IRFBao6t33ido87W6LlnrWPAyXTNPKrctXf8NjxsunNC5JZteN5va3UzNut5WsIU4H/hfBK3cdzSgksGIllUzxyi5UbQ3yejJ8XQgT9rU6cDUqYdxJ6DjPb5mRHQQKBgQDoWpHFpmr6rZAPOm5v4HQDPV/iHGEUB54lEWhdPkUCDU1oulAEMxW4Pl+CKOS+RExwHW+mvFpt+tv3Q9PKNmIhFRmKwfi+LIzF4idBRtyPOIzr72oaPvOE7uJRVGX2asvcqY4D+9eEQiwAi7k/Jk3FbRE8Q08QymkvIW7R/z3KkwKBgQDSuNHwni9//ruvFH6SnyGAwhN0GyYanovFQPTnJLru786zv4qhcrwJEnM9ju6TGKQv1cZrlwbjarIwmhpNS6ow0gFtE9MgYEN0ujtrsTXLYPhkFgqQ67Ns/Rd+CQgiIAZUa0Qkf94/Aclt9AJXEcbIpshFBrPGmaqOpoF2Z61FZQKBgCDnmpcz/xhq9ejCLJuZXLSMv4Rk4naUsZesRGxYAivNOHYpVGWT2nKSGrJzGhm70FtcP9CMAf5rNTVqHJcZZoIq28gjuhcsde8cHNUItPlvV72d1RfMbG4Z6mD8I8hmzWH9fQghZR7sxBB7uQsxYHgh/vvayFqnX4f2IxyIGfcTAoGATFM5Z8hEOD5SSPZJal5NSlABr9ptkNLAmbAPhPGe7BDcc+r7EcN3LGoMZ82vyIPyZIkwF6RakGEDLeuDf1/aw1gtwA6gOGflxw/1mepUK9hx3/7D0P4uvVlm+vXWhlXHNSitQ/M00umYxkTN3C/KKr+OZsIot9TNHGJfHj4rWqkCgYBRkgeRSccjhiVqjcwtYyBYg9T6kc1v5L0maqAf9Cpq9YAE1af67gVn/NncwYQS+Fyk2TJb8QZhlgnIV8P7IyMoevRXEmEE38iA25J4cCX0gxo4Ij8qR/AFUZdowQ9KeSluQMntIUs+wWCq+FDk7cqZiymMS2nAdMHXizDSIK1apA==";
   
    /**
     * 回调地址
    */
   public static String CALLBACK_URL = "http://www.hsjll.com/AlipayReturn";
}
