package my;

import java.util.EnumMap;

public class MultiResultImp implements MultiResult {

    @Override
    public EnumMap<UserInfoProperty, Object> getUserInfoByName(String name) {
        EnumMap<UserInfoProperty,Object> retMap = new EnumMap<UserInfoProperty, Object>(UserInfoProperty.class);        
        
        retMap.put(UserInfoProperty.ROOM,"0003");
        retMap.put(UserInfoProperty.CELLPHONE,"00004");
        retMap.put(UserInfoProperty.Name,name);
        return retMap;
    }
}