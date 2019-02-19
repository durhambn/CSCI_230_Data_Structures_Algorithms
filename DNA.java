package edu.cofc.csci230;


import java.util.Scanner;
/**
 * 
 * Brandi Durham
 * 
 * DNA class: user imputs an upper and lower DNA strand, code checks for if
 * user typed anything, if the imputed strand is correct, and if the two strands correctly
 * match. If the strands don't match, the program changes the letters to make the strands
 * match. 
 * 
 */
public class DNA
{
  public static void main(String[] args) 
  {
       boolean blankLine = false;
       Scanner keyboard = new Scanner(System.in);
       System.out.print("Enter top strand of DNA: ");
       String topDNA = "";
       topDNA = keyboard.nextLine();
       if( topDNA.isEmpty())
       blankLine = true;
       System.out.print("Enter lower strand of DNA: ");
       String lowDNA = "";
       lowDNA = keyboard.nextLine();
       if (lowDNA.isEmpty())
       blankLine = true;
       topDNA = topDNA.toUpperCase();
       lowDNA = lowDNA.toUpperCase();
       int i = 0;
       int j = 0;
       int m = 0;
       boolean correctLetters = true;
       boolean match = true;
       //System.out.println("Top: " + topDNA);
       //System.out.println("Lower: " + lowDNA);
       i=0;
       if (blankLine == false){
       while(i < topDNA.length() && i < lowDNA.length() && correctLetters != false)
       {
           
           if((topDNA.charAt(i) == 'A' || topDNA.charAt(i) == 'T' || topDNA.charAt(i) =='G' || topDNA.charAt(i) == 'C')&& (lowDNA.charAt(i) == 'A' || lowDNA.charAt(i) == 'T' || lowDNA.charAt(i) == 'G' || lowDNA.charAt(i) == 'C'))
                {
                    correctLetters = true;
                    i = i + 1;
                    //System.out.println("I: " + i);
                }
            
           else
                correctLetters = false;
        }
       while(j< topDNA.length() && j< lowDNA.length() && match == true)
       {
           //System.out.println(j);
           if(topDNA.charAt(j) == ('A'))
                if (lowDNA.charAt(j) == ('T'))
                match = true;
                else 
                match = false;
           else if(topDNA.charAt(j) == ('T'))
               if (lowDNA.charAt(j) == ('A'))
                    match = true;
                    else 
                    match = false;
           else if(topDNA.charAt(j) == ('G'))
               if (lowDNA.charAt(j) == ('C'))
                    match = true;
                    else 
                    match = false;
           else if(topDNA.charAt(j) == ('C'))
               if (lowDNA.charAt(j) == ('G'))
                    match = true;
                    else 
                    match = false;
           else
                match = false;
           j++;
        }
    }
       if (blankLine == true)
       {
           System.out.println("The entered upper strand or lower strand is not defined. \nExiting program.");
        }
       else if (topDNA.length() != lowDNA.length())
       {
           System.out.println("The entered upper and lower strands \ndo not have the same number of chemical bases. \nExiting program.");
        }
       else if (correctLetters == false)
       {
           System.out.println("The entered upper and lower strands \nmust only contain combinations of A, G, C, or T. \nExiting program.");
        }
        else if(match == true)
        {
            System.out.println("The entered double-strnd DNA pattern is correct. \nExiting program.");
        }
        else if(match == false)//fix lower stand to match with top strand
        //A-T, G-C
        {
            System.out.println("The entered double-strand DNA pattern had base-pair \nerrors that have been corrected: ");
            m = 0;
            String newDNA = "";
            while (m < topDNA.length()){
            if(topDNA.charAt(m) == 'A'){
                if(lowDNA.charAt(m) != 'T'){
                newDNA = lowDNA.substring(0,m) + 't' + lowDNA.substring(m+1);
                m++;
                lowDNA = newDNA;
                }
                else
                m++;
            }
            else if(topDNA.charAt(m) == 'T'){
                if(lowDNA.charAt(m) != 'A'){
                newDNA = lowDNA.substring(0,m) + 'a' + lowDNA.substring(m+1);
                m++;
                lowDNA = newDNA;
                }
                else
                m++;
            }
            else if(topDNA.charAt(m) == 'G'){
                if(lowDNA.charAt(m) != 'C'){
                newDNA = lowDNA.substring(0,m) + 'c' + lowDNA.substring(m+1);
                m++;
                lowDNA = newDNA;
                }
                else
                m++;
            }
            else if(topDNA.charAt(m) == 'C'){
                if(lowDNA.charAt(m) != 'G'){
                newDNA = lowDNA.substring(0,m) + 'g' + lowDNA.substring(m+1);
                m++;
                lowDNA = newDNA;
                }
                else
                m++;
            }
        }
        System.out.println("Upper: " + topDNA + "\nLower: " + newDNA);
        System.out.println("Exiting Program");
        }
  }
}
