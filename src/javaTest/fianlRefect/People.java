package javaTest.fianlRefect;

public class People {
    private final Integer age = 66 ;
    private final Integer sex = 66;
    private Integer high = new Integer(10);

    public People(){
    }


    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    public Integer getHigh() {
        return high;
    }



    @Override
    public String toString() {
        return "People{" +
                "age=" + age +
                ", sex=" + sex +
                ", high=" + high +
                '}';
    }
}
