package com.github.jakz.jplot;

import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;
import org.jparsec.Terminals;

public class ExpressionParser
{  
  private final Terminals OPERATORS = Terminals.operators("+", "-", "*", "/", "(", ")");
  private final Parser<?> INTEGER_TOKENIZER = Terminals.DecimalLiteral.TOKENIZER;
  
  private final Parser<Void> IGNORED = Parsers.or(Scanners.JAVA_LINE_COMMENT, Scanners.JAVA_BLOCK_COMMENT, Scanners.WHITESPACES).skipMany();
  
  private final Parser<?> TOKENIZER = OPERATORS.tokenizer().cast().or(INTEGER_TOKENIZER);
  
  
  
  
  
  private final Parser<Double> NUMBER = Terminals.DecimalLiteral.PARSER.map(Double::valueOf);
  
  Parser<?> term(String... names) { return OPERATORS.token(names); }
  <T> Parser<T> op(String name, T value) { return term(name).retn(value); }
}
