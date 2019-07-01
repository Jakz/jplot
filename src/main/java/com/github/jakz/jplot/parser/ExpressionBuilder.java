package com.github.jakz.jplot.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Value;

public class ExpressionBuilder
{
  public static class Visitor extends LanguageBaseVisitor<Expression>
  {
    @Override
    public Value visitInteger(LanguageParser.IntegerContext ctx)
    {
      return new Value(Integer.valueOf(ctx.INTEGER().getText()));
    }
    
    @Override
    public Expression visitStart(LanguageParser.StartContext ctx)
    {
      return visit(ctx.integer());
    }
  }
  
  public static Expression of(String input)
  {
    CharStream stream = CharStreams.fromString(input);
    LanguageLexer lexer = new LanguageLexer(stream);
    CommonTokenStream tstream = new CommonTokenStream(lexer);
    LanguageParser parser = new LanguageParser(tstream);
   
    
    LanguageParser.StartContext context = parser.start();
    
    
    return new Visitor().visit(context);
  }
}
