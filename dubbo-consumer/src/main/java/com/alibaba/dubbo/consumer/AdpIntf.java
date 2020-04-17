package com.alibaba.dubbo.consumer;

import com.alibaba.dubbo.common.Adaptive;
import com.alibaba.dubbo.common.Extension;

@Extension("bbb")
public interface AdpIntf {

    @Adaptive
    public void someM();
}
