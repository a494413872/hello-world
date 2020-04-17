package com.alibaba.dubbo.consumer;

import java.util.ServiceLoader;

public class JavaSPITest {
    public static void main(String[] args) {
        ServiceLoader<AdpIntf> serviceLoader = ServiceLoader.load(AdpIntf.class);
        System.out.println("Java SPI");
//        serviceLoader.forEach(AdpIntf::someM);
        for (AdpIntf adpIntf : serviceLoader) {
            adpIntf.someM();

        }
    }
}
