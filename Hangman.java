
/**
 * Hangman.java  
 *
 * @author - Vivian Nguyen
 * @author - Created 1-2-22
 *
 * 
 *
 */ 
import java.util.Scanner;
import java.util.ArrayList;  

/**
 * Hangman
 * Plays a hangman game.
 */
public class Hangman
{   

    private String keyword; 
    private int tries;
    public static Scanner kb = new Scanner(System.in);
    private ArrayList <String> selectedLetters= new ArrayList<String>();
    //a set of random puzzle words
    private String[] words={"Roses are red, violets are blue", "A fish out of water", "Peace begins with a smile", "Who am I?", "I need the toilet",
            "Frazzled", "You only live once", "The future is bright", "Xylophone", "Step in someone else's shoes", "Work hard, play well", "Stronghold", 
            "Larynx", "Pencil", "Find me!", "Hello World!", "Wristwatch", "A man after men", "Catch your death of cold", "Death metal", "Dressed to impress",
            "Fate worse than death", "You got to man up", "It's raining, it's pouring", "Kill time until you are dead", "Ignorance is Strength", 
            "War is peace", "I understand HOW, I do not understand why", "Throw off the cent", "Never lose your soul", "Laughter is contagious",
            "Space and time", "Perplexed", "Bizz Buzz", "Hangman", "Zigzagging", "Sky", "Jungle gym", "You are what you eat", "Goodnight vienna", 
            "Accidentally on purpose", "Action man", "Blood, sweat, and tears", "Dark horse", "Easy as pie", "Hard as a rock","Mouse trap", "Try until you can't anymore",
            "Head in the clouds", "Java Coffee", "Computer Science", "Cat in a bag", "The grade is not everything", "I'm going to lose", "Alone time", "Sunsets are beautiful",
            "System.out.println", "John Locke", "Thomas Hobbes", "George Washington", "Algebra", "School is cool", "Bye Felicia"};
    private boolean play;
    private boolean isOnePlayer;
    private int play1Score;
    private int play2Score;
    String selection;
    public Hangman(){
        selection="";
    }

    public void playGame(){
        play=true;
        selectedLetters=new ArrayList <String>();
        boolean selectPlayMode=false;
        System.out.println("Use letters to make selections");
        System.out.println("Select Play Mode: A. One player B. Two players");
        String players=kb.nextLine();
        isOnePlayer=players.equalsIgnoreCase("A");
        while(!selectPlayMode){
            if (!isOnePlayer){
                System.out.println("Select a game: A. Competitive B. Choose-A-Word C. Help,tell me more!");
                selection=kb.nextLine();
                if (selection.equalsIgnoreCase("A")){
                    selectPlayMode=true;
                    int num=(int)(Math.random()*words.length);
                    keyword=words[num];
                    play1Score=0;
                    play2Score=0;
                    mainGameComp();
                }
                else if (selection.equalsIgnoreCase("B")){
                    selectPlayMode=true;
                    boolean valid=false;
                    System.out.println("Player 2: Enter a keyword");
                    keyword=kb.nextLine();
                    System.out.println("Keyword has been set to: "+ keyword);
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
                        "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("Grab a friend!");
                    mainGame();
                }
                else if (selection.equalsIgnoreCase("C")){
                    System.out.println("Competitive:\nSee who can select the most correct letters! You get a point for every letter present in the puzzle");
                    System.out.println("guessed correctly. Your turn goes to the other player once you guess the wrong letter");
                    System.out.println("If you guess the last letter correctly, you earn 2 extra points! Whoever has the most points wins!");
                    System.out.println("Choose-A-Word:\nOne player chooses a keyword, and the other tries to guess it!");
                }
            } else {
                selectPlayMode=true;
                int num=(int)(Math.random()*words.length);
                keyword=words[num];
                mainGame();

            }
        }

    }

    public void mainGame(){
        boolean selected=false;
        while (!selected){
            System.out.println("Select Difficulty: A. Easy, B. Med, C. Hard");
            String tryStr=kb.nextLine();
            if (tryStr.equalsIgnoreCase("A")){
                tries=7;
                System.out.println("Difficulty has been set to easy");
                selected=true;
            } else if (tryStr.equalsIgnoreCase("B")){
                tries=5;
                System.out.println("Difficulty has been set to medium");
                selected=true;
            } else if (tryStr.equalsIgnoreCase("C")){
                tries=3;
                System.out.println("Difficulty has been set to hard");
                selected=true;
            } else {
                System.out.println("Please select a valid difficulty");
            }
        }
        System.out.println("Type \"bye\" to leave!");
        System.out.println("A hint will cost 2 tries! You cannot obtain hints with 3 or less letters remaining!");
        while (play && tries!=0){
            System.out.println(printout());
            System.out.println("Guess a letter! You have "+ tries + " wrong tries left. Type \"hint\" to get a hint.");
            String guess=kb.nextLine();
            String lowerGuess=guess.toLowerCase();
            String upperGuess=guess.toUpperCase();
            String alphabet= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            if (guess.equals("bye")){
                break;
            }
            else if (guess.equalsIgnoreCase("hint")){
                getHint();
            }
            else if (guess.length()>1){
                System.out.println("Guess should only be 1 character");
            } 
            else if (guess.equals("")){
                System.out.println("Please enter a character");
            }
            else if (selectedLetters.contains(guess)){
                System.out.println("You already guessed " + guess);
            }
            else if (alphabet.indexOf(guess)>=0 && (keyword.indexOf(lowerGuess)>=0 || keyword.indexOf(upperGuess)>=0)) {
                System.out.println(guess.toUpperCase() + " is part of the keyword");
                selectedLetters.add(upperGuess);
                selectedLetters.add(lowerGuess);
            }
            else if (alphabet.indexOf(guess)>=0){
                System.out.println(guess.toUpperCase() + " is not part of the keyword");
                selectedLetters.add(lowerGuess);
                selectedLetters.add(upperGuess);
                tries--;
            }
            else {
                System.out.println("Please put a valid single character");
            }

            if ((tries==0) && (selection.equalsIgnoreCase("B"))){
                System.out.println("Sorry, Player 1 loses! The keyword is: " + keyword);
                play=false;
            }
            else if (printout().equalsIgnoreCase(keyword) && selection.equalsIgnoreCase("B")){
                System.out.println("Congrats! Player 1 wins! The word was: "+ keyword);
                play=false;
            }
            else if (tries==0){
                System.out.println("Sorry, you lose! The keyword is: "+ keyword);
                play=false;
            }
            else if (printout().equalsIgnoreCase(keyword)){
                System.out.println("Congrats! You win! the word was: "+ keyword);
                play=false;
            }
        }
    }

    public void mainGameComp(){
        //DO THIS METHOD WHEN YOU GET BACK
        int numTurns=0;
        boolean selected=false;
        System.out.println("Type \"bye\" to leave!");
        while (!selected){
            System.out.println("Select Difficulty: Easy, Med, Hard");
            String tryStr=kb.nextLine();
            if (tryStr.equalsIgnoreCase("easy")){
                tries=8;
                System.out.println("Difficulty has been set to easy");
                selected=true;
            } else if (tryStr.equalsIgnoreCase("med")){
                tries=6;
                System.out.println("Difficulty has been set to medium");
                selected=true;
            } else if (tryStr.equalsIgnoreCase("hard")){
                tries=4;
                System.out.println("Difficulty has been set to hard");
                selected=true;
            } else {
                System.out.println("Please select a valid difficulty");
            }
        }

        while (play){
            System.out.println(printout());
            if (numTurns%2==0){
                System.out.println("Guess a letter player 1! You have "+ tries + " wrong tries left");
            }
            else {
                System.out.println("Guess a letter player 2! You have "+ tries + " wrong tries left");
            }
            String guess=kb.nextLine();
            String lowerGuess=guess.toLowerCase();
            String upperGuess=guess.toUpperCase();
            String alphabet= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            if (guess.equals("bye")){
                break;
            }
            else if (guess.length()>1){
                System.out.println("Guess should only be 1 character");
            } 
            else if (guess.equals("")){
                System.out.println("Please enter a character");
            }
            else if (selectedLetters.contains(guess)){
                System.out.println("You already guessed " + guess);
            }
            else if (alphabet.indexOf(guess)>=0 && (keyword.indexOf(lowerGuess)>=0 || keyword.indexOf(upperGuess)>=0)) {
                System.out.println(guess.toUpperCase() + " is part of the keyword");
                selectedLetters.add(upperGuess);
                selectedLetters.add(lowerGuess);
                if (numTurns%2==0){
                    for (int i=0; i<keyword.length(); i++){
                        if (keyword.substring(i,i+1).equals(guess)){
                            play1Score++;
                        }
                    }
                }
                else {
                    for (int i=0; i<keyword.length(); i++){
                        if (keyword.substring(i,i+1).equals(guess)){
                            play2Score++;
                        }
                    }
                }
            }
            else if (alphabet.indexOf(guess)>=0){
                System.out.println(guess.toUpperCase() + " is not part of the keyword");
                selectedLetters.add(lowerGuess);
                selectedLetters.add(upperGuess);
                tries--;
                numTurns++;
            }
            else {
                System.out.println("Please put a valid single character");
            }

            if (tries==0){
                System.out.println("Sorry, you didn't get the word");
                if (play1Score>play2Score){
                    System.out.println("Player 1 wins!");
                }
                else if (play1Score<play2Score){
                    System.out.println("Player 2 wins!");
                }
                else {
                    System.out.println("It's a tie");
                }
                System.out.println("Player 1 score: "+ play1Score + " Player 2 score: "+ play2Score);
                System.out.println("The keyword is: " + keyword);
                play=false;
            }
            else if (printout().equalsIgnoreCase(keyword)){
                if (numTurns%2==0){
                    play1Score+=2;
                }
                else {
                    play2Score+=2;
                }
                System.out.println("Congrats! The word was: "+ keyword);
                if (play1Score>play2Score){
                    System.out.println("Player 1 wins!");
                }
                else if (play1Score<play2Score){
                    System.out.println("Player 2 wins!");
                }
                else {
                    System.out.println("It's a tie");
                }
                System.out.println("Player 1 score: "+ play1Score + " Player 2 score: "+ play2Score);
                System.out.println("The keyword is: " + keyword);
                play=false;
            }

        }  
    }

    public boolean keepPlaying(){
        System.out.println("Would you like to play again? Type \"yes\" to play again.");
        String response=kb.nextLine();
        if (response.equalsIgnoreCase("yes")){
            return true;
        }
        return false;
    }

    public String printout(){
        String result="";
        for (int i=0; i<keyword.length(); i++){
            if (selectedLetters.contains(keyword.substring(i,i+1))){
                result+=keyword.substring(i,i+1);
            } else if (keyword.indexOf(" ", i)==i){
                result+=" ";
            } else if (keyword.indexOf("'",i)==i || keyword.indexOf("\"",i)==i || keyword.indexOf(".",i)==i || keyword.indexOf("!",i)==i
            || keyword.indexOf("?",i)==i || keyword.indexOf("'",i)==i || keyword.indexOf("$",i)==i){
                result+=keyword.substring(i,i+1);
            }
            else {
                result+="_";
            }
        }
        return result;
    }

    public static void credits(){
        System.out.println("Credits:");
        System.out.println("Coder: Vivian Nguyen");
        System.out.println("Tester: Vivian Nguyen");
        System.out.println("Idea made by: Vivian Nguyen");
        System.out.println("Have fun! 2022 FunTime Co.");
    }

    public void getHint(){
        if (tries<=2){
            System.out.println("Need at least 3 tries for hint");
        }
        else{
            ArrayList<String> correctLetters=new ArrayList<String>();
            for (int i=0; i<keyword.length(); i++){
                String lower=keyword.substring(i,i+1).toLowerCase();
                if (!correctLetters.contains(lower)){
                    correctLetters.add(lower);
                }
            }
            int corrLettCount=0;
            ArrayList<String> correctLettersSelected=new ArrayList<String>();
            for (String s: correctLetters){
                corrLettCount++;
                String lower=s.toLowerCase();
                if (selectedLetters.contains(lower)&& !correctLettersSelected.contains(lower)){
                    correctLettersSelected.add(lower);
                }
            }
           
            if ((correctLettersSelected.size())+3>=correctLetters.size()){
                System.out.println("No hints for 3 letters remaining");
            }
            else{
                int randIndex=(int)(Math.random()*keyword.length());
                String lett=keyword.substring(randIndex,randIndex+1);
                boolean selected=false;
                for (String s: selectedLetters){
                    if (s.equals(lett)|| lett.equals(" ")){
                        selected=true;
                    }
                }
                if (!selected){
                    selectedLetters.add(lett);
                    System.out.println(lett.toUpperCase()+ " has been added");
                    tries-=2;
                }
                else {
                    getHint();
                } 
            }
        }
    }

    public static void main(String[] args)
    {   
        Hangman game= new Hangman();
        boolean playGame=true;
        System.out.println("Hello! Welcome to Hangman: Edition V!");
        while (playGame){
            game.playGame();
            playGame=game.keepPlaying();
        }
        System.out.println("Thanks for playing!");
        Hangman.credits();

    }
}
