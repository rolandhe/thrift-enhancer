package com.github.rolandhe.thrift.enhancer.translator.cases;

import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoCompactSerializer;
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
import com.github.rolandhe.thrift.enhancer.translator.trans.json.JsonCompactTranslator;
import com.github.rolandhe.thrift.enhancer.translator.trans.json.JsonHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TCompactProtocol.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rolandhe
 */
public class JsonCompactTestCase extends AbstractTestCase {



  @Test
  public void testJsonStandardRequest() throws TException {
    PojoStandardAd pojoStandardAd = createPojoAd(Integer.MAX_VALUE);
    String json = JsonHelper.toJson(pojoStandardAd);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");

    JsonCompactTranslator jsonTranslator = new JsonCompactTranslator();

    byte[] transBuffer = jsonTranslator
        .translateRequest(json, thriftJavaIdl, "entity.StandardAd", false);

    TSerializer tSerializer = new TSerializer(new Factory());
    StandardAd standardAd = createThriftAd(Integer.MAX_VALUE);
    byte[] thriftBuffer = tSerializer.serialize(standardAd);

    System.out.println(Arrays.toString(transBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(transBuffer, thriftBuffer));
  }

  @Test
  public void testJsonComplexRequest() throws TException {
    PojoCreative pojoCreative = createPojoCreative();
    String json = JsonHelper.toJson(pojoCreative);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");

    JsonCompactTranslator jsonTranslator = new JsonCompactTranslator();

    byte[] transBuffer = jsonTranslator
        .translateRequest(json, thriftJavaIdl, "entity.Creative", false);

    TSerializer tSerializer = new TSerializer(new Factory());
    Creative creative = createThriftCreative();
    byte[] thriftBuffer = tSerializer.serialize(creative);

    System.out.println(Arrays.toString(transBuffer));
    System.out.println(Arrays.toString(thriftBuffer));

    Assert.assertTrue(Arrays.equals(transBuffer, thriftBuffer));

    String newJson = jsonTranslator
        .translateResponseStruct(thriftBuffer, thriftJavaIdl, "entity.Creative", false);

    Assert.assertTrue(newJson.equals(json));
  }

  @Test
  public void testJsonMapResponse() {
    PojoWorkResult pojoWorkResult = new PojoWorkResult();
    Map<Integer, String> map = new HashMap<>();
    map.put(0, "0");
    map.put(1, "1");
    pojoWorkResult.setSuccess(map);

    byte[] tBuffer = PojoCompactSerializer.encode(pojoWorkResult);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");

    JsonCompactTranslator jsonTranslator = new JsonCompactTranslator();
    String json = jsonTranslator
        .translateResponse(tBuffer, thriftJavaIdl, "CreativeService.work", false);

    String rawJson = JsonHelper.toJson(pojoWorkResult.getSuccess());
    System.out.println(json);
    System.out.println(rawJson);
    Assert.assertTrue(json.equals(rawJson));

  }

  @Test
  public void testJsonBoolResponse() {
    PojoShowResult pojoShowResult = new PojoShowResult();

    pojoShowResult.setSuccess(true);

    byte[] tBuffer = PojoCompactSerializer.encode(pojoShowResult);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");

    JsonCompactTranslator jsonTranslator = new JsonCompactTranslator();
    String json = jsonTranslator
        .translateResponse(tBuffer, thriftJavaIdl, "CreativeService.show", false);

    String rawJson = JsonHelper.toJson(pojoShowResult.getSuccess());
    System.out.println(json);
    System.out.println(rawJson);
    Assert.assertTrue(json.equals(rawJson));

  }

  @Test
  public void testJsonEnumResponse() {
    PojoGetStyleResult pojoGetStyleResult = new PojoGetStyleResult();

    pojoGetStyleResult.setSuccess(PojoAdStyle.IMAGE);

    byte[] tBuffer = PojoCompactSerializer.encode(pojoGetStyleResult);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");

    JsonCompactTranslator jsonTranslator = new JsonCompactTranslator();
    String json = jsonTranslator
        .translateResponse(tBuffer, thriftJavaIdl, "CreativeService.getStyle", false);

    String rawJson = JsonHelper.toJson(pojoGetStyleResult.getSuccess());
    System.out.println(json);
    System.out.println(rawJson);
    Assert.assertTrue(json.equals(rawJson));

  }


}
