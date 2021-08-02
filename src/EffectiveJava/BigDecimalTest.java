package EffectiveJava;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * bigdecimal 性能计算。
 * 1千万add273毫秒
 * 1千万add multiply 438毫秒
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        long starttime = System.currentTimeMillis();
        BigDecimal bigDecimal = new BigDecimal("10.0");
        for (int i = 0; i < 10000000; i++) {
            bigDecimal.add(new BigDecimal("0.45")).multiply(new BigDecimal("1.6"));
        }
        long endtime = System.currentTimeMillis();
        System.out.println(endtime - starttime);
    }
}
