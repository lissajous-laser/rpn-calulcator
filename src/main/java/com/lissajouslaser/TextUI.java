package com.lissajouslaser;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUI {
   Scanner scanInput;
   Stack numStack;

   public TextUI() {
      this(new Scanner(System.in), new Stack());
   }
   public TextUI(Scanner scanInput, Stack numStack) {
      this.scanInput = scanInput;
      this.numStack = numStack;
   }

   public void menu() {
      System.out.println("\nEnter either:");
      System.out.println("-  an integer");
      System.out.println("-  an operation (+, -, *, /)");
      System.out.println("-  an integer and operation (eg. 11+)");
      System.out.println("- 'q' to quit");
      
      while (true) {
         System.out.println("\n" + numStack);
         String input = scanInput.nextLine().trim();

         if (input.equals("q"))
            break;
         inputDispatcher(input);
      }
   }

   public void inputDispatcher(String input) {
      // integer input         
      if (input.matches("-?\\d+"))
         numStack.put(Integer.valueOf(input));
      // operation input
      else if (input.matches("[\\*/\\+-]{1}")) {
         switch (input) {
            case "*":
               numStack.mult(); break;
            case "/":
               numStack.divi(); break;
            case "+":
               numStack.addi(); break;
            case "-":
               numStack.subt(); break;
         }
      }
      // integer and operation input
      else if (input.matches("-?\\d*(\\*|/|\\+|-)?")) {
         Pattern pattern = Pattern.compile("-?\\d+");
         Matcher matcher = pattern.matcher(input);
         int operand = 0;
         if (matcher.find()) 
            operand = Integer.valueOf(matcher.group());         
               
         pattern = Pattern.compile("[\\*/\\+-]{1}");
         matcher = pattern.matcher(input);
         String operator = "";
         if (matcher.find())
            operator = matcher.group();

         switch (operator) {
            case "*":
               numStack.mult(operand); break;
            case "/":
               numStack.divi(operand); break;
            case "+":
               numStack.addi(operand); break;
            case "-":
               numStack.subt(operand); break;
         } 
      }
      else System.out.println("Invalid input.");     
   }

   Stack getNumStack() {
      return numStack;
   }
   
}
