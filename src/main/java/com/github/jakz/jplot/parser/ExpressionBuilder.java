package com.github.jakz.jplot.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;

import com.github.jakz.jplot.ast.Expression;
import com.github.jakz.jplot.ast.Operation;
import com.github.jakz.jplot.ast.Operator;
import com.github.jakz.jplot.ast.Operators;
import com.github.jakz.jplot.ast.Root;
import com.github.jakz.jplot.ast.Number;
import com.github.jakz.jplot.ast.Variable;

public class ExpressionBuilder
{
  public static class Visitor extends LanguageBaseVisitor<Expression>
  {
    @Override
    public Number visitInteger(LanguageParser.IntegerContext ctx)
    {
      return new Number(Integer.valueOf(ctx.INTEGER().getText()));
    }
        
    @Override
    public Expression visitLiteral(LanguageParser.LiteralContext ctx)
    {
      if (ctx.integer() != null)
        return visit(ctx.integer());
      else if (ctx.IDENTIFIER() != null)
        return new Variable(ctx.getText());
      else if (ctx.WRONG_IDENTIFIER() != null)
        throw new ParseException("Invalid identifier: "+ctx.getText());
      else
        throw new ParseException("Invalid literal parse node");
    }
    
    @Override
    public Expression visitFunctionCall(LanguageParser.FunctionCallContext ctx)
    {
      Operator op = null;
      
      try
      {
        op = Operators.of(ctx.funName.getText());
      }
      catch (IllegalArgumentException e)
      {
        throw new ParseException("Unknown function: "+ctx.funName.getText());
      }

      Expression[] args = ctx.expression().stream().map(this::visit).toArray(i -> new Expression[i]);
      
      return new Operation(op, args);
    }
    
    @Override
    public Expression visitTerminal(LanguageParser.TerminalContext ctx)
    {
      if (ctx.pexpression != null)
        return visit(ctx.pexpression);
      else if (ctx.functionCall() != null)
        return visit(ctx.functionCall());
      else if (ctx.literal() != null)
        return visit(ctx.literal());
      else
        throw new ParseException("Invalid terminal parse node");
    }
    
    @Override
    public Expression visitExpression(LanguageParser.ExpressionContext ctx)
    {      
      if (ctx.terminal() != null)
        return visit(ctx.terminal());
      else if (ctx.bop != null)
        //return new Operation(Operators.of(ctx.op.getText()), ctx.expression().stream().map(this::visit).toArray(i -> new Expression[i]));
        return new Operation(Operators.of(ctx.bop.getText()), new Expression[] { visit(ctx.left), visit(ctx.right) });
      else if (ctx.uop != null)
      {
        if (ctx.uop.getText().equals("-"))
          return new Operation(Operators.NEGATION, new Expression[] { visit(ctx.unary) });
        else
          return new Operation(Operators.of(ctx.uop.getText()), new Expression[] { visit(ctx.unary) });

      }
      else
        throw new ParseException("Invalid expression parse node");
    }
    
    @Override
    public Expression visitStart(LanguageParser.StartContext ctx)
    {
      //return new Root(visit(ctx.expression()));
      return visit(ctx.expression());
    }
  }
  
  private static Expression ofc(String input, boolean debug)
  {
    if (debug)
      System.out.println("Parsing "+input);
    
    CharStream stream = CharStreams.fromString(input);
    LanguageLexer lexer = new LanguageLexer(stream);
    CommonTokenStream tstream = new CommonTokenStream(lexer);
    
    if (debug)
    {
      tstream.fill();
      
      for (Token t : tstream.getTokens())
      {
        String symbolicName = LanguageLexer.VOCABULARY.getSymbolicName(t.getType());
        String literalName = LanguageLexer.VOCABULARY.getLiteralName(t.getType());
        System.out.printf("  %-20s '%s'\n",
                symbolicName == null ? literalName : symbolicName,
                t.getText().replace("\r", "\\r").replace("\n", "\\n").replace("\t", "\\t"));
      }
    }
    
    LanguageParser parser = new LanguageParser(tstream);
   
    
    LanguageParser.StartContext context = parser.start();
    
    
    return new Visitor().visit(context);
  }
  
  public static Expression of(String input)
  {
    return ofc(input, false);
  }
  
  public static Expression ofd(String input)
  {
    return ofc(input, true);
  }
}
