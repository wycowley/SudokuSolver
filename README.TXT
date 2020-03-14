This is an automated version of sudoku.

To make it start running, the user can either press space or press the button below.
If the user wants to edit the sudoku seen, they can delete numbers and change them as they please when the program is not solving.

All the colors while solving mean specific things.
Black means that it is one of the original given values.
Blue means that it used the programmed logic to find out what the value was.
Pink means it randomly guessed that number based on what it could possibly be.
Green means it used the programmed logic to find out what the value was, but this was done after a guess has been made.

Basic rundown of how it works:
Every single cycle it notes down every specific number that is possible.  This is done in the NoteNumbers class
If there is a row or column of noted numbers (like the fours below), it clears the rest of that row or column of those noted numbers.  This is done in the DeletionRows class
4 4 4 (these are noted numbers, not filled in for real)
5 2 2
5 5 2

Then, it finds if there is only one possible place for that number in the 3x3 box.  If so, it fills it in.  This is done in the AddInUsingNotes class
If none are filled in in the entire puzzle, then it sends a signal to run the guess class
The guess class takes one of the positions on the puzzle and guesses what it could be, to prevent it from getting stuck.
 - If it gets stuck after a number has been guessed, then the guess class chooses a different number until it is possibly solved


