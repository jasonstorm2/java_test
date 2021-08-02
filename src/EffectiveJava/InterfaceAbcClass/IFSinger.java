package EffectiveJava.InterfaceAbcClass;

public interface IFSinger {
    void sing();

    void sing2();
}

interface IFDancer {
    void dance();
}

/**
 * 接口 继承接口，不一定需要重写方法
 */
interface IFSingerIFDancer extends IFSinger, IFDancer {
    @Override
    void sing();

    void mix();
}

/**
 * 接口与抽象类结合，该行为成为骨架实现。
 * 抽象类负责所有与接口实现相关的工作
 */
abstract class AbstractIFSinger implements IFSinger {
    String sing = "abcd";

    //实现了接口的方法,不一定实现所有接口的方法，获取实例时，必须实现还没有实现的接口方法
    @Override
    public void sing() {
        System.out.println(sing);
    }

    public void absSing() {

    }

    public void sing22() {
        System.out.println("AbstractIFSinger sing22");
    }

    /**
     * 抽象类中的抽象方法，实现类必须实现
     */
    public abstract void absSing2();

//    public abstract void absSing3();
}

/**
 * 在抽象类中没有实现的方法，在子类中必须实现
 */
class Singer extends AbstractIFSinger {

    @Override
    public void sing2() {

    }

    @Override
    public void absSing2() {

    }

    public String getSingerName(){
        return "";
    }
}

//class subSingger extends Singer{
//    public void getSingerName(){
//
//    }
//
//}


/**
 * 类实现接口，必须实现所有的接口定义的方法
 */
class test implements IFSingerIFDancer {

    static IFSinger createIFSinger() {
        //实例化抽相关类，必须实现抽象类未实现的方法
//        IFSinger ifSinger =  new AbstractIFSinger() {
        AbstractIFSinger ifSinger = new AbstractIFSinger() {
            @Override
            public void absSing2() {

            }

            @Override
            public void sing2() {
                System.out.println("sing2...");
            }
        };
        ifSinger.sing();
        ifSinger.sing2();
        ifSinger.absSing();
        ifSinger.sing22();
        return ifSinger;
    }

    @Override
    public void sing() {

    }

    @Override
    public void sing2() {

    }

    @Override
    public void mix() {

    }

    @Override
    public void dance() {

    }

    public static void main(String[] args) {
        createIFSinger();
    }
}