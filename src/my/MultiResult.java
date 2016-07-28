package my;

import java.util.EnumMap;

public interface  MultiResult {

	    enum UserInfoProperty {
	        ROOM,CELLPHONE,Name
	    }
	    public EnumMap<UserInfoProperty,Object> getUserInfoByName(String name);
}
