package com.baxixiaomi.studynotes.test.chapter9.integration.thirdversion;

import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.FastSort;
import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.FasterSort;
import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.FastestSort;
import com.baxixiaomi.studynotes.test.chapter9.integration.secondversion.ISSort;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用工厂模式创建对象
 *
 * @author 吧嘻小米
 * @date 2020/04/30
 */
public class DealClassFastory {

    private static Map<DealClassEnum, ISSort> dealClass = new HashMap<>();

    static {
        dealClass.put(DealClassEnum.Fast, new FastSort());
        dealClass.put(DealClassEnum.Faster, new FasterSort());
        dealClass.put(DealClassEnum.Fastest, new FastestSort());
    }

    public static ISSort getDealClass(DealClassEnum dealClassEnum) {
        return dealClass.get(dealClassEnum);
    }

}
