package JavaCoreLearn;
/**
 * String compareTo方法的比较规则： 
 * 逐个字符比较大小，若大则返回整数，如果小则返回负数，相等则为0.
 * 
 * String1.compareTo(String2) > 0 说明String1比String2大
 * 
 * @author LiZhenhua
 *
 */
public class PairTest1 {
	public static void main(String[] args) {
		String[] words = {"bary","had","adfdfdfdf","little","lamb","oliva"};
		Pair<String,Integer> mm = ArrayAlg.minmax(words);
		System.out.println("min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());
		System.out.println("length:"+mm.getUFirst());		
	}

}

class ArrayAlg{
	// 泛型具体化，返回一个Pair对象
	public static Pair<String,Integer> minmax(String[] a){
		if(a == null||a.length==0)return null;
		String min = a[0];
		String max = a[0];
		for (int i = 0; i < a.length; i++) {
			if(min.compareTo(a[i])>0) min = a[i];
			if(max.compareTo(a[i])<0) max = a[i];			
		}
		return new Pair<>(min,max,a.length);
	}
}
