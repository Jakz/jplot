grammar Language;

fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];

start : 
  expression
;

expression
  : expression (op=('+' | '-') expression )
  | expression (op=('*' | '/') expression )
  | terminal
;

terminal
  : literal
  | '(' expression ')'
;

literal : 
  identifier | 
  integer 
;

identifier : WORD ;
integer : INTEGER ;

INTEGER : DIGIT+ ;
WORD: LETTER+ ;

WS : 
  . -> skip
;