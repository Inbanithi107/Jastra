grammar Jastra;

program :
        functionDeclration* EOF;

functionDeclration :
        FUN ID INTEGER COLON statement*;

statement :
        storeStatement
        | loadStatement
        | printStatement
        | arithmeticStatement
        | returnVoidStatement;

storeStatement: STORE REGISTER value;
loadStatement: LOAD REGISTER;
arithmeticStatement: ADD | SUBTRACT | MULTIPLY | DIVIDE;
printStatement: PRINT;
returnVoidStatement: RETURN_VOID;

value :
        INTEGER #IntgerLiteral
        | LONG #LongLiteral
        | FLOAT #FloatLiteral
        | DOUBLE #DoubleLiteral
        | CHARACTER #CharLiteral
        | STRING #StringLiteral
        | BOOLEAN # BooleanLiteral;


FUN: 'FUN';
STORE: 'STORE';
LOAD: 'LOAD';
PRINT: 'PRINT';
RETURN_VOID: 'RETURN_VOID';
REGISTER: 'R' ([0-9]| [1-9][0-9]| '1'[0-9][0-9]| '2'[0-4][0-9]| '25'[0-5]);
BOOLEAN : 'true'| 'false';
DOUBLE: [0-9]+ '.' [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+ 'f';
LONG: [0-9]+ 'L';
INTEGER: [0-9]+;
CHARACTER: '\'' . '\'';
ADD: 'ADD';
SUBTRACT: 'SUB';
MULTIPLY: 'MUL';
DIVIDE: 'DIV';
STRING: '"' .*? '"';
COLON: ':';
ID: [a-zA-Z_][a-zA-Z0-9_]*;
WS : [ \t\r\n]+ -> skip;