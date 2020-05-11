package com.alibaba.dubbo.demo.impl;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

@Activate(group = "provider")
public class ProviderTrackFilter implements Filter {
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println(RpcContext.getContext().getAttachments().get("trackNumber"));
        System.out.println(invocation.getAttachments().get("add"));
        return invoker.invoke(invocation);
    }
}
