package com.alibaba.dubbo.consumer;

import com.alibaba.dubbo.common.ExtensionLoader;

public class DubboSPITest {
    public static void main(String[] args) {
        ExtensionLoader<AdpIntf> extensionLoader =
                ExtensionLoader.getExtensionLoader(AdpIntf.class);
        AdpIntf bbb = extensionLoader.getAdaptiveExtension();
        bbb.someM();
    }
}
