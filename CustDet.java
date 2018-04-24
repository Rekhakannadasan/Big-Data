/* Write a program to accept customer details like
Cust id
name
Gender
City
Country
Hobby 
phone

Do below analysis and display the result
Display total count of male and female
Display total count of senoir citizen(age>60)
Display total count of customer whose hobbies are travelling
Display total count of customer belongs to india and usa who does not have phone numbers */

package custdet;

import java.util.*;
public class CustDet {

   
    public static void main(String[] args) {
        CustDet cd = new CustDet();
        int f_count = 0;
        int m_count = 0;
        int s_count = 0;
        int h_count = 0;
        int c_count = 0;
        int p_count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of customers like to add");
        int n = sc.nextInt();
        for(int i = 0; i<n; i++)
        {
         System.out.println("Enter the customer id");
         int custid = sc.nextInt();
         System.out.println("Enter the customer name");
         String custname = sc.next();
         System.out.println("Enter the customer age");
         int custage = sc.nextInt();
         System.out.println("Enter the gender male or female");
         String gender = sc.next();
         System.out.println("Enter the city");
         String city = sc.next();
         System.out.println("Enter the country");
         String custcountry= sc.next();
         System.out.println("Enter the hobby");
         System.out.println("Eneter the number of hobbies you like to enter");
         int t = sc.nextInt();
         for(int j = 0; j< t; j++)
         {
         String custhobby[] = new String[t];    
         custhobby[j] = sc.next();
         if(custhobby[j].equals("travelling"))
         {
             h_count = h_count + 1;
         }
         }
         
         System.out.println("enter the phone number");
         String custphone = sc.next();
         if(gender.equals("male"))
         {
             m_count = m_count + 1;
         }
         else if(gender.equals("female"))
         {
             f_count = f_count + 1;
         }
         else
         {
             System.out.println("enter either male or female");
             
         }
         if(custage > 60)
         {
             s_count = s_count + 1;
         }
         
         if(custcountry.equalsIgnoreCase("India") || custcountry.equalsIgnoreCase("USA"))
         {
             c_count = c_count + 1;
         }
         if(custphone.equals("null")||custphone.equals("nill"))
         {
             p_count = p_count + 1;
         }
        }
        System.out.println("total count of male : " + m_count);
        System.out.println("total count of female : " + f_count);
        System.out.println("total count of senior citizen : " + s_count);
        System.out.println("total count of customers whose hobies are travelling :" + h_count);
        System.out.println("total count of customers belong to India and USA :" + c_count);
        System.out.println("total number of customers not having phone : " + p_count);
    }
    
}
