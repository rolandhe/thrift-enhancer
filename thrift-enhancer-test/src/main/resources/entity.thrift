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