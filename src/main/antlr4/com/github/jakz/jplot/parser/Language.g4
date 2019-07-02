grammar Language;

fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];

start : 
  expression
;

expression
  : left=expression op=('+' | '-') right=expression 
  | left=expression op=('*' | '/') right=expression 
  | <assoc=right> left=expression op='^' right=expression
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