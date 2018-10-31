# CodeBreakers Games
### Openclassrooms - _Dev Java - project number 3_  
<!-- texte en commentaire -->   
<!-- texte en commentaire -->  
This application allows to play two games of code breaking:
<!-- -->  
<!-- -->  

- Codex  
- Master Mind  
<!-- texte en commentaire -->  

### Codex  
In this game the defender or CodeMaker creates a combination and the attacker or CodeBreaker must guess it.  
In order to do this the CodeBreaker submits a combination that will be compared to the one to be guessed and the CodeMaker will provide for each of the digits the following indications:
<!-- texte en commentaire -->  
<!--  -->  

- the number is above the one to guess
- the number is exact
- the number is below the one to guess
<!--  -->  
the game continues for a determined number of turns (default 12) after which the winner is proclaimed.
<!--  -->  
<!--  -->  
<!--  -->  

### Master Mind  

Differently than in the previous game, here the CodeMaker gives different indications:<!--  -->  
<!--  -->  


- the chiffres that are exacts and at the RIGHT place -> number of black pawns  
- the chiffres that are exacts but at the WRONG place -> number of white pawns  
<!--  -->  
In the same way than Codex, the game continues for a determined number of turns (default 12) after which the winner is proclaimed.  
<!--  -->  
<!--  -->  
<!--  -->  
<!--  -->  
<!--  -->  

## Fonctionnalities  

The implemented fonctionnalities are :  
<!-- -->  

For every game :  
  
- Human player attacks : the human player is the CodeBreaker  
- Human player defends : the human player is the CodeMaker   
- Duel mode : players play turn by turn CodeMaker and CodeBreaker  
- Debug mode : in order to verify the program, the combinations to guess are displayed & the computer can play against itself
<!-- -->  

## How to play  

This is a Maven project, developped in the IntelliJ IDEA Ultimate 2018.2.5 version IDE so, in order to execute it you can :  

- import the project in the IDE of your choice : here you have the source code in the `src` folder  
- execute the .jar file you can find in the `out` folder  
- download the source code on your machine and build the project with Maven

#### How to build this project with Maven  

- you need to install Maven on your computer  
- in the project root directory (you have `pom.xml` inside) type : `mvn package`  
- change the directory to `projectRoot/target` and type in `java -jar games-1.0-SNAPSHOT-jar-with-dependencies.jar <param1> <param2>`   

 
 `<param1>` and `<param2>` could be :  
  - "debug" - if you want to launch the debug mode  
  - a path to a configuration file that must be of the `*.properties` type

