package com.github.rolandhe.thrift.enhancer.stuff.cases;

import java.util.Arrays;

import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoBinarySerializer;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoCreative;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoCreativeAll;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoStandardAd;
import com.github.rolandhe.thrift.enhancer.test.Creative;
import com.github.rolandhe.thrift.enhancer.test.CreativeAll;
import com.github.rolandhe.thrift.enhancer.test.StandardAd;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rolandhe
 */
public class TypeTestCase extends AbstractTestCase{

  @Test
  public void testPojoThriftEncode() throws TException {
    PojoStandardAd pojoStandardAd = createPojoAd(Integer.MAX_VALUE);
    byte[] pojoBuffer = PojoBinarySerializer.encode(pojoStandardAd);

    TSerializer tSerializer = new TSerializer();
    StandardAd standardAd =  createThriftAd(Integer.MAX_VALUE);
    byte[] thriftBuffer =  tSerializer.serialize(standardAd);

    System.out.println(Arrays.toString(pojoBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(pojoBuffer,thriftBuffer));
  }

  @Test
  public void testPojoComplexThriftEncode() throws TException {
    PojoCreative pojoCreative = createPojoCreative();
    byte[] pojoBuffer = PojoBinarySerializer.encode(pojoCreative);

    TSerializer tSerializer = new TSerializer();
    Creative creative =  createThriftCreative();
    byte[] thriftBuffer =  tSerializer.serialize(creative);

    System.out.println(Arrays.toString(pojoBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(pojoBuffer,thriftBuffer));
  }

  @Test
  public void testPojoComplexAllThriftEncode() throws TException {
    PojoCreativeAll pojoCreative = createPojoCreativeAll();
    byte[] pojoBuffer = PojoBinarySerializer.encode(pojoCreative);

    TSerializer tSerializer = new TSerializer();
    CreativeAll creative =  createThriftCreativeAll();
    byte[] thriftBuffer =  tSerializer.serialize(creative);

    System.out.println(Arrays.toString(pojoBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(pojoBuffer,thriftBuffer));
  }

  @Test
  public void testPojoThriftDecode() throws TException {
    TSerializer tSerializer = new TSerializer();
    StandardAd standardAd =  createThriftAd(Integer.MAX_VALUE);
    byte[] thriftBuffer =  tSerializer.serialize(standardAd);

    PojoStandardAd decodePojo = PojoBinarySerializer.decode(PojoStandardAd.class,thriftBuffer);
    PojoStandardAd pojoStandardAd = createPojoAd(Integer.MAX_VALUE);

    Assert.assertTrue(pojoStandardAd.equals(decodePojo));
  }

  @Test
  public void testComplexPojoThriftDecode() throws TException {
    TSerializer tSerializer = new TSerializer();
    Creative creative =  createThriftCreative();
    byte[] thriftBuffer =  tSerializer.serialize(creative);

    PojoCreative decodePojo = PojoBinarySerializer.decode(PojoCreative.class,thriftBuffer);
    PojoCreative pojoCreative = createPojoCreative();

    Assert.assertTrue(pojoCreative.equals(decodePojo));
  }

  @Test
  public void testPojoComplexAllThriftDecode() throws TException {

    TSerializer tSerializer = new TSerializer();
    CreativeAll creative =  createThriftCreativeAll();
    byte[] thriftBuffer =  tSerializer.serialize(creative);


    PojoCreativeAll decodePojo =  PojoBinarySerializer.decode(PojoCreativeAll.class,thriftBuffer);

    PojoCreativeAll pojoCreative = createPojoCreativeAll();

    Assert.assertTrue(pojoCreative.equals(decodePojo));
  }
}
