package com.github.rolandhe.thrift.enhancer.translator.pojo;


import com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations.ListContainer;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations.MapContainer;
import com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations.Optional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author rolandhe
 */
public class PojoCreativeAll {
  private long id;
  @ListContainer(PojoStandardAd.class)
  private List<PojoStandardAd> standardList;
  @ListContainer(Byte.class)
  private List<Byte> byteList;
  @ListContainer(Short.class)
  private List<Short> shortList;
  @ListContainer(Integer.class)
  private List<Integer> intList;
  @ListContainer(Long.class)
  private List<Long> longList;
  @MapContainer(keyClass = String.class,valueClass = String.class)
  private Map<String,String> mapString;
  @MapContainer(keyClass = String.class,valueClass = PojoStandardAd.class)
  private Map<String,PojoStandardAd> mapAd;
  @MapContainer(keyClass = Integer.class,valueClass = String.class)
  @Optional
  private Map<Integer,String> intPair;
  private PojoStandardAd spec;
  private byte type; // required
  private short category; // required
  private int shortid; // required
  private long seq; // required
  private String name; // required
  private byte[] content; // required
  private boolean isStart; // required
  private double percent; // required
  private PojoAdStyle adStyle; // required

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<PojoStandardAd> getStandardList() {
    return standardList;
  }

  public void setStandardList(List<PojoStandardAd> standardList) {
    this.standardList = standardList;
  }

  public List<Byte> getByteList() {
    return byteList;
  }

  public void setByteList(List<Byte> byteList) {
    this.byteList = byteList;
  }

  public List<Short> getShortList() {
    return shortList;
  }

  public void setShortList(List<Short> shortList) {
    this.shortList = shortList;
  }

  public List<Integer> getIntList() {
    return intList;
  }

  public void setIntList(List<Integer> intList) {
    this.intList = intList;
  }

  public List<Long> getLongList() {
    return longList;
  }

  public void setLongList(List<Long> longList) {
    this.longList = longList;
  }

  public Map<String, String> getMapString() {
    return mapString;
  }

  public void setMapString(Map<String, String> mapString) {
    this.mapString = mapString;
  }

  public Map<String, PojoStandardAd> getMapAd() {
    return mapAd;
  }

  public void setMapAd(
      Map<String, PojoStandardAd> mapAd) {
    this.mapAd = mapAd;
  }

  public Map<Integer, String> getIntPair() {
    return intPair;
  }

  public void setIntPair(Map<Integer, String> intPair) {
    this.intPair = intPair;
  }

  public PojoStandardAd getSpec() {
    return spec;
  }

  public void setSpec(PojoStandardAd spec) {
    this.spec = spec;
  }

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public short getCategory() {
    return category;
  }

  public void setCategory(short category) {
    this.category = category;
  }

  public int getShortid() {
    return shortid;
  }

  public void setShortid(int shortid) {
    this.shortid = shortid;
  }

  public long getSeq() {
    return seq;
  }

  public void setSeq(long seq) {
    this.seq = seq;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public boolean isStart() {
    return isStart;
  }

  public void setStart(boolean start) {
    isStart = start;
  }

  public double getPercent() {
    return percent;
  }

  public void setPercent(double percent) {
    this.percent = percent;
  }

  public PojoAdStyle getAdStyle() {
    return adStyle;
  }

  public void setAdStyle(PojoAdStyle adStyle) {
    this.adStyle = adStyle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PojoCreativeAll that = (PojoCreativeAll) o;
    return id == that.id &&
        type == that.type &&
        category == that.category &&
        shortid == that.shortid &&
        seq == that.seq &&
        isStart == that.isStart &&
        Double.compare(that.percent, percent) == 0 &&
        Objects.equals(standardList, that.standardList) &&
        Objects.equals(byteList, that.byteList) &&
        Objects.equals(shortList, that.shortList) &&
        Objects.equals(intList, that.intList) &&
        Objects.equals(longList, that.longList) &&
        Objects.equals(mapString, that.mapString) &&
        Objects.equals(mapAd, that.mapAd) &&
        Objects.equals(intPair, that.intPair) &&
        Objects.equals(spec, that.spec) &&
        Objects.equals(name, that.name) &&
        Arrays.equals(content, that.content) &&
        adStyle == that.adStyle;
  }

  @Override
  public int hashCode() {

    int result = Objects
        .hash(id, standardList, byteList, shortList, intList, longList, mapString, mapAd, intPair,
            spec, type, category, shortid, seq, name, isStart, percent, adStyle);
    result = 31 * result + Arrays.hashCode(content);
    return result;
  }
}
