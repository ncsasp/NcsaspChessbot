# NcsaspChessbot
Hi there! I'm making a chessbot in java for my Final Project of my AP Computer Science Class. This has been really fun to work on!

# Things to Know:
Download Chess.jar and Chess.bat, put them in the same folder, and run Chess.bat. 
Contents of Chess.bat:
  chcp 65001
  cls
  @java -jar Chess.jar

User input must be done in Proper Algebraic Chess Notation. (https://en.wikipedia.org/wiki/Algebraic_notation_(chess))
Castling as a player currently only works as White, and you can only short castle, but it works! For now. (Type "0-0")

# Features:
- Visualized Board
- Rules of Chess
- Plays Random Chess Games
- Unicode Chess Pieces
- Smart Chessbot
- User Input (Play against the chessbots or another player)
- Castling (But only sometimes)
- Algebraic Chess Notation Move Log

# Planned Features:
- Chessbot looking 3-4 moves ahead using A-B Pruning
- Finish Castling

# Known Bugs:
- Game is Over message repeated during some games
- Move is Invalid message repeated during some games
- Castling only works with Short Castling as White. (It was a debug feature, but I left it in for now)
  
Hope you all enjoy!
