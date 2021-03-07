package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String length=scanner.nextLine();

        if(!length.matches("\\d+")){
            System.out.println("Error: "+length+" isn't a valid number.");
            return;
        }

        int lengthVal=Integer.parseInt(length);
        if(lengthVal==0){
            System.out.println("Error : The length of length of the secret code should be bigger than 0");
        return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        int range=scanner.nextInt();
        if(range<lengthVal) { 
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " + range + " unique symbols.");
            return;}

        if(range>36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;}

        System.out.println("Okay, let's start a game!");
        String secretCode = codeGenerator(lengthVal,range);

        int bull=0;
        int count=1;
       while(bull!=secretCode.length()){
        System.out.println("Turn "+count+":");
        bull=guessTheNumber(secretCode);
        count++;}
       System.out.println("Congratulations! You guessed the secret code.");
    }
    
    private static int guessTheNumber(String secretCode) {
        Scanner scanner=new Scanner(System.in);
        String guess=scanner.next();
        int bull=0;
        int cow=0;

        for (int i = 0; i< secretCode.length(); i++){
            if(guess.contains(secretCode.charAt(i)+""))
                cow++;
            if(guess.charAt(i)== secretCode.charAt(i)){
                bull++;
                cow--;
            }
        }

       if(bull==0){
           if(cow==1)
               System.out.println("Grade: 1 cow");
           else System.out.println("Grade: "+cow+" cows");
       }
       else if(cow==0){
           if(bull==1)
               System.out.println("Grade: 1 bull");
           else System.out.println("Grade: "+bull+" bulls");
       }
       else if(cow==1){
           if(bull==1)
               System.out.println("Grade: 1 bull and 1 cow");
           else System.out.println("Grade: "+bull+" bulls and 1 cow");}
       else if(bull==1){
           if(cow==1)
               System.out.println("Grade: 1 bull and 1 cow");
           else
           System.out.println("Grade: 1 bull and "+cow+" cows");}
       else if(cow>1&&bull>1)
           System.out.println("Grade: "+bull+" bulls and "+cow+" cows");
       else
            System.out.println("Grade: None.");
        return bull;
    }

    
    private static String codeGenerator(int lengthVal, int range) {
        Random random = new Random();

        String chars="0123456789abcdefghigklmnopqrstuvwxyz";
        StringBuilder code=new StringBuilder(lengthVal);

            while(code.length() <lengthVal) { 
                char c= chars.charAt(random.nextInt(range-1));
                boolean used=false;
                for(int j = 0; j< code.length(); j++){ 
                    if (c==code.charAt(j)) 
                        used=true; }
                if(used) 
                    continue;
                else 
                    code.append(c); }
            
        StringBuilder stars = new StringBuilder();
        stars.append("*".repeat(Math.max(0, lengthVal)));

        if (range<11)
        System.out.println(stars+" (0-"+chars.charAt(range-1)+")");
        else
            System.out.println(stars+" (0-9, a-"+chars.charAt(range-1)+")");
        return code.toString();
    }
}