package DesignModule.Builder;

/**
 * ����ģʽ
 * �ⲿ������һ����̬�ڲ��࣬�������ⲿ��Ĳ��������ɳ�һ���ⲿ��Ķ���
 * �ⲿ��ĳ�Ա����ֻ��get����������ÿ����������final��,����������ֻ��build���������
 * �ⲿ��Ķ����Ǹ����ɱ���󣡣�
 *
 * Ϊʲô��ֱ�����ⲿ���������أ��ⲿ��Ĳ���ÿ��set�������ⲿ�����ʵ����javabeansģʽ��
 *
 *
 * ����ͨ������һ���кܶ�����Ķ���ʱ�����ַ�ʽ�����������أ�JavaBeansģʽ��builderģʽ
 * ������������Ҫ���Ĺ�������
 * javabeans set,get ���ɶ������Ϊ������̱��ֵ��˼��������У��ڹ��������JavaBeans���ܴ��ڲ�һ�µ�״̬�����޷�����ͨ�����鹹������������Ч������֤һ����
 * builderģʽ            builderģʽ�����Զ���Ϊ���ɱ�ģ�Ȼ����һ���ڲ���̬��Builder���������ԣ�
 *                      ��ͨ��һ��ֻ��Builder�����Ĺ�����������Product����Builder��setter��������builder����
 *                      �Ա���Խ������������������ǾͿ�������������ʹ����
 *
 *
 */
public class TestBuilder {

    public static void main(String[] args) {

        //��һ���ⲿ�����
        //�ȹ���һ���ⲿ����ڲ���builer�������ø��������󣬵���build�����������ⲿ��Ķ���
        CompanyClient client = new CompanyClient.Builder()
                .setCompanyName("�ٶ�")
                .setCompanyAddress("�������ٶȴ���")
                .setCompanyRegfunds(5)
                .setmPerson("1000������")
                .build();
        System.out.println("�����һ����˾��" + client.toString());

        System.out.println("---------------------�ָ���");

        //�ڶ����ⲿ�����
        CompanyClient.Builder builder = new CompanyClient.Builder();
        builder.setCompanyName("��Ϊ");
        builder.setCompanyAddress("�������ٶȴ���");
        builder.setCompanyRegfunds(20);
        builder.setmType("ͨ�ſƼ���ҵ");
        CompanyClient client1 = builder.build();
        System.out.println("�������һ����˾��" + client1.toString());
    }
}