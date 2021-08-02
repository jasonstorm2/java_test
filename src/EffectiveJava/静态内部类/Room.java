package EffectiveJava.��̬�ڲ���;

import com.sun.javafx.css.CalculatedValue;
import com.sun.org.apache.xpath.internal.operations.Operation;

/**
 * �Ǿ�̬�ڲ��ࣺ
 * ����һ���ⲿ�������,
 * ������Ϊ�ⲿ���һ����Ա����
 * @author lzh
 */
public class Room {
    public int chair;
    protected Table table;
    private Fruit fruit;
    private AnimalEnum animalEnum;
    private static int cost;

    /**
     * ���캯��
     */
    public Room(){
        animalEnum = AnimalEnum.DOG;
        chair = 4;
        table = new Table();
    }

    public Table getTable() {
        return table;
    }

    /**
     *
     * @param table ���ò���
     */
    public void setTable(Table table) {
        this.table = table;
    }

    public int getChair() {
        return chair;
    }

    public void setChair(int chair) {
        this.chair = chair;
    }

    /**
     * private ������API�������⿪�š������ɵ�JavaDOC����û�и÷���
     *
     * ����������Ʒ������
     * @return
     */
    private int ObjectNum(){
        return chair+table.getNum();
    }

    @Override
    public String toString() {
        return "Room{" +
                "chair=" + chair +
                ", tableLegNum=" + table.getLegNum() +
                " tablechair="+ table.getNum()+
                '}';
    }

    public class Table{
//        public static int color;
        int legNum;
        //�����ⲿ��
        int num = Room.this.getChair();

        public int getNum() {
            return num;
        }

        public void setLegNum(int legNum) {
            this.legNum = legNum;
        }

        public int getLegNum() {
            return legNum;
        }

        public Room getOuterClass(){
            return Room.this;
        }

        //�����ⲿ�ྲ̬��Ա����
        public int getRoomCost(){
            return Room.cost;
        }
        //�����ⲿ��Ǿ�̬��Ա����
        public int getRoomChair(){
            return chair;
        }
    }

    public enum Fruit{
        APPLE,
        ORANGE,
        BANANA
    }


    public static void main(String[] args) {
        Room room = new Room();
        room.setChair(5);
        room.table.setLegNum(9);
        System.out.println(room.toString());
        //�Ǿ�̬�ڲ�����Ҫ��ʵ�����ⲿ��
        room.setTable(room.new Table());

        room.fruit = Fruit.APPLE;
    }

}
