package com.baxixiaomi.code.demo.mockito.stubbing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * stubbing练习
 *
 * @author wjw
 * @date 2020/09/02
 */
@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    private List<String> list;

    @Before
    public void init() {
        this.list = mock(ArrayList.class);
    }

    @After
    public void destroy() {
        reset(this.list);
    }

    @Test
    public void testStubbing() {
        when(list.get(0)).thenReturn("jamie");
        assertEquals(list.get(0), "jamie");
    }

    @Test
    public void testThrowExceptionStubbing() {
        when(list.get(0)).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void testDoNothingStubbing() {
        doNothing().when(this.list).clear();
        list.clear();
        // 验证clear方法是否被执行
        verify(list, times(0)).clear();
    }
}
