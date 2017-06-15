# BFS
BFS: Breadth First Search package.
Purpose: to design and implement a general use BFS package: the current version includes a program to check that it works; the design uses an abstract class and the types are generics. 

There are two branches: master and simple: the simple one is for a design that does not use a State class but only an interface Node as inner type of BFS.

Simple: it has only one class, the BFS class, with its inner type, the Node interface; also the branch includes a Node implementation, BoggleNode, and a Boggle program. It needs a dictionary, which begins with the sequence of letters: command line: cat dict.txt | java Boggle.
