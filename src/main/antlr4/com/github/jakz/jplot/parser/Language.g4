grammar Language;

fragment DIGIT : [0-9] ;
fragment LETTER : [a-zA-Z] ;
fragment GREEK_LETTER : [\u0391-\u03A9\u03B1-\u03c9] ;

start : 
  expression
;

expression
  : left=expression bop=('+' | '-') right=expression 
  | left=expression bop=('*' | '/') right=expression 
  | <assoc=right> left=expression bop='^' right=expression
  | uop='-' unary=expression
  | terminal
;

terminal
  : literal
  | functionCall
  | '(' pexpression=expression ')'
;

functionCall:
  funName=IDENTIFIER '(' args=expression* ')'
;


literal  
  : IDENTIFIER 
  | WRONG_IDENTIFIER 
  | integer 
;

integer : INTEGER ;

IDENTIFIER : (LETTER|GREEK_LETTER) (LETTER|GREEK_LETTER|DIGIT|'_')* ;
INTEGER : DIGIT+ ;

WRONG_IDENTIFIER : (LETTER|GREEK_LETTER|DIGIT|'_')* ;

WS : . -> skip
;