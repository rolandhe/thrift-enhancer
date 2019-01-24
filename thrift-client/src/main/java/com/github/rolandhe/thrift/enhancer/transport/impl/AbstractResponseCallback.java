package com.github.rolandhe.thrift.enhancer.transport.impl;

import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;
import com.github.rolandhe.thrift.enhancer.translator.trans.ThriftDynamicUtil;
import com.github.rolandhe.thrift.enhancer.transport.ResponseCallback;

import java.io.InputStream;

/**
 * 抽象实现AbstractResponseCallback
 *
 * Created by rolandhe on 2019/1/24.
 */
public abstract class AbstractResponseCallback implements ResponseCallback<StructInstance> {
    protected final String methodName;
    protected final ThriftJavaIdl thriftJavaIdl;
    protected final FunctionCallContext functionCallContext;

    public AbstractResponseCallback(String methodName, FunctionCallContext functionCallContext, ThriftJavaIdl thriftJavaIdl) {
        this.methodName = methodName;
        this.functionCallContext = functionCallContext;
        this.thriftJavaIdl = thriftJavaIdl;
    }

    @Override
    public StructInstance callback(InputStream stream) {
        StructInstance structInstance = ThriftDynamicUtil.createStructInstance(thriftJavaIdl, methodName, true);
        decodeMessage(structInstance, stream);
        return structInstance;
    }


    protected abstract void decodeMessage(StructInstance structInstance, InputStream stream);
}
