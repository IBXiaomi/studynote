package com.baxixiaomi.studynotes.test.chapter4;


import java.util.ArrayList;
import java.util.List;

/**
 * 生成25个随机的int数值，判断当前与紧随其后数值的关系
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public class RandomNumber {
    public static void main(String[] args) {
        // 初始化一个大小为25的集合
        List<Integer> list = new ArrayList<Integer>(25);
        for (int i = 0; i < 25; i++) {
            // Math.random()方法生成的随机数在[0,1)的范围内
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list.size());
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                System.out.println("第一个随机数" + list.get(i) + "和紧随其后的" + list.get(i + 1) + "相等");
            } else if (list.get(i) > list.get(i + 1)) {
                System.out.println("第一个随机数" + list.get(i) + "比紧随其后的" + list.get(i + 1) + "大");
            } else {
                System.out.println("第一个随机数" + list.get(i) + "比紧随其后的" + list.get(i + 1) + "小");
            }
        }
    }
}

