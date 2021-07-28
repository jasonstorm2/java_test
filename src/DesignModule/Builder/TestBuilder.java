package DesignModule.Builder;

/**
 * 建造模式
 * 外部类中有一个静态内部类，负责构造外部类的参数，最后成成一个外部类的对象、
 * 外部类的成员变量只有get方法，并且每个变量都是final的,变量的设置只有build类才能设置
 * 外部类的对象是个不可变对象！！
 *
 * 为什么不直接用外部类来设置呢？外部类的参数每次set都返回外部类对象实例，javabeans模式？
 *
 *
 * 我们通常构造一个有很多参数的对象时有三种方式：构造器重载，JavaBeans模式和builder模式
 * 构造器重载需要许多的构造器，
 * javabeans set,get 生成对象后，因为构造过程被分到了几个调用中，在构造过程中JavaBeans可能处于不一致的状态，类无法仅仅通过检验构造器参数的有效性来保证一致性
 * builder模式            builder模式将属性定义为不可变的，然后定义一个内部静态类Builder来构建属性，
 *                      再通过一个只有Builder参数的构造器来生成Product对象。Builder的setter方法返回builder本身，
 *                      以便可以将属性连接起来。我们就可以像下面这样使用了
 *
 *
 */
public class TestBuilder {

    public static void main(String[] args) {

        //第一个外部类对象：
        //先构造一个外部类的内部类builer对象，设置各个参数后，调用build方法，构造外部类的对象
        CompanyClient client = new CompanyClient.Builder()
                .setCompanyName("百度")
                .setCompanyAddress("海定区百度大厦")
                .setCompanyRegfunds(5)
                .setmPerson("1000人以上")
                .build();
        System.out.println("构造出一个公司：" + client.toString());

        System.out.println("---------------------分隔符");

        //第二个外部类对象：
        CompanyClient.Builder builder = new CompanyClient.Builder();
        builder.setCompanyName("华为");
        builder.setCompanyAddress("海定区百度大厦");
        builder.setCompanyRegfunds(20);
        builder.setmType("通信科技行业");
        CompanyClient client1 = builder.build();
        System.out.println("构造出另一个公司：" + client1.toString());
    }
}