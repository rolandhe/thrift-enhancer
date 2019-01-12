package com.github.rolandhe.thrift.enhancer.transport.impl;

import com.github.rolandhe.thrift.enhancer.stuff.general.FunctionCallContext;
import com.github.rolandhe.thrift.enhancer.transport.ResponseCallback;
import com.github.rolandhe.thrift.enhancer.transport.ThriftClient;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 对标TSocket
 *
 * @author rolandhe
 */
public class SimpleThriftClient implements ThriftClient {
    private int seq = 0;

    public SimpleThriftClient() {

    }

    @Override
    public FunctionCallContext provide(String methodName) {
        return FunctionCallContext.of(methodName, ++seq);
    }

    @Override
    public <T> T call(byte[] buffer, String host, int connectTimeout, int soTimeout, ResponseCallback<T> responseCallback) {

        return call(buffer, 0, buffer.length, host, connectTimeout, soTimeout, responseCallback);
    }

    @Override
    public <T> T call(byte[] buffer, int offset, int len, String host, int connectTimeout,
                      int soTimeout, ResponseCallback<T> responseCallback) {
        String[] addrs = StringUtils.split(host, ":");
        try (Socket socket = new Socket()) {
            socket.setSoTimeout(soTimeout);
            socket.connect(new InetSocketAddress(addrs[0], Integer.parseInt(addrs[1])), connectTimeout);
            socket.getOutputStream().write(buffer, offset, len);
            InputStream inputStream = socket.getInputStream();
            return responseCallback.callback(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
