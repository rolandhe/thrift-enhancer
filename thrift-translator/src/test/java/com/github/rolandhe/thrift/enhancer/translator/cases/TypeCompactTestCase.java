package com.github.rolandhe.thrift.enhancer.translator.cases;

import com.github.rolandhe.thrift.enhancer.test.Creative;
import com.github.rolandhe.thrift.enhancer.test.StandardAd;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.StructDescription;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.parse.ResourceStreamIdlParser;
import com.github.rolandhe.thrift.enhancer.translator.runtime.RuntimeCompactSerializer;
import com.github.rolandhe.thrift.enhancer.translator.runtime.instance.StructInstance;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TCompactProtocol;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author rolandhe
 */
public class TypeCompactTestCase extends AbstractTestCase{


  @Test
  public void testThriftIdlAd() throws TException {
    TSerializer tSerializer = new TSerializer(new TCompactProtocol.Factory());
    StandardAd standardAd =  createThriftAd(Integer.MAX_VALUE);
    byte[] thriftBuffer =  tSerializer.serialize(standardAd);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");

    StructDescription structDescription = thriftJavaIdl.getStructByName("entity.StandardAd");

    StructInstance structInstance =  StructInstance.instance(structDescription,thriftJavaIdl);

    RuntimeCompactSerializer.decode(structInstance,thriftBuffer);

    byte[] idlEncodeBuffer = RuntimeCompactSerializer.encode(structInstance);

    System.out.println(Arrays.toString(idlEncodeBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(idlEncodeBuffer,thriftBuffer));

  }

  @Test
  public void testThriftIdlCreative() throws TException {
    TSerializer tSerializer = new TSerializer(new TCompactProtocol.Factory());
    Creative creative =  createThriftCreative();
    byte[] thriftBuffer =  tSerializer.serialize(creative);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");

    StructDescription structDescription = thriftJavaIdl.getStructByName("entity.Creative");

    StructInstance structInstance =  StructInstance.instance(structDescription,thriftJavaIdl);

    RuntimeCompactSerializer.decode(structInstance,thriftBuffer);

    byte[] idlEncodeBuffer = RuntimeCompactSerializer.encode(structInstance);

    System.out.println(Arrays.toString(idlEncodeBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(idlEncodeBuffer,thriftBuffer));

  }

}
