import java.util.*;
public class TransacDet
{
 public static void main(String []args)
  {
    int custId;
    String custName;
    int price;
    int quantity;
    int amt;
    int high = 0;
        Scanner sc = new Scanner(System.in);
    for(int i = 0; i < 10; i++)
      {
        System.out.println("Enter custId");
        System.out.println("Enter custName");
         System.out.println(" price");
         System.out.println("quantity");

        custId = sc.nextInt();
        custName = sc.next();
        price = sc.nextInt();
        quantity = sc.nextInt();
        amt = price * quantity;
        System.out.println("total is " + amt);
        if (high < amt)
          {
            high = amt;
          }
          
      }
     System.out.println("highest price is " + high);
  }
}