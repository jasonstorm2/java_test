package EffectiveJava.静态内部类;

import com.sun.javafx.css.CalculatedValue;
import com.sun.org.apache.xpath.internal.operations.Operation;

/**
 * 非静态内部类：
 * 保留一个外部类的引用,
 * 可以作为外部类的一个成员变量
 * @author lzh
 */
public class Room {
    public int chair;
    protected Table table;
    private Fruit fruit;
    private AnimalEnum animalEnum;
    private static int cost;

    /**
     * 构造函数
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
     * @param table 设置参数
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
     * private 方法（API）不对外开放。在生成的JavaDOC里面没有该方法
     *
     * 计算所有物品的数量
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
        //引用外部类
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

        //访问外部类静态成员变量
        public int getRoomCost(){
            return Room.cost;
        }
        //访问外部类非静态成员变量
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
        //非静态内部类需要先实例化外部类
        room.setTable(room.new Table());

        room.fruit = Fruit.APPLE;
    }

}
