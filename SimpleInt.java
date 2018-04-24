
package simpleint;

import java.util.*;
public class SimpleInt {

    
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       System.out.println("Enter the amount for which simple interest is to be calculated");
       int amt = sc.nextInt();
       System.out.println("Enter the interest rate");
       int r = sc.nextInt();
       System.out.println("Enter the number of years for which the amount is borrowed");
       int n = sc.nextInt();
       int i = (amt*r*n)/100;
       System.out.println("Simple interest for the given amount and the given time period is " + i);
    }
    
}
