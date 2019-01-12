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
