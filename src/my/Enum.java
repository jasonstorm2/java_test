package my;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;

public class Enum {

    // 1.定义枚举类型--内部类

    public enum Light {
        // 利用构造函数传参
        RED(1,"红色"), GREEN(5,"绿色"), YELLOW(7,"黄色");
        // 定义私有变量
        public int nCode;
        public String ss;
        // 构造函数，枚举类型只能为私有
        private Light(int _nCode,String ss) {
            this.nCode = _nCode;
            this.ss = ss;
        }
        @Override
        public String toString() {
            return String.valueOf(this.nCode+"_"+this.ss);
        }
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 1.遍历枚举类型
        System.out.println("演示枚举类型的遍历 ......");
        testTraversalEnum();
        // 2.演示EnumMap对象的使用
        System.out.println("演示EnmuMap对象的使用和遍历.....");
        testEnumMap();
        // 3.演示EnmuSet的使用
        System.out.println("演示EnmuSet对象的使用和遍历.....");
        testEnumSet();
    }

    /**
     * 
     * 演示枚举类型的遍历
     */

    private static void testTraversalEnum() {
    	//values:Returns an array containing the constants of this enum type, in the order they are declared.
        Light[] allLight = Light.values();//获得该枚举类所有的枚举对象

        for (Light aLight : allLight) {		//遍历对象
        	/****模糊理解：枚举类内，每个枚举都是一个对象，一个枚举类，相当于一个包含很多对象的arraylist***/
            System.out.println("返回枚举的名字：" + aLight.name());//得到枚举的名字
            System.out.println("返回枚举的位置序列号：" + aLight.ordinal());//得到枚举的位置序列号

            System.out.println("打印枚举：" + aLight);//枚举的toString方法，没有覆盖toString方法的话，默认是返回枚举的名字
            System.out.println("打印枚举自变量：" + aLight.nCode);//得到枚举里面的参数
            System.out.println("打印枚举自变量：" + aLight.ss);//得到枚举里面的参数                       
            System.out.println("自定义的toString：" + aLight.toString());//可以覆盖toString方法
            System.out.println("类中其他枚举对象：" + aLight.GREEN);//具体某一个aLight
            System.out.println("类中其他枚举对象的变量：" + aLight.GREEN.ss);
        }
        ArrayList< Light> EnumArray = new ArrayList<Light>();
        for (Light aLight : allLight) {		//遍历对象
        	EnumArray.add(aLight);
        	System.out.println("###枚举对象："+aLight);
        }
    }

    /**
     * 
     * 演示EnumMap的使用，EnumMap跟HashMap的使用差不多，只不过key要是枚举类型
     */

    private static void testEnumMap() {

        // 1.演示定义EnumMap对象，EnumMap对象的构造函数需要参数传入,默认是key的类的类型

        EnumMap<Light, String> currEnumMap = new EnumMap<Light, String>(Light.class);

        currEnumMap.put(Light.RED, "红灯");

        currEnumMap.put(Light.GREEN, "绿灯");

        currEnumMap.put(Light.YELLOW, "黄灯");

        // 2.遍历对象

        for (Light aLight : Light.values()) {

            System.out.println("[key=" + aLight.name() + ",value="

            + currEnumMap.get(aLight) + "]");

        }

    }

    /**
     * 
     * 演示EnumSet如何使用，EnumSet是一个抽象类，获取一个类型的枚举类型内容<BR/>
     * 
     * 可以使用allOf方法
     */

    private static void testEnumSet() {

        EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);

        for (Light aLightSetElement : currEnumSet) {

            System.out.println("当前EnumSet中数据为：" + aLightSetElement);

        }

    }

}
