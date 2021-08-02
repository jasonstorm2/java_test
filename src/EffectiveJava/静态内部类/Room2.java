package EffectiveJava.静态内部类;

public class Room2 {
    private Table table = new Table();
    private static Table table2 = new Table();

    private  int cupNum;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getCupNum() {
        return cupNum;
    }

    public void setCupNum(int cupNum) {
        this.cupNum = cupNum;
    }

    public Room2(){
        cupNum = 4;
        table = new Table();
    }

    //Room类需要返回两个参数，需要一个类来包装
    public Table calculate(int x,int y){
        Table table = new Table(x,y);
        return table;
    }


    @Override
    public String toString() {
        return "Room2{" +
                "tableNum=" + table.getNum() +
                ", cupNum=" + cupNum +
                '}';
    }

    private static class Table{
        //访问外部类非静态成员--报错
//        public Table getOuterClassTable1(){
//            return Room2.table;
//        }
        //访问外部类静态成员
        public Table getOuterClassTable2(){
            return Room2.table2;
        }

        int num;
        static int color;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public static int getColor() {
            return color;
        }

        public static void setColor(int color) {
            Table.color = color;
        }

        public Table(){
            num = 6;
        }

        public Table(int x,int y){
            num = x;
            color = y;
        }

    }

    public static void main(String[] args) {
        Table table1 = new Table();
        table1.setNum(111);
        System.out.println(table1.getNum());

        Room2 room2 = new Room2();
        System.out.println(room2.toString());

        Table tableCal = room2.calculate(111,333);
        System.out.println(tableCal.getNum() + "__"+Table.getColor());

    }
}
