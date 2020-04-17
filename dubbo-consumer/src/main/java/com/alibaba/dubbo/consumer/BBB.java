package com.alibaba.dubbo.consumer;

import com.alibaba.dubbo.common.Extension;

@Extension("bbb")
public class BBB implements AdpIntf {
    public void someM() {
        System.out.println("bbb");
    }
}
