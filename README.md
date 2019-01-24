thrift-enhancer是一组支持thrift协议的加强包，设计的初衷是为了简化thrift的调用。传统的thrift会使用thrift的编译器
编译出一组代码，然后基于这组代码进行rpc编程，编译出的代码往往和组织的代码风格不同，阅读起来很不方便。更有甚者，有时候
业务中可能已经存在一组类足以描述rpc的请求或者response，但为了使用thrift又编译一组类，同时需要一组转换的工具类，使得
代码膨胀厉;传统的thrift没有实现协议转换，比如 json与thrift的协议转换，这在网关应用中很重要；我们要替换传统的thrift
调用，那么transport的功能也应该是必不可少的了。thrift-enhancer提供了如下能力:
* thrift-stuff: pojo转出成thrift二进制协议的能力，这一点与[protostuff](https://github.com/protostuff/protostuff)
类似, 目前实现了thrift的二进制和压缩协议
* thrift-translator: 提供动态解析idl并生成参数对象的能力，动态生成的参数对象可以自动转换为thrift协议数据，同时提供
thrift与json、xml的双向转换, 动态解析idl功能基于[antlr4](https://github.com/antlr/antlr4))实现
* thrift-client: 提供thrift调用的能力，目前仅支持 TSocket的能力

# 1. 使用thrift-stuff 转换pojo

## 1.1 导入

### maven

```
<dependency>
    <groupId>com.github.rolandhe</groupId>
    <artifactId>thrift-stuff</artifactId>
    <version>1.0.0</version>
</dependency>

```

### gradle

```
compile group: 'com.github.rolandhe', name: 'thrift-stuff', version: '1.0.0'

```

## 1.2 examples

### 1.2.1 创建pojo

```
public enum  PojoAdStyle {
  WORD,
  IMAGE;
}

```

```
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

```

### 1.2.2 序列化/反序列化

```
public static PojoStandardAd createPojoAd(int id) {
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
  
  public static void main(String[] args) {
    PojoStandardAd pojoStandardAd = createPojoAd(Integer.MAX_VALUE);
    byte[] pojoBuffer = PojoBinarySerializer.encode(pojoStandardAd);

    // 反序列化
    PojoStandardAd decodePojo = PojoBinarySerializer.decode(PojoStandardAd.class,pojoBuffer);

    System.out.println(decodePojo.equals(pojoStandardAd));
  }
  

```

## 1.3 泛型支持注解

thrift idl支持map, list, set， 但java的泛型是语法糖，在编译后会丢失泛型信息，因此需要注解来描述泛型类型。

### 1.3.1 ListContainer

描述list field<br>
* value()用于指定list element对应的类
* realListClass() 用于反序列化时构建java List对象的实现类，可以指定ArrayList，LinkedList等

### 1.3.2 SetContainer

类似 ListContainer， 用于指定set 类型

### 1.3.3 MapContainer

描述 map field <br>
* keyClass(), 指定map key的类型
* valueClass() 指定map value类型
* realMapClass(), 反序列化时用于指定java Map的实现类，比如HashMap,TreeMap等

### 1.3.4 Optional

对应thrift optional，描述指定的field是可选的

## 1.4 支持泛型的例子

```
public class PojoCreative {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PojoCreative that = (PojoCreative) o;
    return id == that.id &&
        Objects.equals(standardList, that.standardList) &&
        Objects.equals(byteList, that.byteList) &&
        Objects.equals(shortList, that.shortList) &&
        Objects.equals(intList, that.intList) &&
        Objects.equals(longList, that.longList) &&
        Objects.equals(mapString, that.mapString) &&
        Objects.equals(mapAd, that.mapAd) &&
        Objects.equals(intPair, that.intPair) &&
        Objects.equals(spec, that.spec);
  }

  @Override
  public int hashCode() {

    return Objects
        .hash(id, standardList, byteList, shortList, intList, longList, mapString, mapAd, intPair,
            spec);
  }
}

```

```
static PojoCreative createPojoCreative(){
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
    pojoCreative.setIntPair(integerStringMap);

    pojoCreative.setSpec(createPojoAd(1001));

    return pojoCreative;
  }
  
  public static void main(String[] args) {
       PojoCreative pojoCreative = createPojoCreative();
       byte[] pojoBuffer = PojoBinarySerializer.encode(pojoCreative);
  
      // 反序列化
      PojoCreative decodePojo = PojoBinarySerializer.decode(PojoCreative.class,pojoBuffer);
  
      System.out.println(decodePojo.equals(pojoCreative));
    }
  
```

## 1.5 rpc调用序列化

上面的例子都是实现的TSerializer的功能，rpc调用时 thrift的 struct会被封装成一个message的结构，
而且需要提供一次调用的id，该id会被rpc调用完成后被原封不动的返回，可以用调用时的id和返回id进行核对
信息的正确性。FunctionCallContext描述rpc方法的一次调用。

### 1.5.1 encodeMessage方法
打包rpc 调用 message

### 1.5.6 decodeMessage

解析rpc返回message

## 1.7 PojoCompactSerializer

实现了 thrift compact协议

# 2 使用 thrift-translator转换 json与thrift

日常业务中大家会在内部服务中使用thrift，而与外部的交流中会使用http/https，这时就需要一个称之为"gateway"的系统
把内部服务thrift方法暴露成http/https, 在gateway中如果针对每个thrift接口都开发协议转换或者调用thriftsdk的话，
那开发的工作量就比较巨大，而且容易引起jar包冲突，此时的理想状态就是使用一个json/xml到thrift的协议转换工具。而
thrift-translator就是这样的工具，而该转换工具仅仅使用thrift idl即可，不使用其他工具。<br>

maven导入：<br>

```
<!-- https://mvnrepository.com/artifact/com.github.rolandhe/thrift-translator -->
<dependency>
    <groupId>com.github.rolandhe</groupId>
    <artifactId>thrift-translator</artifactId>
    <version>1.0.0</version>
</dependency>

```

gradle 导入:<br>

```
// https://mvnrepository.com/artifact/com.github.rolandhe/thrift-translator
compile group: 'com.github.rolandhe', name: 'thrift-translator', version: '1.0.0'

```

## 2.1 设计原理
thrift是使用idl来描述接口文档的，thrift idl主要包含 enum、struct、service 3中主要对象(由于java没有union，所以我们也暂时不支持union)，
我们可以把thrift的idl文件解析出来，使用java 对象把enum、struct、service描述出来，这样就在一个抽象的级别
构建出thrift对象，比如struct就是一个 field（name/type/optional）的列表，而struct的实例就是一个field/value的列表。<br>
antlr4是一个构建编译器的工具，它使用类似bnf的语言来描述语言的词法和语法，然后就能构建出这门语言的编译器，按照thrift的语法可以
定义对应的thrift的bnf(resources/Thrift.g4)。使用thrift的语法编译器就可以解析thrift的idl，由此能够动态实例化struct或者
thrift service方法的参数。<br>

使用jackson的sax功能可以遍历json string，然后按照解析出的thrift struct就能够完成json field与thrift struct field的映射，
从而完成json与thrift的互转，动态thrift struct实例使用thrift-stuff序列化成byte数组。<br>

xml的转换与json类似，使用jackson xml组件完成。

## 2.2 使用
### 2.2.1 thrift idl

entity.thrift<br>

``` 
namespace java com.github.rolandhe.thrift.enhancer.test

enum AdStyle
{
   WORD,
   IMAGE
}

struct StandardAd
{
  1: i8 type,
  2: i16 category,
  3: i32 id,
  4: i64 seq,
  5: string name,
  6: binary content,
  7: bool isStart,
  8: double percent,
  9: AdStyle adStyle
}

struct Creative {
 1: i64 id,
 2: list<StandardAd> standardList,
 3: list<i8> byteList,
 4: list<i16> shortList,
 5: list<i32> intList,
 6: list<i64> longList,
 7: map<string,string> mapString,
 8: map<string,StandardAd> mapAd,
 9: optional map<i32, string> intPair,
 10: StandardAd spec
}

struct CreativeAll {
 1: i64 id,
 2: list<StandardAd> standardList,
 3: list<i8> byteList,
 4: list<i16> shortList,
 5: list<i32> intList,
 6: list<i64> longList,
 7: map<string,string> mapString,
 8: map<string,StandardAd> mapAd,
 9: optional map<i32, string> intPair,
 10: StandardAd spec,
 11: i8 type,
 12: i16 category,
 13: i32 shortid,
 14: i64 seq,
 15: string name,
 16: binary content,
 17: bool isStart,
 18: double percent,
 19: AdStyle adStyle
}

```

complex.thrift<br>

```
include "entity.thrift"
namespace java com.github.rolandhe.thrift.enhancer.test




service CreativeService
{
    void create(1: entity.Creative creative),
    map<string,string> convert(1: entity.Creative creative),
    list<i32> getList(),
    entity.StandardAd build(),
    map<i32,string> work(),
    bool show()
    entity.AdStyle getStyle()
}

```

### 2.2.2 json translate example

```
 public static void main(String[] args) {
    PojoStandardAd pojoStandardAd = createPojoAd(Integer.MAX_VALUE);
    // convert to json
    String json = JsonHelper.toJson(pojoStandardAd);

    ResourceStreamIdlParser resourceStreamIncludeParser = new ResourceStreamIdlParser();
    // load thrift idl file from resources
    ThriftJavaIdl thriftJavaIdl = resourceStreamIncludeParser.parse("complex.thrift");

    JsonTranslator jsonTranslator = new JsonTranslator();

    byte[] transBuffer = jsonTranslator
        .translateRequest(json, thriftJavaIdl, "entity.StandardAd", false);
  }

```
更多示例参见 unit test case。

### 2.2.3 idl解析

IdlParser 接口定义了idl文件的解析功能，AbstractIdlParser 实现了具体的解析实现，你只需要提供一个
idl文件的流即可，即你需要实现openInputStream方法，基于AbstractIdlParser你可以解析存储在任意地方
的idl 文件，比如: 你可以从web下载一个idl文件并解析。我们已经内置了:<br>
* ResourceStreamIdlParser, 支持从resources加载idl文件
* FileIdlParser， 从绝对路径读取idl文件解析


### 2.2.4 协议转换

* JsonTranslator， 实现json与thrift的转换
* XmlTranslator， 实现xml与thrift的转换


# License
[MIT License](https://opensource.org/licenses/MIT)





