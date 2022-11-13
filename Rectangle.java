/*
Name: Grace K. P. Barros            Student Number: 100385031
Course/Section: CPSC 1150-6
Instructor: Bryan Green             Date:           November 9 - 2022
Assignment/Lab: Assignment#4        Last Modified:  November 10 - 2022 10:21am

Purpose:  Prints a Rectangle inside a 40X20 grid, with width and height provided by user.
*/

import java.util.Scanner;
public class Rectangle {
    public static void main (String[] args) {
        printIdentification();

        //Inputs from User
        int xCoordinate = askIntInsideRange("x coordinate you wanna start drawing your rectangle.\nRemember the grid range for x is [0,40]",0,39);
        int yCoordinate = askIntInsideRange("y coordinate you wanna start drawing your rectangle.\nRemember the grid range for y is [0,20]",1,20);
        int width = askIntInsideRange("width",1,40-xCoordinate);
        int height = askIntInsideRange("height",1,yCoordinate);

        //Execute
        drawVerticalGrid(xCoordinate,yCoordinate,width,height);
        drawBottom();

    } //Main method that calls all others methods

    /**
     Print student identification.
     It includes assignment/lab name, student name, course, section, and student number.
     */
    private static void printIdentification()
    {
        System.out.println("### Assignment 3: Rectangle Plotter   Author: Grace Karine P. Barros  ###");
        System.out.println("### Course/Section - CPSC1150-6 ###");
        System.out.println("### St.number - 100385031 ###\n");
    } // printIdentification

    /**
     Ask an input for the user and return an integer.
     It keeps asking input from user while user do not provide an integer.
     @param what name of what is being asked to be used at question for user clarification.
     @return first integer that users provides.
     */
    private static int askIntForUser(String what) {
        System.out.printf("Please enter the %s: ", what);
        Scanner input = new Scanner(System.in);
        int answer;
        while (!input.hasNextInt()) {
            System.out.printf("Sorry! Not valid, %s must be an integer. Try again:%n", what);
            input.nextLine();
        }
        answer = input.nextInt();
        return answer;
    }

    /**
     Verify if the integer number provided is inside range and return it or ask again for a number in range.
     It keeps asking input from user while user do not provide an integer inside range,
     always validating if it is an integer and if it is inside range limits.
     @param what name of what is being asked to be used at question (for user clarification).
     @param number which number was first provided by user.
     @param bottomLimit minimum number allowed inside range.
     @param topLimit maximum number allowed inside range.
     @return first integer in range that users provides.
     */
    private static int verifyRange(String what,int number,int bottomLimit, int topLimit){
        int answer=number;
        while(answer<bottomLimit || answer>topLimit){
            System.out.printf("Sorry! Not valid, number must be in range[%d , %d ]. Try again:%n", bottomLimit,topLimit);
            answer = askIntForUser(what); //if it is not inside range, call again the function to provide an integer.
            // This way we will make sure an integer is provided and checked if it is inside range.
        }
        return answer;
    } //Verify if int given is inside range

    /**
     Ask for an integer inside range and return it or ask again until it is provided.
     It combines the askIntForUser and verifyRange methods, so it is possible
     to ask for an integer inside range using only one method.
     It keeps asking input from user while user do not provide an integer inside range,
     always validating if it is an integer and if it is inside range limits.
     @param what name of what is being asked to be used at question (for user clarification).
     @param bottomLimit minimum number allowed inside range.
     @param topLimit maximum number allowed inside range.
     @return first integer in range that users provides.
     */
    private static int askIntInsideRange(String what,int bottomLimit, int topLimit){
        int myInteger = askIntForUser(what);
        myInteger = verifyRange(what,myInteger,bottomLimit,topLimit);
        return myInteger;
    }//Combine askIntForUser and verifyRange in one function to facilitate asking int inside range.


    /**
     Draw a line of repeat times the symbol provided
     @param length how many times symbol will be printed in the line.
     @param symbol the symbol that will be printed.
     */
    private static void drawLine(int length,char symbol)
    {
        for(int count=1;count<=length;count++){
            System.out.print(symbol);
        }
    } // drawLine

    /**
     Draw line per line of vertical grid, including the rectangle.
     Result is complete vertical grid, with pattern of + and | vertically.
     As soon as the line of grid it is being printed matches with y provided,
     rectangle will be printed with the parameters provided by user.
     @param x x Coordinate where rectangle start (must be inside grid limits).
     @param y y Coordinate where rectangle start (must be inside grid limits).
     @param width width of rectangle that will be printed.
     @param height height of rectangle that will be printed.
     */
    private static void drawVerticalGrid(int x, int y, int width,int height){
        System.out.println("   ^");

        for(int scale=20;scale>0;scale--,System.out.println()){ //each line will be printed and can not be modified once was printed(loops goes to next value);
            //Print the scale numbers and the pattern of +|
            if(scale%5==0 && scale!=5){
                System.out.print(scale+" +");
            }
            else if(scale==5){
                System.out.print(" 5 +");
            }
            else{
                System.out.print("   |");
            }

            //Verify if it is time to print the rectangle, then print it line per line
            if(scale<=y && scale>y-height){
                drawLine(x,' ');
                drawLine(width,'*');
            }
        }
    }

    /**
     Draw bottom part of the grid.
     It does not depend on any variable, it is just the pattern for grid.
     It prints the pattern of + and =. Also prints the numbers multiple of 5.
     */
    private static void drawBottom(){
        System.out.print(" 0 "); //zero from y scale
        for(int columns=0;columns<40;columns+=5){
            System.out.print('+');
            drawLine(4,'='); //print patterns of +====
        }
        System.out.println("+>");
        drawLine(3,' ');
        for(int scale=0;scale<=40;scale+=5){ //prints numbers of x scale with right space between each one.
            System.out.print(scale);
            if(scale==0)
                drawLine(4,' ');
            else
                drawLine(3,' ');
        }
    }
}
