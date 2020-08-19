package com.baxixiaomi.studynotes.test.chapter6.interfaceandimpl;

/**
 * 对外提供一个动物的计算技能
 *
 * @author 吧嘻小米
 * @since 1.0-SNAPSHOT
 */
public interface Animal {
    /**
     * 获取两数之和
     * 使用者只能获取到最终的结果，无法查看或修改过程
     *
     * @param a 参数a
     * @param b 参数b
     * @return 返回结果
     */
    Integer getSum(int a, int b);

}
