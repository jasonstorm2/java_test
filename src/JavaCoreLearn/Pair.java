package JavaCoreLearn;

public class Pair<T,U> {
	private T first;
	private T second;
	private U ufirst;
	
	public Pair(){ 
		first = null; second = null;
		}
	public Pair(T first,T second,U f){
		this.first = first;
		this.second = second;
		this.ufirst = f;
	}
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public T getSecond() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	
	public U getUFirst(){
		return ufirst;
	}
	
	public void setUFirst(U f){
		this.ufirst = f;
	}
	
	
}
