package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringTest {
	public static void main(String[] args) {
		List<Integer> intt = new ArrayList<Integer>(Arrays.asList(0, 0, 1));
		String str = listToStr(intt);
		System.out.println("��ӡ������"+str);		
		System.out.println("��������"+countStars("1"));
		
		String s = "100";
		String s2 = new String("100");
		String s3 = new String("100");
		
		System.out.println(StringValueEqual(s, s2));
		System.out.println(StringValueEqual(s2, s3));
		
		System.out.println("1------------------------");
		System.out.println(StringReferenceEqual(s, s2));
		System.out.println(StringReferenceEqual(s2, s3));
		System.out.println("2------------------------");
		
		System.out.println(StringValueEqual("100", "101"));
		System.out.println(StringValueEqual("100", "100"));
		System.out.println(StringValueEqual("a", "b"));
		System.out.println(StringValueEqual("aa", "aa"));
		
		System.out.println("�����ַ���������"+StrCompare("010", "001"));
		String[] arr =new  String[]{"a","b","c"};
		int[] arr2 =new  int[]{1,5,6};
		System.out.println("========================================");
		System.out.println(getIndexInArray("g", arr));
		System.out.println(getIndexInArray(7, arr2));
		
		System.out.println(getValueByArrayIndex(arr, 1));
		System.out.println(getValueByArrayIndex(arr2, 2));
		System.out.println("========================================");
		isStringTheSameRef();
		
	}
	
	public static<T> int getIndexInArray(T value,T array){
		int res = -1;
		if(array instanceof String[]){
			String[] arr = (String[])array;
			String str = (String)value;
			for(int i=0;i<arr.length;i++){
				if(str == arr[i]){
					res = i;
					break;
				}
			}
			
		}else if(array instanceof int[]){
			int[] arr = (int[])array;
			int str = (Integer) value;
			for(int i=0;i<arr.length;i++){
				if(str == arr[i]){
					res = i;
					break;
				}
			}
		}
		return res;		
	}
	
	public static<T> Object getValueByArrayIndex(T array,int index){
		Object res =null;
		if(array instanceof String[]){
			String[] arr = (String[])array;
			if(index<arr.length){
				res = arr[index];
			}
		}else if(array instanceof int[]){
			int[] arr = (int[])array;
			if(index<arr.length){
				res = arr[index];
			}
		}
		return res;
	}

	/**
	 * List<Integer> ת���� String ��ʽ
	 * @param stars
	 * @return
	 */
	private static String listToStr(List<Integer> stars){
    	String result = "";
    	for(Integer i:stars){
    		result+=i;
    	}
    	return result;
    }
	
	/**
	 * �ж�һ��str�м���1
	 * @param str
	 * @return
	 */
	private static int countStars(String str) {
		// StringUtils ֵ�ж�unicode +�� -�� . ���߶�����������unicode ����,���Բ����жϸ���
		if (str == null || StringUtils.isEmpty(str) || !StringUtils.isNumeric(str)) {
			return 0;
		}
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			String s = String.valueOf((str.charAt(i)));
			if (s.equals("1")) {
				result++;
			}
		}
		return result;
	}
	
	/**
	 * �ж�����String ��ֵ�Ƿ����
	 * @param str1
	 * @param str2
	 * @return
	 */
	private static boolean StringValueEqual(String str1,String str2){
		return str1.equals(str2);		
	}
	
	/**
	 * �ж�����String �������Ƿ���ͬ
	 * @param str1
	 * @param str2
	 * @return
	 */
	private static boolean StringReferenceEqual(String str1,String str2){
		return (str1 ==  str2);		
	}
	
	/**
	 * ���� int ������
	 * @param a
	 * @param b
	 * @return
	 */
	private static int isEqual(int a,int b){
		System.out.println("a|b:"+(a|b));
		return a|b;
	}
	
	
	/**
	 * �����ַ��� ������
	 * @param a
	 * @param b
	 * @return
	 */
	private static String StrCompare(String a,String b){
		String str = "";
		if(a.length()!=b.length()){
			return "";
		}		
		for (int i = 0; i < a.length(); i++) {	
			int a1 = Integer.valueOf(String.valueOf((a.charAt(i))));
			int b1 = Integer.valueOf(String.valueOf((b.charAt(i))));
			str+=isEqual(a1, b1);
		}
		return str;
	}
	
	/**
	 * �ж������ַ����������Ƿ�һ��
	 */
	private static void isStringTheSameRef(){
		String s1 = "program1";
		String s2 = new String("program1");
		String s3 = "pro"+"gram1";
		System.out.println(s1==s2); //f
		System.out.println(s1==s3); //t
		System.out.println(s1 == s1.intern()); //t
		
	}

}
