// Generated from Thrift.g4 by ANTLR 4.7.1
package com.github.rolandhe.thrift.enhancer.translator.idl;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ThriftParser}.
 */
public interface ThriftListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ThriftParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(ThriftParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(ThriftParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(ThriftParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(ThriftParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#include}.
	 * @param ctx the parse tree
	 */
	void enterInclude(ThriftParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#include}.
	 * @param ctx the parse tree
	 */
	void exitInclude(ThriftParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#cppinclude}.
	 * @param ctx the parse tree
	 */
	void enterCppinclude(ThriftParser.CppincludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#cppinclude}.
	 * @param ctx the parse tree
	 */
	void exitCppinclude(ThriftParser.CppincludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterNamespace(ThriftParser.NamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitNamespace(ThriftParser.NamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#namespacescope}.
	 * @param ctx the parse tree
	 */
	void enterNamespacescope(ThriftParser.NamespacescopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#namespacescope}.
	 * @param ctx the parse tree
	 */
	void exitNamespacescope(ThriftParser.NamespacescopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(ThriftParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(ThriftParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#constdef}.
	 * @param ctx the parse tree
	 */
	void enterConstdef(ThriftParser.ConstdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#constdef}.
	 * @param ctx the parse tree
	 */
	void exitConstdef(ThriftParser.ConstdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#typedef}.
	 * @param ctx the parse tree
	 */
	void enterTypedef(ThriftParser.TypedefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#typedef}.
	 * @param ctx the parse tree
	 */
	void exitTypedef(ThriftParser.TypedefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#tenum}.
	 * @param ctx the parse tree
	 */
	void enterTenum(ThriftParser.TenumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#tenum}.
	 * @param ctx the parse tree
	 */
	void exitTenum(ThriftParser.TenumContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#tenumfield}.
	 * @param ctx the parse tree
	 */
	void enterTenumfield(ThriftParser.TenumfieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#tenumfield}.
	 * @param ctx the parse tree
	 */
	void exitTenumfield(ThriftParser.TenumfieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#senum}.
	 * @param ctx the parse tree
	 */
	void enterSenum(ThriftParser.SenumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#senum}.
	 * @param ctx the parse tree
	 */
	void exitSenum(ThriftParser.SenumContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#senumfield}.
	 * @param ctx the parse tree
	 */
	void enterSenumfield(ThriftParser.SenumfieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#senumfield}.
	 * @param ctx the parse tree
	 */
	void exitSenumfield(ThriftParser.SenumfieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(ThriftParser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(ThriftParser.StructContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(ThriftParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(ThriftParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#exception}.
	 * @param ctx the parse tree
	 */
	void enterException(ThriftParser.ExceptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#exception}.
	 * @param ctx the parse tree
	 */
	void exitException(ThriftParser.ExceptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#service}.
	 * @param ctx the parse tree
	 */
	void enterService(ThriftParser.ServiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#service}.
	 * @param ctx the parse tree
	 */
	void exitService(ThriftParser.ServiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#serviceparent}.
	 * @param ctx the parse tree
	 */
	void enterServiceparent(ThriftParser.ServiceparentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#serviceparent}.
	 * @param ctx the parse tree
	 */
	void exitServiceparent(ThriftParser.ServiceparentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(ThriftParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(ThriftParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#fieldid}.
	 * @param ctx the parse tree
	 */
	void enterFieldid(ThriftParser.FieldidContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#fieldid}.
	 * @param ctx the parse tree
	 */
	void exitFieldid(ThriftParser.FieldidContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#fieldreq}.
	 * @param ctx the parse tree
	 */
	void enterFieldreq(ThriftParser.FieldreqContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#fieldreq}.
	 * @param ctx the parse tree
	 */
	void exitFieldreq(ThriftParser.FieldreqContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#xsdfieldoptions}.
	 * @param ctx the parse tree
	 */
	void enterXsdfieldoptions(ThriftParser.XsdfieldoptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#xsdfieldoptions}.
	 * @param ctx the parse tree
	 */
	void exitXsdfieldoptions(ThriftParser.XsdfieldoptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#xsdattrs}.
	 * @param ctx the parse tree
	 */
	void enterXsdattrs(ThriftParser.XsdattrsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#xsdattrs}.
	 * @param ctx the parse tree
	 */
	void exitXsdattrs(ThriftParser.XsdattrsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(ThriftParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(ThriftParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#functiontype}.
	 * @param ctx the parse tree
	 */
	void enterFunctiontype(ThriftParser.FunctiontypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#functiontype}.
	 * @param ctx the parse tree
	 */
	void exitFunctiontype(ThriftParser.FunctiontypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#throwstat}.
	 * @param ctx the parse tree
	 */
	void enterThrowstat(ThriftParser.ThrowstatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#throwstat}.
	 * @param ctx the parse tree
	 */
	void exitThrowstat(ThriftParser.ThrowstatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#fieldtype}.
	 * @param ctx the parse tree
	 */
	void enterFieldtype(ThriftParser.FieldtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#fieldtype}.
	 * @param ctx the parse tree
	 */
	void exitFieldtype(ThriftParser.FieldtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#definitiontype}.
	 * @param ctx the parse tree
	 */
	void enterDefinitiontype(ThriftParser.DefinitiontypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#definitiontype}.
	 * @param ctx the parse tree
	 */
	void exitDefinitiontype(ThriftParser.DefinitiontypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#basetype}.
	 * @param ctx the parse tree
	 */
	void enterBasetype(ThriftParser.BasetypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#basetype}.
	 * @param ctx the parse tree
	 */
	void exitBasetype(ThriftParser.BasetypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#containertype}.
	 * @param ctx the parse tree
	 */
	void enterContainertype(ThriftParser.ContainertypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#containertype}.
	 * @param ctx the parse tree
	 */
	void exitContainertype(ThriftParser.ContainertypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#maptype}.
	 * @param ctx the parse tree
	 */
	void enterMaptype(ThriftParser.MaptypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#maptype}.
	 * @param ctx the parse tree
	 */
	void exitMaptype(ThriftParser.MaptypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#settype}.
	 * @param ctx the parse tree
	 */
	void enterSettype(ThriftParser.SettypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#settype}.
	 * @param ctx the parse tree
	 */
	void exitSettype(ThriftParser.SettypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#listtype}.
	 * @param ctx the parse tree
	 */
	void enterListtype(ThriftParser.ListtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#listtype}.
	 * @param ctx the parse tree
	 */
	void exitListtype(ThriftParser.ListtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#cpptype}.
	 * @param ctx the parse tree
	 */
	void enterCpptype(ThriftParser.CpptypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#cpptype}.
	 * @param ctx the parse tree
	 */
	void exitCpptype(ThriftParser.CpptypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#constvalue}.
	 * @param ctx the parse tree
	 */
	void enterConstvalue(ThriftParser.ConstvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#constvalue}.
	 * @param ctx the parse tree
	 */
	void exitConstvalue(ThriftParser.ConstvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#intconst}.
	 * @param ctx the parse tree
	 */
	void enterIntconst(ThriftParser.IntconstContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#intconst}.
	 * @param ctx the parse tree
	 */
	void exitIntconst(ThriftParser.IntconstContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#doubleconst}.
	 * @param ctx the parse tree
	 */
	void enterDoubleconst(ThriftParser.DoubleconstContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#doubleconst}.
	 * @param ctx the parse tree
	 */
	void exitDoubleconst(ThriftParser.DoubleconstContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#constlist}.
	 * @param ctx the parse tree
	 */
	void enterConstlist(ThriftParser.ConstlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#constlist}.
	 * @param ctx the parse tree
	 */
	void exitConstlist(ThriftParser.ConstlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#constmap}.
	 * @param ctx the parse tree
	 */
	void enterConstmap(ThriftParser.ConstmapContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#constmap}.
	 * @param ctx the parse tree
	 */
	void exitConstmap(ThriftParser.ConstmapContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#oneconst}.
	 * @param ctx the parse tree
	 */
	void enterOneconst(ThriftParser.OneconstContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#oneconst}.
	 * @param ctx the parse tree
	 */
	void exitOneconst(ThriftParser.OneconstContext ctx);
	/**
	 * Enter a parse tree produced by {@link ThriftParser#listseparator}.
	 * @param ctx the parse tree
	 */
	void enterListseparator(ThriftParser.ListseparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ThriftParser#listseparator}.
	 * @param ctx the parse tree
	 */
	void exitListseparator(ThriftParser.ListseparatorContext ctx);
}