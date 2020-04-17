package com.alibaba.dubbo.consumer;

import com.alibaba.dubbo.common.Extension;

@Extension("ccc")
public class CCC implements AdpIntf {
    public void someM() {
        System.out.println("ccc");
    }
}
