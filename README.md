# My Personal Project Proposal

The application I want to make is a short and fast game. It will be challenging and random. Similar in concept to a game like flappy bird but gameplay wise much different. The people who play this game will either be looking for a short and fun experience, or a challenging competition. This project is of interest to me, because games I enjoy games like these. I also believe that while the concept is simple there are numerous ways of creating complexity and depth. 



## Some of the things a user should be able to do
- play the game
- check their best Score(s)
- be able to play different difficulties
- See the full leaderboard



## User Stories
- As a user, I want to check my best score
- As a user, I want to successfully play the game
- As a user, I want to be able to change the difficulty
- As a user, I want to add my Scores to a leaderboard

## Phase 2 User Stories
- As a user, I want to save the entire leaderboard and difficulty to a file
- As a user, I want to load a saved leaderboard or difficulty



## Rules
during the game there will be 2 kinds of objects, spikes and portals.
You must input the index you wish to avoiding spikes along the way. The more portals you pass the more points you get.
Beware that the number of spikes will increase as you gain more score.
Also to note, that if you fail to input an index in time, the portal will close, and you will die.
If you also input an invalid index then you also die.


#Phase 4: Task 2
Fri Nov 26 11:43:10 PST 2021 Wiped the board
Fri Nov 26 11:43:21 PST 2021 Added Score: fred 3
Fri Nov 26 11:43:34 PST 2021 Added Score: harry 8
Fri Nov 26 11:43:38 PST 2021 Saved Score: harry 8
Fri Nov 26 11:43:38 PST 2021 Saved Score: fred 3
Fri Nov 26 11:43:55 PST 2021 Added Score: joe 0
Fri Nov 26 11:44:07 PST 2021 Added Score: george 15 50
Fri Nov 26 11:44:11 PST 2021 Wiped the board
Fri Nov 26 11:44:11 PST 2021 Added Score: harry 8
Fri Nov 26 11:44:11 PST 2021 Added Score: fred 3

Currently there is no good way to log the loaded of a new leaderboard. As such I have implemented a "wiping" logging whenever a new leaderboard is created. Since the code always only references the new leaderboard I felt this compromise would be acceptable.
 In hindsight It would have been better to implement a "remove all" method within leaderboard to allow for more accurate logging.

#Phase 4: Task 3

I'm overall pretty happy with the structuring of the project however there is a few things I would change

- Make a more pretty UI
- Split game app into subclasses. It currently does too much. (ui generation AND functionality)
- Make only one json reader/writer vs 2
- Possibly combine buttons and tools together. Currently there is a lot of duplication of button code.
- Never construct more than one leaderboard, instead, just alter the original.

