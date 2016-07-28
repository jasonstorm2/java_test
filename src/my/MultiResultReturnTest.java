package my;

import java.util.EnumMap;

public class MultiResultReturnTest {
	  public static void main( String[] args )
	    {
	        MultiResult testService = new MultiResultImp();
	        String name = "testName";
	        //EnumMap<enum,object>
	        EnumMap<MultiResult.UserInfoProperty,Object> userInfo = testService.getUserInfoByName(name);
	         userInfo.entrySet().iterator();
	        System.out.println(userInfo.get(MultiResult.UserInfoProperty.Name));
	        System.out.println(userInfo.get(MultiResult.UserInfoProperty.ROOM));
	        System.out.println(userInfo.get(MultiResult.UserInfoProperty.CELLPHONE));
	    }

}
