### How to run?
+ Find Application.java, run main(). 
+ You can change the player number if you want more players to join in.

### Design thinking
+ The overall abstraction is a game which contains 54 cards, 1 card sender and N players.
+ Consider each player and card sender as an independent person, running in their own thread.
+ Card sender will deal with cards to each player and then starts a new round.
+ Each player makes their own decision concurrently. The round ends when each one of them ends.
+ If there is no winner, the card sender starts a new round.
