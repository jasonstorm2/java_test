package EffectiveJava.SingleTon;

public class SingleTonMain {
    public static void main(String[] args) {
        SingleTon singleTonTest = SingleTon.getInstance();
        singleTonTest.setKey("sex");
        System.out.println(singleTonTest.toString());
    }
}
