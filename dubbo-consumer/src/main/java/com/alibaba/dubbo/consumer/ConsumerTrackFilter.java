package com.alibaba.dubbo.consumer;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

@Activate(group = "consumer")
public class ConsumerTrackFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext rpcContext = RpcContext.getContext();
        rpcContext.getAttachments().put("trackNumber","123");

        invocation.getAttachments().put("add","456");
        return invoker.invoke(invocation);
    }
}
