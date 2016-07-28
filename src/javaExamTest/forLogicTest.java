package javaExamTest;

/**
 * for（）循环的逻辑
 * @author Administrator
 *
 */

/*
 * for()的逻辑是:
 *      for(初始化语句；判断表达式；迭代语句){方法体}
 *      初始化语句：执行一次；
 *      判断表达式：成功，执行方法体，执行迭代语句；失败，跳出循环；
 *      迭代语句  ：迭代
 */
public class forLogicTest {
    static boolean foo(char c) {
       System.out.print(c);
       return true;
    }
    public static void main(String[] argv) {
       int i = 0;
       //for(65;88&&(i<2);67)
       for (foo('A'); foo('B') && (i < 2); foo('C')) {
           i++;
           foo('D');
       }
    }
}