package com.github.rolandhe.thrift.enhancer.stuff.cases;

import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoCompactSerializer;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoCreative;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoCreativeAll;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoStandardAd;
import com.github.rolandhe.thrift.enhancer.test.Creative;
import com.github.rolandhe.thrift.enhancer.test.CreativeAll;
import com.github.rolandhe.thrift.enhancer.test.StandardAd;
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
  public void testPojoThriftEncode() throws TException {
    PojoStandardAd pojoStandardAd = createPojoAd(Integer.MAX_VALUE);
    byte[] pojoBuffer = PojoCompactSerializer.encode(pojoStandardAd);

    TSerializer tSerializer = new TSerializer(new TCompactProtocol.Factory());
    StandardAd standardAd =  createThriftAd(Integer.MAX_VALUE);
    byte[] thriftBuffer =  tSerializer.serialize(standardAd);

    System.out.println(Arrays.toString(pojoBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(pojoBuffer,thriftBuffer));
  }

  @Test
  public void testPojoComplexThriftEncode() throws TException {
    PojoCreative pojoCreative = createPojoCreative();
    byte[] pojoBuffer = PojoCompactSerializer.encode(pojoCreative);

    TSerializer tSerializer = new TSerializer(new TCompactProtocol.Factory());
    Creative creative =  createThriftCreative();
    byte[] thriftBuffer =  tSerializer.serialize(creative);

    System.out.println(Arrays.toString(pojoBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(pojoBuffer,thriftBuffer));
  }

  @Test
  public void testPojoComplexAllThriftEncode() throws TException {
    PojoCreativeAll pojoCreative = createPojoCreativeAll();
    byte[] pojoBuffer = PojoCompactSerializer.encode(pojoCreative);

    TSerializer tSerializer = new TSerializer(new TCompactProtocol.Factory());
    CreativeAll creative =  createThriftCreativeAll();
    byte[] thriftBuffer =  tSerializer.serialize(creative);

    System.out.println(Arrays.toString(pojoBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(pojoBuffer,thriftBuffer));
  }

  @Test
  public void testPojoThriftDecode() throws TException {
    TSerializer tSerializer = new TSerializer(new TCompactProtocol.Factory());
    StandardAd standardAd =  createThriftAd(Integer.MAX_VALUE);
    byte[] thriftBuffer =  tSerializer.serialize(standardAd);

    PojoStandardAd decodePojo = PojoCompactSerializer.decode(PojoStandardAd.class,thriftBuffer);
    PojoStandardAd pojoStandardAd = createPojoAd(Integer.MAX_VALUE);

    Assert.assertTrue(pojoStandardAd.equals(decodePojo));
  }

  @Test
  public void testComplexPojoThriftDecode() throws TException {
    TSerializer tSerializer = new TSerializer(new TCompactProtocol.Factory());
    Creative creative =  createThriftCreative();
    byte[] thriftBuffer =  tSerializer.serialize(creative);

    PojoCreative decodePojo = PojoCompactSerializer.decode(PojoCreative.class,thriftBuffer);
    PojoCreative pojoCreative = createPojoCreative();

    Assert.assertTrue(pojoCreative.equals(decodePojo));
  }

  @Test
  public void testPojoComplexAllThriftDecode() throws TException {

    TSerializer tSerializer = new TSerializer(new TCompactProtocol.Factory());
    CreativeAll creative =  createThriftCreativeAll();
    byte[] thriftBuffer =  tSerializer.serialize(creative);


    PojoCreativeAll decodePojo =  PojoCompactSerializer.decode(PojoCreativeAll.class,thriftBuffer);

    PojoCreativeAll pojoCreative = createPojoCreativeAll();

    Assert.assertTrue(pojoCreative.equals(decodePojo));
  }
}
