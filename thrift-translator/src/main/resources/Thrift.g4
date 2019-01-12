grammar Thrift;

document: header* definition*;


header:
        include
        | cppinclude
        | namespace
        ;

include: 'include' LITERAL ;
cppinclude: 'cpp_include' LITERAL ;
namespace: 'namespace' namespacescope ID
           | 'smalltalk.category' SID
           | 'smalltalk.prefix' ID
           | 'php_namespace' LITERAL
           | 'xsd_namespace' LITERAL
           ;

namespacescope: '*'
                | 'cpp'
                | 'java'
                | 'py'
                | 'perl'
                | 'rb'
                | 'cocoa'
                | 'csharp'
                ;



definition: constdef
            | typedef
            | tenum
            | senum
            | struct
            | union
            | exception
            | service
            ;

constdef: 'const' fieldtype ID '=' constvalue listseparator? ;

typedef: 'typedef' definitiontype ID ;

tenum: 'enum' ID '{' tenumfield* '}' ;

tenumfield: ID ('=' intconst)? listseparator? ;

senum: 'senum' ID '{' senumfield* '}' ;
senumfield: LITERAL listseparator? ;

struct: 'struct' ID 'xsd_all'? '{' field* '}' ;

union: 'union' ID 'xsd_all'? '{' field* '}' ;

exception: 'exception' ID '{' field* '}' ;

service: 'service' ID serviceparent '{' function* '}' ;
serviceparent: ('extends' ID)? ;

field: fieldid fieldreq? fieldtype ID ('=' constvalue)? xsdfieldoptions listseparator? ;
fieldid: intconst ':' ;


fieldreq: 'required'
          | 'optional'
          ;

xsdfieldoptions: 'xsd_optional'? 'xsd_nillable'? xsdattrs? ;
xsdattrs: 'xsd_attrs' '{' field* '}' ;

function: 'oneway'? functiontype ID '(' field* ')' throwstat? listseparator? ;
functiontype: fieldtype | 'void' ;
throwstat: 'throws' '(' field* ')' ;


fieldtype:
           basetype
           | containertype
           | ID
           ;

definitiontype: basetype
                | containertype
                ;

basetype: 'bool'
          | 'byte'
          | 'i8'
          | 'i16'
          | 'i32'
          | 'i64'
          | 'double'
          | 'string'
          | 'binary'
          | 'slist'
          ;

containertype: maptype
              | settype
              | listtype
              ;

maptype: 'map' cpptype? '<' fieldtype ',' fieldtype '>' ;
settype: 'set' cpptype? '<' fieldtype '>' ;
listtype: 'list' '<' fieldtype '>' cpptype? ;
cpptype: 'cpp_type' LITERAL ;


constvalue: oneconst
            | constlist
            ;

intconst: ('+'|'-')? DIGIT+ ;
doubleconst: ('+'|'-')? DIGIT* ('.' DIGIT+)? (ES intconst)? ;
constlist:
           '[' oneconst (listseparator oneconst)* ']'
           |'[' constlist  (listseparator constlist)* ']'
           ;
constmap: '{' (constvalue ':' constvalue listseparator?)* '}' ;

oneconst: intconst
          |doubleconst
          |LITERAL
          |ID
          |constmap
		      ;

ID: (LETTER|'_') (LETTER|DIGIT|'.'|'_')* ;
SID: (LETTER|'_') (LETTER |LETTER|'.'|'_'|'-')* ;
listseparator: ',' | ';' ;


DIGIT: [0-9] ;
LETTER: [A-Za-z] ;
ES: [eE] ;

LITERAL: '"' ~["']* '"' ;

WS: [ \t\r\n]+->skip ;

LINE_COMMENT: ('//'|'#') .*? '\n' -> skip ;
COMMENT: '/*' .*? '*/' -> skip ;



