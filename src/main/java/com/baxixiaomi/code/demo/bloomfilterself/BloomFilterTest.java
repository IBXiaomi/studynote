package com.baxixiaomi.code.demo.bloomfilterself;

/**
 * 测试布隆过滤器
 *
 * @author 吧嘻小米
 * @date 2020/06/21
 */
public class BloomFilterTest {
    public static void main(String[] args) {
        test01();
        test02();

    }

    private static void test01() {
        String str1 = "ba xi xiao mi";
        String str2 = "BA XI XIAO MI";
        BloomFilter bloomFilter = new BloomFilter();
        System.out.println(bloomFilter.contains(str1));
        System.out.println(bloomFilter.contains(str2));
        bloomFilter.add(str1);
        bloomFilter.add(str2);
        System.out.println(bloomFilter.contains(str1));
        System.out.println(bloomFilter.contains(str2));
    }
    private static void test02() {
        Long value1 = 0404L;
        Long value2 = 0213L;
        BloomFilter bloomFilter = new BloomFilter();
        System.out.println(bloomFilter.contains(value1));
        System.out.println(bloomFilter.contains(value2));
        bloomFilter.add(value1);
        bloomFilter.add(value2);
        System.out.println(bloomFilter.contains(value1));
        System.out.println(bloomFilter.contains(value2));
    }
}
