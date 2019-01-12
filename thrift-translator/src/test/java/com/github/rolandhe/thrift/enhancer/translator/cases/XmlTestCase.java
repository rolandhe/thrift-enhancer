package com.github.rolandhe.thrift.enhancer.translator.cases;

import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoBinarySerializer;
import com.github.rolandhe.thrift.enhancer.test.Creative;
import com.github.rolandhe.thrift.enhancer.test.StandardAd;
import com.github.rolandhe.thrift.enhancer.translator.idl.java.ThriftJavaIdl;
import com.github.rolandhe.thrift.enhancer.translator.idl.parse.ResourceStreamIdlParser;
import com.github.rolandhe.thrift.enhancer.translator.pojo.PojoAdStyle;
import com.github.rolandhe.thrift.enhancer.translator.pojo.PojoCreative;
import com.github.rolandhe.thrift.enhancer.translator.pojo.PojoGetStyleResult;
import com.github.rolandhe.thrift.enhancer.translator.pojo.PojoShowResult;
import com.github.rolandhe.thrift.enhancer.translator.pojo.PojoStandardAd;
import com.github.rolandhe.thrift.enhancer.translator.pojo.PojoWorkResult;
import com.github.rolandhe.thrift.enhancer.translator.trans.xml.XmlHelper;
import com.github.rolandhe.thrift.enhancer.translator.trans.xml.XmlTranslator;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rolandhe
 */
public class XmlTestCase extends AbstractTestCase{

  @Test
  public void testJsonStandardRequest() throws TException {
    PojoStandardAd pojoStandardAd = createPojoAd(Integer.MAX_VALUE);
    String xml = XmlHelper.toXml(pojoStandardAd);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");


    XmlTranslator xmlTranslator = new XmlTranslator();

   byte[] transBuffer =  xmlTranslator.translateRequest(xml,thriftJavaIdl,"entity.StandardAd",false);

    TSerializer tSerializer = new TSerializer();
    StandardAd standardAd =  createThriftAd(Integer.MAX_VALUE);
    byte[] thriftBuffer =  tSerializer.serialize(standardAd);

    System.out.println(Arrays.toString(transBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(transBuffer,thriftBuffer));
  }

  @Test
  public void testJsonComplexRequest() throws TException {
    PojoCreative pojoCreative = createPojoCreative();
    String xml = XmlHelper.toXml(pojoCreative);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");


    XmlTranslator xmlTranslator = new XmlTranslator();

    byte[] transBuffer =  xmlTranslator.translateRequest(xml,thriftJavaIdl,"entity.Creative",false);

    TSerializer tSerializer = new TSerializer();
    Creative creative =  createThriftCreative();
    byte[] thriftBuffer =  tSerializer.serialize(creative);

    System.out.println(Arrays.toString(transBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(transBuffer,thriftBuffer));

    String newXml = xmlTranslator.translateResponseStruct(thriftBuffer,thriftJavaIdl,"entity.Creative",false);

    Assert.assertTrue(newXml.equals(xml));
  }

  @Test
  public void testJsonMapResponse(){
    PojoWorkResult pojoWorkResult = new PojoWorkResult();
    Map<Integer,String> map = new HashMap<>();
    map.put(0,"0");
    map.put(1,"1");
    pojoWorkResult.setSuccess(map);

    byte[] tBuffer = PojoBinarySerializer.encode(pojoWorkResult);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");


    XmlTranslator xmlTranslator = new XmlTranslator();
   String xml =  xmlTranslator.translateResponse(tBuffer,thriftJavaIdl,"CreativeService.work",false);

   String rawXml = XmlHelper.toXml(pojoWorkResult.getSuccess());
   System.out.println(xml);
   System.out.println(rawXml);
   Assert.assertTrue(xml.equals(rawXml.replace("HashMap","work_result")));

  }

  @Test
  public void testJsonBoolResponse(){
    PojoShowResult pojoShowResult = new PojoShowResult();

    pojoShowResult.setSuccess(true);

    byte[] tBuffer = PojoBinarySerializer.encode(pojoShowResult);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");


    XmlTranslator xmlTranslator = new XmlTranslator();
    String xml =  xmlTranslator.translateResponse(tBuffer,thriftJavaIdl,"CreativeService.show",false);

    String rawXml = XmlHelper.toXml(pojoShowResult.getSuccess());
    System.out.println(xml);
    System.out.println(rawXml);
    Assert.assertTrue(xml.equals(rawXml.replace("Boolean","show_result")));

  }

  @Test
  public void testJsonEnumResponse(){
    PojoGetStyleResult pojoGetStyleResult = new PojoGetStyleResult();

    pojoGetStyleResult.setSuccess(PojoAdStyle.IMAGE);

    byte[] tBuffer = PojoBinarySerializer.encode(pojoGetStyleResult);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");


    XmlTranslator xmlTranslator = new XmlTranslator();
    String xml =  xmlTranslator.translateResponse(tBuffer,thriftJavaIdl,"CreativeService.getStyle",false);

    String rawXml = XmlHelper.toXml(pojoGetStyleResult.getSuccess());
    System.out.println(xml);
    System.out.println(rawXml);
    Assert.assertTrue(xml.equals(rawXml.replace("PojoAdStyle","getStyle_result")));

  }


}
