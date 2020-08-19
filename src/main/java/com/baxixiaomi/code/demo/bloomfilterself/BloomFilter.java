package com.baxixiaomi.code.demo.bloomfilterself;

import java.util.BitSet;

/**
 * 布隆过滤器
 *
 * @author 吧嘻小米
 * @date 2020/06/21
 */
public class BloomFilter {

    /**
     * 默认大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * 创建不同的hash函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 位数组。数组中的元素只能是0或1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 存放hash函数的类的数组
     */
    private SimpleHash[] function = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个包含hash函数的类的数组，每个类中的hash函数都不一样
     */
    public BloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            function[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 布隆过滤器中添加元素
     *
     * @param value
     */
    public void add(Object value) {
        for (SimpleHash simpleHash : function) {
            bits.set(simpleHash.hash(value), true);
        }
    }

    /**
     * 判断元素是否存在于布隆过滤器中
     *
     * @param value
     * @return
     */
    public boolean contains(Object value) {
        boolean flag = true;
        for (SimpleHash simpleHash : function) {
            flag = flag && bits.get(simpleHash.hash(value));
        }
        return flag;
    }

    /**
     * 静态内部类
     */
    private static class SimpleHash {
        private int cap;

        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(Object value) {
            int hash;
            return (null == value) ? 0 : Math.abs(seed * (cap - 1) & ((hash = value.hashCode()) ^ (hash >>> 16)));
        }
    }
}
