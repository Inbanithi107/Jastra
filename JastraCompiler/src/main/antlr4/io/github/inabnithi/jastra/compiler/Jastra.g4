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
        | compareStatement
        | conditionalStatement
        | callStatement
        | returnStatement
        | returnVoidStatement;

storeStatement: STORE REGISTER value;
loadStatement: LOAD REGISTER;
arithmeticStatement: ADD | SUBTRACT | MULTIPLY | DIVIDE;
compareStatement: CMP_EQ | CMP_NE | CMP_LT | CMP_LE | CMP_GT | CMP_GE;
conditionalStatement :
                    IF COLON ifStatements+=statement* END
                    | IF COLON ifStatements+=statement* ELSE COLON elseStatements+=statement* END;
printStatement: PRINT;
callStatement: CALL INTEGER INTEGER;
returnStatement: RETURN INTEGER;
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
CMP_EQ: 'CMP_EQ';
CMP_NE: 'CMP_NE';
CMP_LT: 'CMP_LT';
CMP_LE: 'CMP_LE';
CMP_GT: 'CMP_GT';
CMP_GE: 'CMP_GE';
IF: 'JIZ' | 'JNZ';
ELSE: 'ELSE';
END: 'END';
STORE: 'STORE';
LOAD: 'LOAD';
CALL: 'CALL';
PRINT: 'PRINT';
RETURN: 'RETURN';
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