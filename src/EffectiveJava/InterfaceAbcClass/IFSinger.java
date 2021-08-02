package EffectiveJava.InterfaceAbcClass;

public interface IFSinger {
    void sing();

    void sing2();
}

interface IFDancer {
    void dance();
}

/**
 * �ӿ� �̳нӿڣ���һ����Ҫ��д����
 */
interface IFSingerIFDancer extends IFSinger, IFDancer {
    @Override
    void sing();

    void mix();
}

/**
 * �ӿ���������ϣ�����Ϊ��Ϊ�Ǽ�ʵ�֡�
 * �����ฺ��������ӿ�ʵ����صĹ���
 */
abstract class AbstractIFSinger implements IFSinger {
    String sing = "abcd";

    //ʵ���˽ӿڵķ���,��һ��ʵ�����нӿڵķ�������ȡʵ��ʱ������ʵ�ֻ�û��ʵ�ֵĽӿڷ���
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
     * �������еĳ��󷽷���ʵ�������ʵ��
     */
    public abstract void absSing2();

//    public abstract void absSing3();
}

/**
 * �ڳ�������û��ʵ�ֵķ������������б���ʵ��
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
 * ��ʵ�ֽӿڣ�����ʵ�����еĽӿڶ���ķ���
 */
class test implements IFSingerIFDancer {

    static IFSinger createIFSinger() {
        //ʵ����������࣬����ʵ�ֳ�����δʵ�ֵķ���
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