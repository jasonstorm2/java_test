package JavaCoreLearn;

import java.util.Date;
import java.util.GregorianCalendar;


public class Employee implements Comparable<Employee>,Cloneable{
	
	static{
		b=7;
		
	}	
	static int b = 9;
	static int a = 8;
	
	
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee(String n,double s){
		this.name = n;
		this.salary = s;
		
	}
	
	
	
	public String getName(){
		return name;
	}

	public double getSalary(){
		return salary;
	}
	
	public Date getHireDay(){
		return hireDay;
	}
	
	public void setHireDay(int year,int month,int day){
		Date newHireDay = new GregorianCalendar(year,month-1,day).getTime();
		hireDay.setTime(newHireDay.getTime());
	}
	
	public void raiseSalary(double byPercent){
		double raise = salary * byPercent/100;//增长多少
		salary+=raise;//计算总值
	}

	/**
	 * 重写接口的方法
	 * Compares employees by Salary
	 * 比较薪水
	 */
	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		return Double.compare(salary, o.salary);
	}
	
	public Employee clone() throws CloneNotSupportedException {
		Employee cloned = (Employee) super.clone();//浅拷贝
		cloned.hireDay = (Date)hireDay.clone();     //深拷贝
		return cloned;
	}
	
	public static boolean testCast(int a){
		boolean result = false;
		double re = 0;
		try {
			re =  1/a;
			result = true;
		} catch (Exception e) {
			System.out.println("输入分母为零");
		}
		System.out.println("last-------------------");
		return result;
		
	}
	
}
