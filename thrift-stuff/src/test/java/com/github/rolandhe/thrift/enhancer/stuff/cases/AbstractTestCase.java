package com.github.rolandhe.thrift.enhancer.stuff.cases;


import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoAdStyle;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoCreative;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoCreativeAll;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.PojoStandardAd;
import com.github.rolandhe.thrift.enhancer.test.AdStyle;
import com.github.rolandhe.thrift.enhancer.test.Creative;
import com.github.rolandhe.thrift.enhancer.test.CreativeAll;
import com.github.rolandhe.thrift.enhancer.test.StandardAd;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rolandhe
 */
public abstract class AbstractTestCase {
  private static final byte[] content;
  static {
    content = "hello world.".getBytes(StandardCharsets.UTF_8);
  }

  protected PojoStandardAd createPojoAd(int id) {
    PojoStandardAd pojoStandardAd = new PojoStandardAd();

    pojoStandardAd.setType((byte)101);
    pojoStandardAd.setCategory((short)32500);
    pojoStandardAd.setId(id);
    pojoStandardAd.setSeq(Long.MAX_VALUE - 1L);
    pojoStandardAd.setName("testPojoThrift");
    pojoStandardAd.setContent(content);
    pojoStandardAd.setStart(true);
    pojoStandardAd.setPercent(99.32D);
    pojoStandardAd.setAdStyle(PojoAdStyle.IMAGE);
    return pojoStandardAd;
  }

  protected StandardAd createThriftAd(int id) {
    StandardAd standardAd = new StandardAd();

    standardAd.setType((byte)101);
    standardAd.setCategory((short)32500);
    standardAd.setId(id);
    standardAd.setSeq(Long.MAX_VALUE - 1L);
    standardAd.setName("testPojoThrift");
    standardAd.setContent(content);
    standardAd.setIsStart(true);
    standardAd.setPercent(99.32D);
    standardAd.setAdStyle(AdStyle.IMAGE);
    return standardAd;
  }

  protected PojoCreative createPojoCreative(){
    PojoCreative pojoCreative = new PojoCreative();

    pojoCreative.setId(Long.MAX_VALUE - 1L);
    pojoCreative.setStandardList(Arrays.asList(createPojoAd(1),createPojoAd(2),createPojoAd(3)));

    pojoCreative.setByteList(Arrays.asList((byte)0,(byte)32,(byte)101,(byte)127));
    pojoCreative.setShortList(Arrays.asList((short)0,(short)32,(short)101,(short)32767));
    pojoCreative.setIntList(Arrays.asList(0,32,101,Integer.MAX_VALUE));
    pojoCreative.setLongList(Arrays.asList(0L,32L,101L,Long.MAX_VALUE));
    Map<String,String> stringMap = new HashMap<>();
    stringMap.put("a","1");
    stringMap.put("b","2");
    pojoCreative.setMapString(stringMap);

    Map<String,PojoStandardAd> pojoStandardAdMap = new HashMap<>();
    pojoStandardAdMap.put("x",createPojoAd(100));
    pojoStandardAdMap.put("y",createPojoAd(200));

    pojoCreative.setMapAd(pojoStandardAdMap);
    Map<Integer,String> integerStringMap = new HashMap<>();
//    integerStringMap.put(1,"1");
//    integerStringMap.put(2,"2");
    pojoCreative.setIntPair(integerStringMap);

    pojoCreative.setSpec(createPojoAd(1001));

    return pojoCreative;
  }

  protected PojoCreativeAll createPojoCreativeAll(){
    PojoCreativeAll pojoCreative = new PojoCreativeAll();

    pojoCreative.setId(Long.MAX_VALUE - 1L);
    pojoCreative.setStandardList(Arrays.asList(createPojoAd(1),createPojoAd(2),createPojoAd(3)));

    pojoCreative.setByteList(Arrays.asList((byte)0,(byte)32,(byte)101,(byte)127));
    pojoCreative.setShortList(Arrays.asList((short)0,(short)32,(short)101,(short)32767));
    pojoCreative.setIntList(Arrays.asList(0,32,101,Integer.MAX_VALUE));
    pojoCreative.setLongList(Arrays.asList(0L,32L,101L,Long.MAX_VALUE));
    Map<String,String> stringMap = new HashMap<>();
    stringMap.put("a","1");
    stringMap.put("b","2");
    pojoCreative.setMapString(stringMap);

    Map<String,PojoStandardAd> pojoStandardAdMap = new HashMap<>();
    pojoStandardAdMap.put("x",createPojoAd(100));
    pojoStandardAdMap.put("y",createPojoAd(200));

    pojoCreative.setMapAd(pojoStandardAdMap);
    Map<Integer,String> integerStringMap = new HashMap<>();
//    integerStringMap.put(1,"1");
//    integerStringMap.put(2,"2");
    pojoCreative.setIntPair(integerStringMap);

    pojoCreative.setSpec(createPojoAd(1001));

    pojoCreative.setType((byte)101);
    pojoCreative.setCategory((short)32500);
    pojoCreative.setShortid(345);
    pojoCreative.setSeq(Long.MAX_VALUE - 1L);
    pojoCreative.setName("testPojoThrift");
    pojoCreative.setContent(content);
    pojoCreative.setStart(true);
    pojoCreative.setPercent(99.32D);
    pojoCreative.setAdStyle(PojoAdStyle.IMAGE);

    return pojoCreative;
  }

  protected CreativeAll createThriftCreativeAll(){
    CreativeAll creative = new CreativeAll();

    creative.setId(Long.MAX_VALUE - 1L);
    creative.setStandardList(Arrays.asList(createThriftAd(1),createThriftAd(2),createThriftAd(3)));

    creative.setByteList(Arrays.asList((byte)0,(byte)32,(byte)101,(byte)127));
    creative.setShortList(Arrays.asList((short)0,(short)32,(short)101,(short)32767));
    creative.setIntList(Arrays.asList(0,32,101,Integer.MAX_VALUE));
    creative.setLongList(Arrays.asList(0L,32L,101L,Long.MAX_VALUE));
    Map<String,String> stringMap = new HashMap<>();
    stringMap.put("a","1");
    stringMap.put("b","2");
    creative.setMapString(stringMap);

    Map<String,StandardAd> pojoStandardAdMap = new HashMap<>();
    pojoStandardAdMap.put("x",createThriftAd(100));
    pojoStandardAdMap.put("y",createThriftAd(200));

    creative.setMapAd(pojoStandardAdMap);
    Map<Integer,String> integerStringMap = new HashMap<>();
//    integerStringMap.put(1,"1");
//    integerStringMap.put(2,"2");
    creative.setIntPair(integerStringMap);

    creative.setSpec(createThriftAd(1001));

    creative.setType((byte)101);
    creative.setCategory((short)32500);
    creative.setShortid(345);
    creative.setSeq(Long.MAX_VALUE - 1L);
    creative.setName("testPojoThrift");
    creative.setContent(content);
    creative.setIsStart(true);
    creative.setPercent(99.32D);
    creative.setAdStyle(AdStyle.IMAGE);

    return creative;
  }


  protected Creative createThriftCreative(){
    Creative creative = new Creative();

    creative.setId(Long.MAX_VALUE - 1L);
    creative.setStandardList(Arrays.asList(createThriftAd(1),createThriftAd(2),createThriftAd(3)));

    creative.setByteList(Arrays.asList((byte)0,(byte)32,(byte)101,(byte)127));
    creative.setShortList(Arrays.asList((short)0,(short)32,(short)101,(short)32767));
    creative.setIntList(Arrays.asList(0,32,101,Integer.MAX_VALUE));
    creative.setLongList(Arrays.asList(0L,32L,101L,Long.MAX_VALUE));
    Map<String,String> stringMap = new HashMap<>();
    stringMap.put("a","1");
    stringMap.put("b","2");
    creative.setMapString(stringMap);

    Map<String,StandardAd> pojoStandardAdMap = new HashMap<>();
    pojoStandardAdMap.put("x",createThriftAd(100));
    pojoStandardAdMap.put("y",createThriftAd(200));

    creative.setMapAd(pojoStandardAdMap);
    Map<Integer,String> integerStringMap = new HashMap<>();
//    integerStringMap.put(1,"1");
//    integerStringMap.put(2,"2");
    creative.setIntPair(integerStringMap);

    creative.setSpec(createThriftAd(1001));

    return creative;
  }
}
