package com.github.jakz.jplot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  BaseOperatorParsingTests.class,
  FreeOfMetaOperatorTest.class,
  FunctionCallParsingTests.class,
  MathFunctionTests.class,
  SimplificationTests.class,
  SubstituteSubtreeTransformerTest.class,
  TerminalParsingTests.class,
})
public class TestSuite
{
  
}