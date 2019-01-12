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

