package com.baxixiaomi.code.demo.basic_calculation;

/**
 * 算法的优势
 * 比较相同的运算的不同时间
 *
 * @author 吧嘻小米
 * @date 2020/06010
 */
public class BasicCalculation {
    public static void main(String[] args) {
        Calculation1();
        Calculation2();
    }

    /**
     * 在计算机的存储中，指令读取的顺序如下
     * 读取3到运算器
     * 乘以3在运算器
     * 乘以8在运算器
     * 存储8*3*3在存储器
     * 读取2到运算器
     * 乘以3在运算器
     * 加上8*3*3在运算器
     * 加上 6在运算器
     * 一共经历了8步
     *
     * 耗时214000纳秒
     */
    private static void Calculation1() {
        int begin = (int) System.nanoTime();
        int result = 8 * 3 * 3 + 2 * 3 + 6;
        System.out.println(result);
        int end = (int) System.nanoTime();
        System.out.println(end - begin);
    }

    /**
     * 在计算机的存储中，指令读取的顺序如下
     * 读取3到运算器
     * 乘以8在运算器
     * 加上2到运算器
     * 乘以3在运算器
     * 加上6在运算器
     * 一共经历了5步
     *
     * 耗时14300纳秒
     */
    private static void Calculation2() {
        int begin = (int) System.nanoTime();
        int result = (8 * 3 + 2) * 3 + 6;
        System.out.println(result);
        int end = (int) System.nanoTime();
        System.out.println(end - begin);
    }
}
