package com.github.jakz.jplot.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Operation;
import com.github.jakz.jplot.ast.Operators;
import com.github.jakz.jplot.ast.Value;
import com.github.jakz.jplot.ast.Variable;

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
    public Variable visitIdentifier(LanguageParser.IdentifierContext ctx)
    {
      return new Variable(ctx.WORD().getText());
    }
    
    @Override
    public Expression visitTerminal(LanguageParser.TerminalContext ctx)
    {
      if (ctx.expression() != null)
        return visit(ctx.expression());
      else if (ctx.literal() != null)
        return visit(ctx.literal());
      else
        throw new IllegalArgumentException("Unknown terminal node");
    }
    
    @Override
    public Expression visitExpression(LanguageParser.ExpressionContext ctx)
    {      
      if (ctx.terminal() != null)
        return visit(ctx.terminal());
      else if (ctx.expression().size() == 2)
        return new Operation(Operators.of(ctx.op.getText()), ctx.expression().stream().map(this::visit).toArray(i -> new Expression[i]));
      else
        throw new IllegalArgumentException("Unknown expression node");
    }
    
    @Override
    public Expression visitStart(LanguageParser.StartContext ctx)
    {
      return visit(ctx.expression());
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
