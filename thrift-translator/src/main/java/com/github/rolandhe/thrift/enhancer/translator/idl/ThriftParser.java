// Generated from Thrift.g4 by ANTLR 4.7.1
package com.github.rolandhe.thrift.enhancer.translator.idl;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ThriftParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, ID=63, SID=64, DIGIT=65, LETTER=66, ES=67, 
		LITERAL=68, WS=69, LINE_COMMENT=70, COMMENT=71;
	public static final int
		RULE_document = 0, RULE_header = 1, RULE_include = 2, RULE_cppinclude = 3, 
		RULE_namespace = 4, RULE_namespacescope = 5, RULE_definition = 6, RULE_constdef = 7, 
		RULE_typedef = 8, RULE_tenum = 9, RULE_tenumfield = 10, RULE_senum = 11, 
		RULE_senumfield = 12, RULE_struct = 13, RULE_union = 14, RULE_exception = 15, 
		RULE_service = 16, RULE_serviceparent = 17, RULE_field = 18, RULE_fieldid = 19, 
		RULE_fieldreq = 20, RULE_xsdfieldoptions = 21, RULE_xsdattrs = 22, RULE_function = 23, 
		RULE_functiontype = 24, RULE_throwstat = 25, RULE_fieldtype = 26, RULE_definitiontype = 27, 
		RULE_basetype = 28, RULE_containertype = 29, RULE_maptype = 30, RULE_settype = 31, 
		RULE_listtype = 32, RULE_cpptype = 33, RULE_constvalue = 34, RULE_intconst = 35, 
		RULE_doubleconst = 36, RULE_constlist = 37, RULE_constmap = 38, RULE_oneconst = 39, 
		RULE_listseparator = 40;
	public static final String[] ruleNames = {
		"document", "header", "include", "cppinclude", "namespace", "namespacescope", 
		"definition", "constdef", "typedef", "tenum", "tenumfield", "senum", "senumfield", 
		"struct", "union", "exception", "service", "serviceparent", "field", "fieldid", 
		"fieldreq", "xsdfieldoptions", "xsdattrs", "function", "functiontype", 
		"throwstat", "fieldtype", "definitiontype", "basetype", "containertype", 
		"maptype", "settype", "listtype", "cpptype", "constvalue", "intconst", 
		"doubleconst", "constlist", "constmap", "oneconst", "listseparator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'include'", "'cpp_include'", "'namespace'", "'smalltalk.category'", 
		"'smalltalk.prefix'", "'php_namespace'", "'xsd_namespace'", "'*'", "'cpp'", 
		"'java'", "'py'", "'perl'", "'rb'", "'cocoa'", "'csharp'", "'const'", 
		"'='", "'typedef'", "'enum'", "'{'", "'}'", "'senum'", "'struct'", "'xsd_all'", 
		"'union'", "'exception'", "'service'", "'extends'", "':'", "'required'", 
		"'optional'", "'xsd_optional'", "'xsd_nillable'", "'xsd_attrs'", "'oneway'", 
		"'('", "')'", "'void'", "'throws'", "'bool'", "'byte'", "'i8'", "'i16'", 
		"'i32'", "'i64'", "'double'", "'string'", "'binary'", "'slist'", "'map'", 
		"'<'", "','", "'>'", "'set'", "'list'", "'cpp_type'", "'+'", "'-'", "'.'", 
		"'['", "']'", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "ID", "SID", "DIGIT", "LETTER", "ES", "LITERAL", "WS", 
		"LINE_COMMENT", "COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Thrift.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ThriftParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DocumentContext extends ParserRuleContext {
		public List<HeaderContext> header() {
			return getRuleContexts(HeaderContext.class);
		}
		public HeaderContext header(int i) {
			return getRuleContext(HeaderContext.class,i);
		}
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitDocument(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) {
				{
				{
				setState(82);
				header();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__17) | (1L << T__18) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) {
				{
				{
				setState(88);
				definition();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderContext extends ParserRuleContext {
		public IncludeContext include() {
			return getRuleContext(IncludeContext.class,0);
		}
		public CppincludeContext cppinclude() {
			return getRuleContext(CppincludeContext.class,0);
		}
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				include();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				cppinclude();
				}
				break;
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				namespace();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IncludeContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(ThriftParser.LITERAL, 0); }
		public IncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_include; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterInclude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitInclude(this);
		}
	}

	public final IncludeContext include() throws RecognitionException {
		IncludeContext _localctx = new IncludeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_include);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__0);
			setState(100);
			match(LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CppincludeContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(ThriftParser.LITERAL, 0); }
		public CppincludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cppinclude; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterCppinclude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitCppinclude(this);
		}
	}

	public final CppincludeContext cppinclude() throws RecognitionException {
		CppincludeContext _localctx = new CppincludeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cppinclude);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__1);
			setState(103);
			match(LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamespaceContext extends ParserRuleContext {
		public NamespacescopeContext namespacescope() {
			return getRuleContext(NamespacescopeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public TerminalNode SID() { return getToken(ThriftParser.SID, 0); }
		public TerminalNode LITERAL() { return getToken(ThriftParser.LITERAL, 0); }
		public NamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterNamespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitNamespace(this);
		}
	}

	public final NamespaceContext namespace() throws RecognitionException {
		NamespaceContext _localctx = new NamespaceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_namespace);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				match(T__2);
				setState(106);
				namespacescope();
				setState(107);
				match(ID);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(T__3);
				setState(110);
				match(SID);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				match(T__4);
				setState(112);
				match(ID);
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				match(T__5);
				setState(114);
				match(LITERAL);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				match(T__6);
				setState(116);
				match(LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamespacescopeContext extends ParserRuleContext {
		public NamespacescopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespacescope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterNamespacescope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitNamespacescope(this);
		}
	}

	public final NamespacescopeContext namespacescope() throws RecognitionException {
		NamespacescopeContext _localctx = new NamespacescopeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_namespacescope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionContext extends ParserRuleContext {
		public ConstdefContext constdef() {
			return getRuleContext(ConstdefContext.class,0);
		}
		public TypedefContext typedef() {
			return getRuleContext(TypedefContext.class,0);
		}
		public TenumContext tenum() {
			return getRuleContext(TenumContext.class,0);
		}
		public SenumContext senum() {
			return getRuleContext(SenumContext.class,0);
		}
		public StructContext struct() {
			return getRuleContext(StructContext.class,0);
		}
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public ExceptionContext exception() {
			return getRuleContext(ExceptionContext.class,0);
		}
		public ServiceContext service() {
			return getRuleContext(ServiceContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitDefinition(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_definition);
		try {
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				constdef();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				typedef();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				tenum();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				senum();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 5);
				{
				setState(125);
				struct();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 6);
				{
				setState(126);
				union();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 7);
				{
				setState(127);
				exception();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 8);
				{
				setState(128);
				service();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstdefContext extends ParserRuleContext {
		public FieldtypeContext fieldtype() {
			return getRuleContext(FieldtypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public ConstvalueContext constvalue() {
			return getRuleContext(ConstvalueContext.class,0);
		}
		public ListseparatorContext listseparator() {
			return getRuleContext(ListseparatorContext.class,0);
		}
		public ConstdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterConstdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitConstdef(this);
		}
	}

	public final ConstdefContext constdef() throws RecognitionException {
		ConstdefContext _localctx = new ConstdefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__15);
			setState(132);
			fieldtype();
			setState(133);
			match(ID);
			setState(134);
			match(T__16);
			setState(135);
			constvalue();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__51 || _la==T__61) {
				{
				setState(136);
				listseparator();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypedefContext extends ParserRuleContext {
		public DefinitiontypeContext definitiontype() {
			return getRuleContext(DefinitiontypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public TypedefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterTypedef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitTypedef(this);
		}
	}

	public final TypedefContext typedef() throws RecognitionException {
		TypedefContext _localctx = new TypedefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typedef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(T__17);
			setState(140);
			definitiontype();
			setState(141);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TenumContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public List<TenumfieldContext> tenumfield() {
			return getRuleContexts(TenumfieldContext.class);
		}
		public TenumfieldContext tenumfield(int i) {
			return getRuleContext(TenumfieldContext.class,i);
		}
		public TenumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tenum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterTenum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitTenum(this);
		}
	}

	public final TenumContext tenum() throws RecognitionException {
		TenumContext _localctx = new TenumContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tenum);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__18);
			setState(144);
			match(ID);
			setState(145);
			match(T__19);
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(146);
				tenumfield();
				}
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(152);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TenumfieldContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public IntconstContext intconst() {
			return getRuleContext(IntconstContext.class,0);
		}
		public ListseparatorContext listseparator() {
			return getRuleContext(ListseparatorContext.class,0);
		}
		public TenumfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tenumfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterTenumfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitTenumfield(this);
		}
	}

	public final TenumfieldContext tenumfield() throws RecognitionException {
		TenumfieldContext _localctx = new TenumfieldContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tenumfield);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(ID);
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(155);
				match(T__16);
				setState(156);
				intconst();
				}
			}

			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__51 || _la==T__61) {
				{
				setState(159);
				listseparator();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SenumContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public List<SenumfieldContext> senumfield() {
			return getRuleContexts(SenumfieldContext.class);
		}
		public SenumfieldContext senumfield(int i) {
			return getRuleContext(SenumfieldContext.class,i);
		}
		public SenumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_senum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterSenum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitSenum(this);
		}
	}

	public final SenumContext senum() throws RecognitionException {
		SenumContext _localctx = new SenumContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_senum);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__21);
			setState(163);
			match(ID);
			setState(164);
			match(T__19);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LITERAL) {
				{
				{
				setState(165);
				senumfield();
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(171);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SenumfieldContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(ThriftParser.LITERAL, 0); }
		public ListseparatorContext listseparator() {
			return getRuleContext(ListseparatorContext.class,0);
		}
		public SenumfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_senumfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterSenumfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitSenumfield(this);
		}
	}

	public final SenumfieldContext senumfield() throws RecognitionException {
		SenumfieldContext _localctx = new SenumfieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_senumfield);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(LITERAL);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__51 || _la==T__61) {
				{
				setState(174);
				listseparator();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public StructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitStruct(this);
		}
	}

	public final StructContext struct() throws RecognitionException {
		StructContext _localctx = new StructContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_struct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__22);
			setState(178);
			match(ID);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(179);
				match(T__23);
				}
			}

			setState(182);
			match(T__19);
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (T__56 - 57)) | (1L << (T__57 - 57)) | (1L << (DIGIT - 57)))) != 0)) {
				{
				{
				setState(183);
				field();
				}
				}
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(189);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitUnion(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_union);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(T__24);
			setState(192);
			match(ID);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(193);
				match(T__23);
				}
			}

			setState(196);
			match(T__19);
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (T__56 - 57)) | (1L << (T__57 - 57)) | (1L << (DIGIT - 57)))) != 0)) {
				{
				{
				setState(197);
				field();
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(203);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExceptionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public ExceptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exception; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterException(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitException(this);
		}
	}

	public final ExceptionContext exception() throws RecognitionException {
		ExceptionContext _localctx = new ExceptionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exception);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(T__25);
			setState(206);
			match(ID);
			setState(207);
			match(T__19);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (T__56 - 57)) | (1L << (T__57 - 57)) | (1L << (DIGIT - 57)))) != 0)) {
				{
				{
				setState(208);
				field();
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(214);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public ServiceparentContext serviceparent() {
			return getRuleContext(ServiceparentContext.class,0);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public ServiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_service; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterService(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitService(this);
		}
	}

	public final ServiceContext service() throws RecognitionException {
		ServiceContext _localctx = new ServiceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_service);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(T__26);
			setState(217);
			match(ID);
			setState(218);
			serviceparent();
			setState(219);
			match(T__19);
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__34) | (1L << T__37) | (1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__53) | (1L << T__54) | (1L << ID))) != 0)) {
				{
				{
				setState(220);
				function();
				}
				}
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(226);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceparentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public ServiceparentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceparent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterServiceparent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitServiceparent(this);
		}
	}

	public final ServiceparentContext serviceparent() throws RecognitionException {
		ServiceparentContext _localctx = new ServiceparentContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_serviceparent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(228);
				match(T__27);
				setState(229);
				match(ID);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public FieldidContext fieldid() {
			return getRuleContext(FieldidContext.class,0);
		}
		public FieldtypeContext fieldtype() {
			return getRuleContext(FieldtypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public XsdfieldoptionsContext xsdfieldoptions() {
			return getRuleContext(XsdfieldoptionsContext.class,0);
		}
		public FieldreqContext fieldreq() {
			return getRuleContext(FieldreqContext.class,0);
		}
		public ConstvalueContext constvalue() {
			return getRuleContext(ConstvalueContext.class,0);
		}
		public ListseparatorContext listseparator() {
			return getRuleContext(ListseparatorContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			fieldid();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__29 || _la==T__30) {
				{
				setState(233);
				fieldreq();
				}
			}

			setState(236);
			fieldtype();
			setState(237);
			match(ID);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(238);
				match(T__16);
				setState(239);
				constvalue();
				}
			}

			setState(242);
			xsdfieldoptions();
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__51 || _la==T__61) {
				{
				setState(243);
				listseparator();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldidContext extends ParserRuleContext {
		public IntconstContext intconst() {
			return getRuleContext(IntconstContext.class,0);
		}
		public FieldidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterFieldid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitFieldid(this);
		}
	}

	public final FieldidContext fieldid() throws RecognitionException {
		FieldidContext _localctx = new FieldidContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_fieldid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			intconst();
			setState(247);
			match(T__28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldreqContext extends ParserRuleContext {
		public FieldreqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldreq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterFieldreq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitFieldreq(this);
		}
	}

	public final FieldreqContext fieldreq() throws RecognitionException {
		FieldreqContext _localctx = new FieldreqContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fieldreq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			_la = _input.LA(1);
			if ( !(_la==T__29 || _la==T__30) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XsdfieldoptionsContext extends ParserRuleContext {
		public XsdattrsContext xsdattrs() {
			return getRuleContext(XsdattrsContext.class,0);
		}
		public XsdfieldoptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xsdfieldoptions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterXsdfieldoptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitXsdfieldoptions(this);
		}
	}

	public final XsdfieldoptionsContext xsdfieldoptions() throws RecognitionException {
		XsdfieldoptionsContext _localctx = new XsdfieldoptionsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_xsdfieldoptions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__31) {
				{
				setState(251);
				match(T__31);
				}
			}

			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__32) {
				{
				setState(254);
				match(T__32);
				}
			}

			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(257);
				xsdattrs();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XsdattrsContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public XsdattrsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xsdattrs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterXsdattrs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitXsdattrs(this);
		}
	}

	public final XsdattrsContext xsdattrs() throws RecognitionException {
		XsdattrsContext _localctx = new XsdattrsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_xsdattrs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(T__33);
			setState(261);
			match(T__19);
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (T__56 - 57)) | (1L << (T__57 - 57)) | (1L << (DIGIT - 57)))) != 0)) {
				{
				{
				setState(262);
				field();
				}
				}
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(268);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public FunctiontypeContext functiontype() {
			return getRuleContext(FunctiontypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public ThrowstatContext throwstat() {
			return getRuleContext(ThrowstatContext.class,0);
		}
		public ListseparatorContext listseparator() {
			return getRuleContext(ListseparatorContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__34) {
				{
				setState(270);
				match(T__34);
				}
			}

			setState(273);
			functiontype();
			setState(274);
			match(ID);
			setState(275);
			match(T__35);
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (T__56 - 57)) | (1L << (T__57 - 57)) | (1L << (DIGIT - 57)))) != 0)) {
				{
				{
				setState(276);
				field();
				}
				}
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(282);
			match(T__36);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__38) {
				{
				setState(283);
				throwstat();
				}
			}

			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__51 || _la==T__61) {
				{
				setState(286);
				listseparator();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctiontypeContext extends ParserRuleContext {
		public FieldtypeContext fieldtype() {
			return getRuleContext(FieldtypeContext.class,0);
		}
		public FunctiontypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functiontype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterFunctiontype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitFunctiontype(this);
		}
	}

	public final FunctiontypeContext functiontype() throws RecognitionException {
		FunctiontypeContext _localctx = new FunctiontypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_functiontype);
		try {
			setState(291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__53:
			case T__54:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				fieldtype();
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				match(T__37);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowstatContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public ThrowstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwstat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterThrowstat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitThrowstat(this);
		}
	}

	public final ThrowstatContext throwstat() throws RecognitionException {
		ThrowstatContext _localctx = new ThrowstatContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_throwstat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__38);
			setState(294);
			match(T__35);
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (T__56 - 57)) | (1L << (T__57 - 57)) | (1L << (DIGIT - 57)))) != 0)) {
				{
				{
				setState(295);
				field();
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(301);
			match(T__36);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldtypeContext extends ParserRuleContext {
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public ContainertypeContext containertype() {
			return getRuleContext(ContainertypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public FieldtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldtype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterFieldtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitFieldtype(this);
		}
	}

	public final FieldtypeContext fieldtype() throws RecognitionException {
		FieldtypeContext _localctx = new FieldtypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_fieldtype);
		try {
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				basetype();
				}
				break;
			case T__49:
			case T__53:
			case T__54:
				enterOuterAlt(_localctx, 2);
				{
				setState(304);
				containertype();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(305);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitiontypeContext extends ParserRuleContext {
		public BasetypeContext basetype() {
			return getRuleContext(BasetypeContext.class,0);
		}
		public ContainertypeContext containertype() {
			return getRuleContext(ContainertypeContext.class,0);
		}
		public DefinitiontypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definitiontype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterDefinitiontype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitDefinitiontype(this);
		}
	}

	public final DefinitiontypeContext definitiontype() throws RecognitionException {
		DefinitiontypeContext _localctx = new DefinitiontypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_definitiontype);
		try {
			setState(310);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				basetype();
				}
				break;
			case T__49:
			case T__53:
			case T__54:
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				containertype();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasetypeContext extends ParserRuleContext {
		public BasetypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basetype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterBasetype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitBasetype(this);
		}
	}

	public final BasetypeContext basetype() throws RecognitionException {
		BasetypeContext _localctx = new BasetypeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_basetype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__39) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContainertypeContext extends ParserRuleContext {
		public MaptypeContext maptype() {
			return getRuleContext(MaptypeContext.class,0);
		}
		public SettypeContext settype() {
			return getRuleContext(SettypeContext.class,0);
		}
		public ListtypeContext listtype() {
			return getRuleContext(ListtypeContext.class,0);
		}
		public ContainertypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_containertype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterContainertype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitContainertype(this);
		}
	}

	public final ContainertypeContext containertype() throws RecognitionException {
		ContainertypeContext _localctx = new ContainertypeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_containertype);
		try {
			setState(317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__49:
				enterOuterAlt(_localctx, 1);
				{
				setState(314);
				maptype();
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
				settype();
				}
				break;
			case T__54:
				enterOuterAlt(_localctx, 3);
				{
				setState(316);
				listtype();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MaptypeContext extends ParserRuleContext {
		public List<FieldtypeContext> fieldtype() {
			return getRuleContexts(FieldtypeContext.class);
		}
		public FieldtypeContext fieldtype(int i) {
			return getRuleContext(FieldtypeContext.class,i);
		}
		public CpptypeContext cpptype() {
			return getRuleContext(CpptypeContext.class,0);
		}
		public MaptypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maptype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterMaptype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitMaptype(this);
		}
	}

	public final MaptypeContext maptype() throws RecognitionException {
		MaptypeContext _localctx = new MaptypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_maptype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(T__49);
			setState(321);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__55) {
				{
				setState(320);
				cpptype();
				}
			}

			setState(323);
			match(T__50);
			setState(324);
			fieldtype();
			setState(325);
			match(T__51);
			setState(326);
			fieldtype();
			setState(327);
			match(T__52);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SettypeContext extends ParserRuleContext {
		public FieldtypeContext fieldtype() {
			return getRuleContext(FieldtypeContext.class,0);
		}
		public CpptypeContext cpptype() {
			return getRuleContext(CpptypeContext.class,0);
		}
		public SettypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_settype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterSettype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitSettype(this);
		}
	}

	public final SettypeContext settype() throws RecognitionException {
		SettypeContext _localctx = new SettypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_settype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			match(T__53);
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__55) {
				{
				setState(330);
				cpptype();
				}
			}

			setState(333);
			match(T__50);
			setState(334);
			fieldtype();
			setState(335);
			match(T__52);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListtypeContext extends ParserRuleContext {
		public FieldtypeContext fieldtype() {
			return getRuleContext(FieldtypeContext.class,0);
		}
		public CpptypeContext cpptype() {
			return getRuleContext(CpptypeContext.class,0);
		}
		public ListtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listtype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterListtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitListtype(this);
		}
	}

	public final ListtypeContext listtype() throws RecognitionException {
		ListtypeContext _localctx = new ListtypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_listtype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			match(T__54);
			setState(338);
			match(T__50);
			setState(339);
			fieldtype();
			setState(340);
			match(T__52);
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__55) {
				{
				setState(341);
				cpptype();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CpptypeContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(ThriftParser.LITERAL, 0); }
		public CpptypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cpptype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterCpptype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitCpptype(this);
		}
	}

	public final CpptypeContext cpptype() throws RecognitionException {
		CpptypeContext _localctx = new CpptypeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_cpptype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			match(T__55);
			setState(345);
			match(LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstvalueContext extends ParserRuleContext {
		public OneconstContext oneconst() {
			return getRuleContext(OneconstContext.class,0);
		}
		public ConstlistContext constlist() {
			return getRuleContext(ConstlistContext.class,0);
		}
		public ConstvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterConstvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitConstvalue(this);
		}
	}

	public final ConstvalueContext constvalue() throws RecognitionException {
		ConstvalueContext _localctx = new ConstvalueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_constvalue);
		try {
			setState(349);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(347);
				oneconst();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
				constlist();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntconstContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(ThriftParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(ThriftParser.DIGIT, i);
		}
		public IntconstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intconst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterIntconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitIntconst(this);
		}
	}

	public final IntconstContext intconst() throws RecognitionException {
		IntconstContext _localctx = new IntconstContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_intconst);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__56 || _la==T__57) {
				{
				setState(351);
				_la = _input.LA(1);
				if ( !(_la==T__56 || _la==T__57) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(355); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(354);
					match(DIGIT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(357); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleconstContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(ThriftParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(ThriftParser.DIGIT, i);
		}
		public TerminalNode ES() { return getToken(ThriftParser.ES, 0); }
		public IntconstContext intconst() {
			return getRuleContext(IntconstContext.class,0);
		}
		public DoubleconstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleconst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterDoubleconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitDoubleconst(this);
		}
	}

	public final DoubleconstContext doubleconst() throws RecognitionException {
		DoubleconstContext _localctx = new DoubleconstContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_doubleconst);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(359);
				_la = _input.LA(1);
				if ( !(_la==T__56 || _la==T__57) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			setState(365);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(362);
					match(DIGIT);
					}
					} 
				}
				setState(367);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			setState(374);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(368);
				match(T__58);
				setState(370); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(369);
						match(DIGIT);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(372); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			setState(378);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(376);
				match(ES);
				setState(377);
				intconst();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstlistContext extends ParserRuleContext {
		public List<OneconstContext> oneconst() {
			return getRuleContexts(OneconstContext.class);
		}
		public OneconstContext oneconst(int i) {
			return getRuleContext(OneconstContext.class,i);
		}
		public List<ListseparatorContext> listseparator() {
			return getRuleContexts(ListseparatorContext.class);
		}
		public ListseparatorContext listseparator(int i) {
			return getRuleContext(ListseparatorContext.class,i);
		}
		public List<ConstlistContext> constlist() {
			return getRuleContexts(ConstlistContext.class);
		}
		public ConstlistContext constlist(int i) {
			return getRuleContext(ConstlistContext.class,i);
		}
		public ConstlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterConstlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitConstlist(this);
		}
	}

	public final ConstlistContext constlist() throws RecognitionException {
		ConstlistContext _localctx = new ConstlistContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_constlist);
		int _la;
		try {
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(380);
				match(T__59);
				setState(381);
				oneconst();
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__51 || _la==T__61) {
					{
					{
					setState(382);
					listseparator();
					setState(383);
					oneconst();
					}
					}
					setState(389);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(390);
				match(T__60);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(392);
				match(T__59);
				setState(393);
				constlist();
				setState(399);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__51 || _la==T__61) {
					{
					{
					setState(394);
					listseparator();
					setState(395);
					constlist();
					}
					}
					setState(401);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(402);
				match(T__60);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstmapContext extends ParserRuleContext {
		public List<ConstvalueContext> constvalue() {
			return getRuleContexts(ConstvalueContext.class);
		}
		public ConstvalueContext constvalue(int i) {
			return getRuleContext(ConstvalueContext.class,i);
		}
		public List<ListseparatorContext> listseparator() {
			return getRuleContexts(ListseparatorContext.class);
		}
		public ListseparatorContext listseparator(int i) {
			return getRuleContext(ListseparatorContext.class,i);
		}
		public ConstmapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constmap; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterConstmap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitConstmap(this);
		}
	}

	public final ConstmapContext constmap() throws RecognitionException {
		ConstmapContext _localctx = new ConstmapContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_constmap);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			match(T__19);
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (T__19 - 20)) | (1L << (T__28 - 20)) | (1L << (T__56 - 20)) | (1L << (T__57 - 20)) | (1L << (T__58 - 20)) | (1L << (T__59 - 20)) | (1L << (ID - 20)) | (1L << (DIGIT - 20)) | (1L << (ES - 20)) | (1L << (LITERAL - 20)))) != 0)) {
				{
				{
				setState(407);
				constvalue();
				setState(408);
				match(T__28);
				setState(409);
				constvalue();
				setState(411);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__51 || _la==T__61) {
					{
					setState(410);
					listseparator();
					}
				}

				}
				}
				setState(417);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(418);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OneconstContext extends ParserRuleContext {
		public IntconstContext intconst() {
			return getRuleContext(IntconstContext.class,0);
		}
		public DoubleconstContext doubleconst() {
			return getRuleContext(DoubleconstContext.class,0);
		}
		public TerminalNode LITERAL() { return getToken(ThriftParser.LITERAL, 0); }
		public TerminalNode ID() { return getToken(ThriftParser.ID, 0); }
		public ConstmapContext constmap() {
			return getRuleContext(ConstmapContext.class,0);
		}
		public OneconstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oneconst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterOneconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitOneconst(this);
		}
	}

	public final OneconstContext oneconst() throws RecognitionException {
		OneconstContext _localctx = new OneconstContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_oneconst);
		try {
			setState(425);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(420);
				intconst();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(421);
				doubleconst();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(422);
				match(LITERAL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(423);
				match(ID);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(424);
				constmap();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListseparatorContext extends ParserRuleContext {
		public ListseparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listseparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).enterListseparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ThriftListener ) ((ThriftListener)listener).exitListseparator(this);
		}
	}

	public final ListseparatorContext listseparator() throws RecognitionException {
		ListseparatorContext _localctx = new ListseparatorContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_listseparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			_la = _input.LA(1);
			if ( !(_la==T__51 || _la==T__61) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3I\u01b0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\7\2"+
		"V\n\2\f\2\16\2Y\13\2\3\2\7\2\\\n\2\f\2\16\2_\13\2\3\3\3\3\3\3\5\3d\n\3"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6x\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0084\n\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u008c\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\7\13\u0096\n\13\f\13\16\13\u0099\13\13\3\13\3\13\3\f\3\f\3\f\5\f\u00a0"+
		"\n\f\3\f\5\f\u00a3\n\f\3\r\3\r\3\r\3\r\7\r\u00a9\n\r\f\r\16\r\u00ac\13"+
		"\r\3\r\3\r\3\16\3\16\5\16\u00b2\n\16\3\17\3\17\3\17\5\17\u00b7\n\17\3"+
		"\17\3\17\7\17\u00bb\n\17\f\17\16\17\u00be\13\17\3\17\3\17\3\20\3\20\3"+
		"\20\5\20\u00c5\n\20\3\20\3\20\7\20\u00c9\n\20\f\20\16\20\u00cc\13\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\7\21\u00d4\n\21\f\21\16\21\u00d7\13\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\22\7\22\u00e0\n\22\f\22\16\22\u00e3\13"+
		"\22\3\22\3\22\3\23\3\23\5\23\u00e9\n\23\3\24\3\24\5\24\u00ed\n\24\3\24"+
		"\3\24\3\24\3\24\5\24\u00f3\n\24\3\24\3\24\5\24\u00f7\n\24\3\25\3\25\3"+
		"\25\3\26\3\26\3\27\5\27\u00ff\n\27\3\27\5\27\u0102\n\27\3\27\5\27\u0105"+
		"\n\27\3\30\3\30\3\30\7\30\u010a\n\30\f\30\16\30\u010d\13\30\3\30\3\30"+
		"\3\31\5\31\u0112\n\31\3\31\3\31\3\31\3\31\7\31\u0118\n\31\f\31\16\31\u011b"+
		"\13\31\3\31\3\31\5\31\u011f\n\31\3\31\5\31\u0122\n\31\3\32\3\32\5\32\u0126"+
		"\n\32\3\33\3\33\3\33\7\33\u012b\n\33\f\33\16\33\u012e\13\33\3\33\3\33"+
		"\3\34\3\34\3\34\5\34\u0135\n\34\3\35\3\35\5\35\u0139\n\35\3\36\3\36\3"+
		"\37\3\37\3\37\5\37\u0140\n\37\3 \3 \5 \u0144\n \3 \3 \3 \3 \3 \3 \3!\3"+
		"!\5!\u014e\n!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\5\"\u0159\n\"\3#\3#\3#\3"+
		"$\3$\5$\u0160\n$\3%\5%\u0163\n%\3%\6%\u0166\n%\r%\16%\u0167\3&\5&\u016b"+
		"\n&\3&\7&\u016e\n&\f&\16&\u0171\13&\3&\3&\6&\u0175\n&\r&\16&\u0176\5&"+
		"\u0179\n&\3&\3&\5&\u017d\n&\3\'\3\'\3\'\3\'\3\'\7\'\u0184\n\'\f\'\16\'"+
		"\u0187\13\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\7\'\u0190\n\'\f\'\16\'\u0193\13"+
		"\'\3\'\3\'\5\'\u0197\n\'\3(\3(\3(\3(\3(\5(\u019e\n(\7(\u01a0\n(\f(\16"+
		"(\u01a3\13(\3(\3(\3)\3)\3)\3)\3)\5)\u01ac\n)\3*\3*\3*\2\2+\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\7\3\2"+
		"\n\21\3\2 !\3\2*\63\3\2;<\4\2\66\66@@\2\u01c8\2W\3\2\2\2\4c\3\2\2\2\6"+
		"e\3\2\2\2\bh\3\2\2\2\nw\3\2\2\2\fy\3\2\2\2\16\u0083\3\2\2\2\20\u0085\3"+
		"\2\2\2\22\u008d\3\2\2\2\24\u0091\3\2\2\2\26\u009c\3\2\2\2\30\u00a4\3\2"+
		"\2\2\32\u00af\3\2\2\2\34\u00b3\3\2\2\2\36\u00c1\3\2\2\2 \u00cf\3\2\2\2"+
		"\"\u00da\3\2\2\2$\u00e8\3\2\2\2&\u00ea\3\2\2\2(\u00f8\3\2\2\2*\u00fb\3"+
		"\2\2\2,\u00fe\3\2\2\2.\u0106\3\2\2\2\60\u0111\3\2\2\2\62\u0125\3\2\2\2"+
		"\64\u0127\3\2\2\2\66\u0134\3\2\2\28\u0138\3\2\2\2:\u013a\3\2\2\2<\u013f"+
		"\3\2\2\2>\u0141\3\2\2\2@\u014b\3\2\2\2B\u0153\3\2\2\2D\u015a\3\2\2\2F"+
		"\u015f\3\2\2\2H\u0162\3\2\2\2J\u016a\3\2\2\2L\u0196\3\2\2\2N\u0198\3\2"+
		"\2\2P\u01ab\3\2\2\2R\u01ad\3\2\2\2TV\5\4\3\2UT\3\2\2\2VY\3\2\2\2WU\3\2"+
		"\2\2WX\3\2\2\2X]\3\2\2\2YW\3\2\2\2Z\\\5\16\b\2[Z\3\2\2\2\\_\3\2\2\2]["+
		"\3\2\2\2]^\3\2\2\2^\3\3\2\2\2_]\3\2\2\2`d\5\6\4\2ad\5\b\5\2bd\5\n\6\2"+
		"c`\3\2\2\2ca\3\2\2\2cb\3\2\2\2d\5\3\2\2\2ef\7\3\2\2fg\7F\2\2g\7\3\2\2"+
		"\2hi\7\4\2\2ij\7F\2\2j\t\3\2\2\2kl\7\5\2\2lm\5\f\7\2mn\7A\2\2nx\3\2\2"+
		"\2op\7\6\2\2px\7B\2\2qr\7\7\2\2rx\7A\2\2st\7\b\2\2tx\7F\2\2uv\7\t\2\2"+
		"vx\7F\2\2wk\3\2\2\2wo\3\2\2\2wq\3\2\2\2ws\3\2\2\2wu\3\2\2\2x\13\3\2\2"+
		"\2yz\t\2\2\2z\r\3\2\2\2{\u0084\5\20\t\2|\u0084\5\22\n\2}\u0084\5\24\13"+
		"\2~\u0084\5\30\r\2\177\u0084\5\34\17\2\u0080\u0084\5\36\20\2\u0081\u0084"+
		"\5 \21\2\u0082\u0084\5\"\22\2\u0083{\3\2\2\2\u0083|\3\2\2\2\u0083}\3\2"+
		"\2\2\u0083~\3\2\2\2\u0083\177\3\2\2\2\u0083\u0080\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0083\u0082\3\2\2\2\u0084\17\3\2\2\2\u0085\u0086\7\22\2\2\u0086"+
		"\u0087\5\66\34\2\u0087\u0088\7A\2\2\u0088\u0089\7\23\2\2\u0089\u008b\5"+
		"F$\2\u008a\u008c\5R*\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\21"+
		"\3\2\2\2\u008d\u008e\7\24\2\2\u008e\u008f\58\35\2\u008f\u0090\7A\2\2\u0090"+
		"\23\3\2\2\2\u0091\u0092\7\25\2\2\u0092\u0093\7A\2\2\u0093\u0097\7\26\2"+
		"\2\u0094\u0096\5\26\f\2\u0095\u0094\3\2\2\2\u0096\u0099\3\2\2\2\u0097"+
		"\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u0097\3\2"+
		"\2\2\u009a\u009b\7\27\2\2\u009b\25\3\2\2\2\u009c\u009f\7A\2\2\u009d\u009e"+
		"\7\23\2\2\u009e\u00a0\5H%\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a2\3\2\2\2\u00a1\u00a3\5R*\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2"+
		"\2\u00a3\27\3\2\2\2\u00a4\u00a5\7\30\2\2\u00a5\u00a6\7A\2\2\u00a6\u00aa"+
		"\7\26\2\2\u00a7\u00a9\5\32\16\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3\2\2"+
		"\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00aa"+
		"\3\2\2\2\u00ad\u00ae\7\27\2\2\u00ae\31\3\2\2\2\u00af\u00b1\7F\2\2\u00b0"+
		"\u00b2\5R*\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\33\3\2\2\2"+
		"\u00b3\u00b4\7\31\2\2\u00b4\u00b6\7A\2\2\u00b5\u00b7\7\32\2\2\u00b6\u00b5"+
		"\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00bc\7\26\2\2"+
		"\u00b9\u00bb\5&\24\2\u00ba\u00b9\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba"+
		"\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf"+
		"\u00c0\7\27\2\2\u00c0\35\3\2\2\2\u00c1\u00c2\7\33\2\2\u00c2\u00c4\7A\2"+
		"\2\u00c3\u00c5\7\32\2\2\u00c4\u00c3\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\u00ca\7\26\2\2\u00c7\u00c9\5&\24\2\u00c8\u00c7\3"+
		"\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\u00cd\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00ce\7\27\2\2\u00ce\37\3\2\2"+
		"\2\u00cf\u00d0\7\34\2\2\u00d0\u00d1\7A\2\2\u00d1\u00d5\7\26\2\2\u00d2"+
		"\u00d4\5&\24\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8"+
		"\u00d9\7\27\2\2\u00d9!\3\2\2\2\u00da\u00db\7\35\2\2\u00db\u00dc\7A\2\2"+
		"\u00dc\u00dd\5$\23\2\u00dd\u00e1\7\26\2\2\u00de\u00e0\5\60\31\2\u00df"+
		"\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2"+
		"\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7\27\2\2\u00e5"+
		"#\3\2\2\2\u00e6\u00e7\7\36\2\2\u00e7\u00e9\7A\2\2\u00e8\u00e6\3\2\2\2"+
		"\u00e8\u00e9\3\2\2\2\u00e9%\3\2\2\2\u00ea\u00ec\5(\25\2\u00eb\u00ed\5"+
		"*\26\2\u00ec\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee"+
		"\u00ef\5\66\34\2\u00ef\u00f2\7A\2\2\u00f0\u00f1\7\23\2\2\u00f1\u00f3\5"+
		"F$\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4"+
		"\u00f6\5,\27\2\u00f5\u00f7\5R*\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2"+
		"\2\u00f7\'\3\2\2\2\u00f8\u00f9\5H%\2\u00f9\u00fa\7\37\2\2\u00fa)\3\2\2"+
		"\2\u00fb\u00fc\t\3\2\2\u00fc+\3\2\2\2\u00fd\u00ff\7\"\2\2\u00fe\u00fd"+
		"\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3\2\2\2\u0100\u0102\7#\2\2\u0101"+
		"\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2\2\2\u0103\u0105\5."+
		"\30\2\u0104\u0103\3\2\2\2\u0104\u0105\3\2\2\2\u0105-\3\2\2\2\u0106\u0107"+
		"\7$\2\2\u0107\u010b\7\26\2\2\u0108\u010a\5&\24\2\u0109\u0108\3\2\2\2\u010a"+
		"\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e\3\2"+
		"\2\2\u010d\u010b\3\2\2\2\u010e\u010f\7\27\2\2\u010f/\3\2\2\2\u0110\u0112"+
		"\7%\2\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0114\5\62\32\2\u0114\u0115\7A\2\2\u0115\u0119\7&\2\2\u0116\u0118\5&"+
		"\24\2\u0117\u0116\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u0119"+
		"\u011a\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u011e\7\'"+
		"\2\2\u011d\u011f\5\64\33\2\u011e\u011d\3\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"\u0121\3\2\2\2\u0120\u0122\5R*\2\u0121\u0120\3\2\2\2\u0121\u0122\3\2\2"+
		"\2\u0122\61\3\2\2\2\u0123\u0126\5\66\34\2\u0124\u0126\7(\2\2\u0125\u0123"+
		"\3\2\2\2\u0125\u0124\3\2\2\2\u0126\63\3\2\2\2\u0127\u0128\7)\2\2\u0128"+
		"\u012c\7&\2\2\u0129\u012b\5&\24\2\u012a\u0129\3\2\2\2\u012b\u012e\3\2"+
		"\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012f\3\2\2\2\u012e"+
		"\u012c\3\2\2\2\u012f\u0130\7\'\2\2\u0130\65\3\2\2\2\u0131\u0135\5:\36"+
		"\2\u0132\u0135\5<\37\2\u0133\u0135\7A\2\2\u0134\u0131\3\2\2\2\u0134\u0132"+
		"\3\2\2\2\u0134\u0133\3\2\2\2\u0135\67\3\2\2\2\u0136\u0139\5:\36\2\u0137"+
		"\u0139\5<\37\2\u0138\u0136\3\2\2\2\u0138\u0137\3\2\2\2\u01399\3\2\2\2"+
		"\u013a\u013b\t\4\2\2\u013b;\3\2\2\2\u013c\u0140\5> \2\u013d\u0140\5@!"+
		"\2\u013e\u0140\5B\"\2\u013f\u013c\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u013e"+
		"\3\2\2\2\u0140=\3\2\2\2\u0141\u0143\7\64\2\2\u0142\u0144\5D#\2\u0143\u0142"+
		"\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\7\65\2\2"+
		"\u0146\u0147\5\66\34\2\u0147\u0148\7\66\2\2\u0148\u0149\5\66\34\2\u0149"+
		"\u014a\7\67\2\2\u014a?\3\2\2\2\u014b\u014d\78\2\2\u014c\u014e\5D#\2\u014d"+
		"\u014c\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\7\65"+
		"\2\2\u0150\u0151\5\66\34\2\u0151\u0152\7\67\2\2\u0152A\3\2\2\2\u0153\u0154"+
		"\79\2\2\u0154\u0155\7\65\2\2\u0155\u0156\5\66\34\2\u0156\u0158\7\67\2"+
		"\2\u0157\u0159\5D#\2\u0158\u0157\3\2\2\2\u0158\u0159\3\2\2\2\u0159C\3"+
		"\2\2\2\u015a\u015b\7:\2\2\u015b\u015c\7F\2\2\u015cE\3\2\2\2\u015d\u0160"+
		"\5P)\2\u015e\u0160\5L\'\2\u015f\u015d\3\2\2\2\u015f\u015e\3\2\2\2\u0160"+
		"G\3\2\2\2\u0161\u0163\t\5\2\2\u0162\u0161\3\2\2\2\u0162\u0163\3\2\2\2"+
		"\u0163\u0165\3\2\2\2\u0164\u0166\7C\2\2\u0165\u0164\3\2\2\2\u0166\u0167"+
		"\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168I\3\2\2\2\u0169"+
		"\u016b\t\5\2\2\u016a\u0169\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016f\3\2"+
		"\2\2\u016c\u016e\7C\2\2\u016d\u016c\3\2\2\2\u016e\u0171\3\2\2\2\u016f"+
		"\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0178\3\2\2\2\u0171\u016f\3\2"+
		"\2\2\u0172\u0174\7=\2\2\u0173\u0175\7C\2\2\u0174\u0173\3\2\2\2\u0175\u0176"+
		"\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0179\3\2\2\2\u0178"+
		"\u0172\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017c\3\2\2\2\u017a\u017b\7E"+
		"\2\2\u017b\u017d\5H%\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017dK"+
		"\3\2\2\2\u017e\u017f\7>\2\2\u017f\u0185\5P)\2\u0180\u0181\5R*\2\u0181"+
		"\u0182\5P)\2\u0182\u0184\3\2\2\2\u0183\u0180\3\2\2\2\u0184\u0187\3\2\2"+
		"\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0188\3\2\2\2\u0187\u0185"+
		"\3\2\2\2\u0188\u0189\7?\2\2\u0189\u0197\3\2\2\2\u018a\u018b\7>\2\2\u018b"+
		"\u0191\5L\'\2\u018c\u018d\5R*\2\u018d\u018e\5L\'\2\u018e\u0190\3\2\2\2"+
		"\u018f\u018c\3\2\2\2\u0190\u0193\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192"+
		"\3\2\2\2\u0192\u0194\3\2\2\2\u0193\u0191\3\2\2\2\u0194\u0195\7?\2\2\u0195"+
		"\u0197\3\2\2\2\u0196\u017e\3\2\2\2\u0196\u018a\3\2\2\2\u0197M\3\2\2\2"+
		"\u0198\u01a1\7\26\2\2\u0199\u019a\5F$\2\u019a\u019b\7\37\2\2\u019b\u019d"+
		"\5F$\2\u019c\u019e\5R*\2\u019d\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e"+
		"\u01a0\3\2\2\2\u019f\u0199\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1\u019f\3\2"+
		"\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a4\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a4"+
		"\u01a5\7\27\2\2\u01a5O\3\2\2\2\u01a6\u01ac\5H%\2\u01a7\u01ac\5J&\2\u01a8"+
		"\u01ac\7F\2\2\u01a9\u01ac\7A\2\2\u01aa\u01ac\5N(\2\u01ab\u01a6\3\2\2\2"+
		"\u01ab\u01a7\3\2\2\2\u01ab\u01a8\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01aa"+
		"\3\2\2\2\u01acQ\3\2\2\2\u01ad\u01ae\t\6\2\2\u01aeS\3\2\2\2\65W]cw\u0083"+
		"\u008b\u0097\u009f\u00a2\u00aa\u00b1\u00b6\u00bc\u00c4\u00ca\u00d5\u00e1"+
		"\u00e8\u00ec\u00f2\u00f6\u00fe\u0101\u0104\u010b\u0111\u0119\u011e\u0121"+
		"\u0125\u012c\u0134\u0138\u013f\u0143\u014d\u0158\u015f\u0162\u0167\u016a"+
		"\u016f\u0176\u0178\u017c\u0185\u0191\u0196\u019d\u01a1\u01ab";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}