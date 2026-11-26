grammar Jastra;

program :
        constDeclration? functionDeclration* EOF;

functionDeclration :
        FUN ID INTEGER COLON statement*;

constDeclration: 'GLOBALS' '{' constants* '}';

constants: ID '=' (STRING|INTEGER|FLOAT) ';';

statement :
        storeStatement
        | popAndStoreinRegisterStatement
        | loadStatement
        | loadFromConstantStatement
        | pushStatement
        | popStatement
        | printStatement
        | arithmeticStatement
        | compareStatement
        | conditionalStatement
        | loopStatement
        | callStatement
        | callExternalStatement
        | returnStatement
        | returnVoidStatement;

storeStatement: STORE REGISTER value;
popAndStoreinRegisterStatement: PSR REGISTER;
loadStatement: LOAD REGISTER;
loadFromConstantStatement: LFC ID;
pushStatement: PUSH value;
popStatement: POP;
arithmeticStatement: ADD | SUBTRACT | MULTIPLY | DIVIDE;
compareStatement: CMP_EQ | CMP_NE | CMP_LT | CMP_LE | CMP_GT | CMP_GE;
conditionalStatement :
                    IF COLON ifStatements+=statement* END
                    | IF COLON ifStatements+=statement* ELSE COLON elseStatements+=statement* END;
loopStatement :
            LOOP COLON
                (('INIT') COLON initStatements+=statement*)?
                ('COND') COLON condStatements+=statement*
                ('BODY') COLON bodyStatements+=statement*
                (('UPD') COLON updateStatemts+=statement*) ?
                END;
printStatement: PRINT;
callStatement: CALL ID INTEGER;
callExternalStatement: CALLE ID ID INTEGER;
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
LOOP: 'LOOP';
END: 'END';
STORE: 'STORE';
PSR: 'PSR';
LOAD: 'LOAD';
LFC: 'LFC';
PUSH: 'PUSH';
POP: 'POP';
CALL: 'CALL';
CALLE: 'CALLE';
PRINT: 'PRINT';
RETURN: 'RETURN';
RETURN_VOID: 'RETURN_VOID';
REGISTER: 'R' ([0-9]| [1-9][0-9]| '1'[0-9][0-9]| '2'[0-4][0-9]| '25'[0-5]);
BOOLEAN : 'true'| 'false';
DOUBLE: [0-9]+ '.' [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+ 'F';
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