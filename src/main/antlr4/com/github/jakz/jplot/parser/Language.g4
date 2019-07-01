grammar Language;

fragment DIGIT : [0-9];

start : 
  integer
;


integer : INTEGER
;

INTEGER : DIGIT+
;

WS : 
  . -> skip
;