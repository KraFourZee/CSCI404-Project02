 - Name and CSM ID of the student.

Kyle DiSandro

 - What programming language and what version of the compiler are used.

Java, I used OSX Terminal as well as git bash for windows 8.1, both worked

 - How the code is structured.

The code uses two classes, maxconnect4.java and GameBoard.java
GameBoard is generally unmodified from the code we were presented with, I added a getAdditionalWinningRowScore function that
allows the computer to make a row comparison and properly weight if it wants to go in that row or not.

maxconnect4 contains all of the arguement handling as well as minimax and minimaxrev used for the computer's.

 - How to run the code, including very specific compilation instructions, if compilation is needed. Instructions such as "compile using g++" are NOT considered specific.

can be compiled and ran the exact same as provided, run this is the command line.
javac maxconnect4.java GameBoard.java

Interactive
java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]
For example:
java maxconnect4 interactive input1.txt computer-next 7

One-Move
java maxconnect4 one-move [input_file] [output_file] [depth]
For example:
java maxconnect4 one-move output1.txt output2.txt 5

then to have them continue the game and 'battle'

java maxconnect4 one-move output2.txt output1.txt 5