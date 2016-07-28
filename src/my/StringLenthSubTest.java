package my;

public class StringLenthSubTest {
	public static void main(String[] args) {
		String content ="我是中国人呢啊啊啊啊啊我要我还要";
		
		int strlen=strLength(content);
		if(strlen>10){
			 content = content.substring(0,10);
		}
		System.out.println(content);
		String str = "100,100";
		System.out.println("str的长度："+str.length());
		int[] i = new int[]{1,4};
		System.out.println(i[0]+","+i[1]);
		i[0]=i[1];
		System.out.println(i[0]+","+i[1]);
	}
	
    /**
     * 描述：判断一个字符串是否为null或空值.
     *
     * @param str 指定的字符串
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
    /**
     * 描述：获取字符串的长度.
     *
     * @param str 指定的字符串
     * @return  字符串的长度（中文字符计2个）
     */
     public static int strLength(String str) {
         int valueLength = 0;
         String chinese = "[\u0391-\uFFE5]";
         if(!isEmpty(str)){
	         //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
	         for (int i = 0; i < str.length(); i++) {
	             //获取一个字符
	             String temp = str.substring(i, i + 1);
	             //判断是否为中文字符
	             if (temp.matches(chinese)) {
	                 //中文字符长度为2
	                 valueLength += 2;
	             } else {
	                 //其他字符长度为1
	                 valueLength += 1;
	             }
	         }
         }
         return valueLength;
     }

}
