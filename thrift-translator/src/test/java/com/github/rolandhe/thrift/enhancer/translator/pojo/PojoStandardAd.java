package com.github.rolandhe.thrift.enhancer.translator.pojo;


import java.util.Arrays;
import java.util.Objects;

/**
 * @author rolandhe
 */
public class PojoStandardAd {
  private byte type; // required
  private short category; // required
  private int id; // required
  private long seq; // required
  private String name; // required
  private byte[] content; // required
  private boolean isStart; // required
  private double percent; // required
  private PojoAdStyle adStyle; // required

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public boolean getIsStart() {
    return isStart;
  }

  public void setStart(boolean start) {
    isStart = start;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PojoStandardAd that = (PojoStandardAd) o;
    return type == that.type &&
        category == that.category &&
        id == that.id &&
        seq == that.seq &&
        isStart == that.isStart &&
        Double.compare(that.percent, percent) == 0 &&
        Objects.equals(name, that.name) &&
        Arrays.equals(content, that.content) &&
        adStyle == that.adStyle;
  }

  @Override
  public int hashCode() {

    int result = Objects.hash(type, category, id, seq, name, isStart, percent, adStyle);
    result = 31 * result + Arrays.hashCode(content);
    return result;
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


}
