package my;

/**
 * ���� String���contain����������String�����Ƿ����ĳ��StringƬ��
 * �������������Ƭ�Σ�����true
 * @author Administrator
 *
 */
public class StringContainTest {
	public static void main(String[] args) {
		String str = "123,235���Ĵ���";
		int a = 5;
		String as = a+"";
		
		boolean inContain = StringContainTest.isStrContain(str, as);
		System.out.println("�Ƿ����ĳ�ֶΣ�"+inContain);
		System.out.println("�Ƿ����ĳ�ֶΣ�"+str.contains("35��"));
		
	}
	
	public static boolean isStrContain(String a,String b){
		return a.contains(b);
	}

}