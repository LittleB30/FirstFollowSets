# FirstFollowSets
Write a java program named printFF.java that will print the input grammar, the set of non-terminal symbols, the set of terminal symbol, and the first set and follow set of every non-terminal symbol. Arrange your program output in the following format: 

java printFF G4.txt

S ::= asg
S ::= if C then S E
C ::= bool
E ::= lambda
E ::= else S

Non-terminal symbols: S C E
Terminal symbols: else asg if bool then

First sets:
S: asg if
C: bool
E: lambda else

Follow sets:
S: $ else
C: then
E: $ else
