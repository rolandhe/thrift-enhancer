package com.github.rolandhe.thrift.enhancer.transport.impl;

import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.runtime.RuntimeBinarySerializer;
import com.github.rolandhe.thrift.enhancer.translator.runtime.RuntimeCompactSerializer;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;
import com.github.rolandhe.thrift.enhancer.translator.trans.ThriftDynamicUtil;
import com.github.rolandhe.thrift.enhancer.transport.ResponseCallback;

import java.io.InputStream;

/**
 * compact解析
 *
 * Created by rolandhe on 2019/1/24.
 */
public class CompactResponseCallback extends AbstractResponseCallback implements ResponseCallback<StructInstance> {

    public CompactResponseCallback(String methodName, FunctionCallContext functionCallContext, ThriftJavaIdl thriftJavaIdl) {
        super(methodName, functionCallContext, thriftJavaIdl);
    }

    @Override
    public StructInstance callback(InputStream stream) {
        StructInstance structInstance =  ThriftDynamicUtil.createStructInstance(thriftJavaIdl,methodName,true);
        decodeMessage(structInstance,stream);
        return structInstance;
    }


    @Override
    protected  void decodeMessage(StructInstance structInstance,InputStream stream){
        RuntimeCompactSerializer.decodeMessage(structInstance,functionCallContext,stream);
    }
}
