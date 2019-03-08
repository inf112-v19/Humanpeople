# Obligatorisk oppgave 3



## Part 1
### How do the roles of the team work? Do you need to update who is teamlead or customer contact? Do you need other roles? Write down some points about what the different roles actually mean to you.
 - No more roles are needed
 - Customer contact - Expert in RoboRally. Responsible for talking to the customer (Siv) whenever we need anything
 - Teamlead - Main responsbility to have the mandatory assignments completed within the deadline. Has last say in code review before commit to master. Has the last word in regarding what to prioritize in iterations. Has responsibility to merge branches with master.
### Are there any experiences either team-wise or regarding project methodology that is worth mentioning? Does the team think the choices you've made are good? If not, what can you do differently to improve the way the team works?
 - Keeping everyone up to speed on what code has been written has worked well for people's general understanding of the application
 - Hard to maintain project board. Will work more on this for next iteration.
 - For further iterations we will make an effort to have better communication and be better at updating and maintaining the project board. Other than that, all group members are pleased with the choices that we have taken.


### How are the group dynamics? 
 - Generally the group members are kind to each-other, openminded, and willing to listen to eachothers opinion. The groupmembers do the tasks that are asked of them without complaining. 
### How does communication work for you?
 - Had an incident where three group members had worked on the same implementation. This was due to faulty use of project board, which we have gotten better at towards the end of the iteration and will continue to do heading forward.
### Make a brief retrospective in which you consider what you have done so far and what can be improved. This is about project structure, not code. You can of course discuss code, but this is not about error correction, but about how to work and communicate. 
 - At the start of the iteration, first meeting after oblig 1, we started creating a plan for what to implement this iteration. After having agreed upon the different implementations we added tasks to the project board as we saw fit. The tasks were for the individual team members to choose from, but we also discussed in the meetings who could take what task to some degree. 
   After the agreed upon tasks were completed we had no more to do, and therefore  team members added their own tasks without much group discussion, only based on what has been talked about on meetings, but not properly agreed upon how to do. This led to some conflicting implementation and one instance where 3 members finished the same task.
 - Later in the iteration we got better at using the project board to the agreed plans made at meetings.
 - The group are happy with the roles given at the start of the project and we feel that no changes needs to be made to the group structure.
 - Testing has also been a struggle. Often tests have been an afterthought and not been done as we had planned. Optimally we would have written all tests after the respective implementation had been completed, and not closer to the end of the iteration. 
### During evaluation, every member's contribution to the code base will be emphasized. If there is a big difference in who commits, you must enclose a brief explanation as to why it is like that. Remember to commit everything. Including design files. 
 - Some of the members have done more than others.
 - Teamlead wrote the mandatory assignment while it was discussed during a meeting. Hence a lot more lines have been commited by him.
### Report from meetings since the previous delivery must also be delivered. 


### From the retrospective agree and write down three things (max), that the team will follow up during the next sprint
 - Project board
 - Communication
 - Better at task deligation
## Part 2
### Clarification of the customer's requirements. What are the actual tasks?
 - Player movement
   * Create player class
   * Position and Direction class
   * move player on the board
   * Create GameObjects for walls, and players
   * Create a Grid grid to keep track of where different game objects are at all times
   * solve collision between different GameObjects 
 - Possible to get every type of movement card
   * Create a Program card deck-class containing every program card type with methods for dealing a given number of random cards to a given player.
 - Deal nine cards to player
   * Create a PlayerDeck class which contains a list of 9 cards to choose from and a list of 5 cards (the cards which have been choosen)
   * Deal cards from ProgramCardDeck class to every player
 - Run program from chosen cards
   * The player character moves based on what cards has been placed in which order in the programboard
 - Choose five cards (confirm choice/”ready to play”-option)
   * Create a UI which lets the players choose 5 cards and confirm
   * Made a new screen(CardPickingScreen) with 9 cards which lets player choose cards back and forth from hand
   * Had to make some changes to current methods in different classes to make it happen
 - If a robot walks off the board, it is destroyed and respawned at backup position
   * If the character walks off the board, it dies.
   * if the character dies it respawns at last backup.
### How do the team Prioritize these tasks?
 - Get movement for player
 - Collision into walls
 - Create cards and deck
 - Movement based on cards
 - Collision between players
### If changes are made in order from what is given by the customer, why is this done?
 - The team has discarded most of the proposed requirements in favour of a good foundation of the system, with focus on player movement, for easier and better further development. As of now; the movement system is closing in on perfect. With collisions, walls, a main menu for easy access to several options, an gamedev screen distached from the main game and movement through beautifully designed program cards.
### How will you verify that the requirements are met? (What are the acceptance criteria?)
 - Each task when completed by a team member has been brought to the meeting for review. If each team member is happy with the implementation, the task is seen as finished. If there is a disagreement, the teamlead has final say.
### Update what your priorities are, how far you have come and what you have done since last time. 
 - We have completed all of our priorities from the first iteration (implement board, a player tile, basic GUI and a few inputs). 
 - Our updated list of priorities for this iteration has been:
  * Implement player movement
  * Implement collision with walls and other players
  * Implement a menu screen with options for testing
  * Implement a card class for dealing and choosing cards
  * Get a good test coverage

### The list of requirements is long, but it is not necessary to deliver on all requirements if it is not realistic. It is more important that the tasks that you've executed are of high quality. Regard subproblem 4: "Completed tasks must be finished." 
## Part 3
### You need to document how the project builds, tests, and runs such that it's easy to test the code. During evaluation, the code will also be user tested. 
 - When the program is run (Main class) a Menu screen will open up. To start the game press “Play” in the middle of the screen. This will open up a game map screen. To get players to move around based on a random selection of cards, press “Space”. 
 - Manual tests are done by selecting the Test screen button on the main menu. Instructions on how to run these tests are found in the projects test folder.
 - Code tests are found in the projects test folder and run without any pre-requirements
### Document how the tests should be run, as well. 
### Code quality and test coverage are emphasized. Note that the tests you write must be used in the product.
### Completed tasks must be finished.
### Deliver class diagrams. (If there are many classes, make class diagrams for the most important ones.) 
 - Ref. picture file in the repository
