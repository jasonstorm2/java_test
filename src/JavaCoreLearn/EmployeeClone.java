package JavaCoreLearn;

public class EmployeeClone implements Cloneable{
	
	public EmployeeClone clone() throws CloneNotSupportedException{
		
		return 	(EmployeeClone) super.clone();
	}

}
