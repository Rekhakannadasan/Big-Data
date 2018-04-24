/* Write a program to accept Employee details:

Emp id
Name
City
Country
Dept(hr,Finance,marketing,operations)
age
Marital status
Gender
Salary

Do below analysis and display the result.

Find out and display the details of male employees who are married
Find out and display the total count of employees in each department
Find out and display the highest salary in each department
Find out and display the details of all the employees who are going to retire from his service after one year (age>=60 retirement)
Find out and display the details of all the employees who are female and belongs to india. */

import java.util.Scanner;


public class EmpDet {

    
    public static void main(String[] args) {
        int h_count = 0;
        int m_count = 0;
        int f_count=0;
        int o_count = 0;
        double h_sal = 0;
        double m_sal = 0;
        double f_sal=0;
        double o_sal= 0;
        int r_count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of employee you like to add");
        int n = sc.nextInt();
        int empid[] = new int[n];
        String empname[] = new String[n];
        String gender[] = new String[n];
        String mari[] = new String[n];
        String empcountry[] = new String[n];
        for(int i = 0; i<n; i++)
        {
         System.out.println("Enter the employee id");
         empid[i] = sc.nextInt();
         System.out.println("Enter the employee name");
         empname[i] = sc.next();
         System.out.println("Enter the emp age");
         int empage = sc.nextInt();
         System.out.println("Enter the gender male or female");
         gender[i] = sc.next();
         System.out.println("Enter the city");
         String city = sc.next();
         System.out.println("Enter the country");
         empcountry[i]= sc.next();
         System.out.println("Enter the department in hr or marketing or finance or operations");
         String department = sc.next();
         System.out.println("Enter the marital status either single or married");
         mari[i] = sc.next();
         System.out.println("Enter the salary of the employee");
         double sal = sc.nextDouble();
                  if(department.equalsIgnoreCase("hr"))
         {
             h_count = h_count + 1;
             if(sal > h_sal)
             {
                 h_sal = sal; 
             }
         }
         if(department.equalsIgnoreCase("marketing"))
         {
             m_count = m_count + 1;
             if(sal > m_sal)
             {
                 m_sal = sal; 
             }
         }
         if(department.equalsIgnoreCase("finance"))
         {
             f_count = f_count + 1;
             if(sal > f_sal)
             {
                 f_sal = sal; 
             }
         }
         if(department.equalsIgnoreCase("operations"))
         {
             o_count = o_count + 1;
             if(sal > o_sal)
             {
                 o_sal = sal; 
             }
         }
         if(empage==59)
         {
             System.out.println("employees who are going to get retired after one year : " + empname);
             r_count = r_count + 1;
         }
         
        }
        for(int j=0;j<n;j++)
        {
         if(gender[j].equalsIgnoreCase("male"))
         {
             if(mari[j].equalsIgnoreCase("married"))
             {
                 System.out.println("male employee who are married are  " + empname[j] + " whose id is  " + empid[j]);
             }
         }
         else if (gender[j].equalsIgnoreCase("female"))
         {
             if(empcountry[j].equalsIgnoreCase("india"))
             {
                 System.out.println("female employee who are from Inida : " + empname[j]);
             }
         }
   
        }
        System.out.println("total count of employee in hr " + h_count);
        System.out.println("total count of employee in marketing " + m_count);
        System.out.println("total count of employee in finance " + f_count);
        System.out.println("total count of employee in operations " + o_count);
        System.out.println("highest salary of employee in hr " + h_sal);
        System.out.println("highest salary of employee in marketing " + m_sal);
        System.out.println("highest salary of employee in finance " + f_sal);
        System.out.println("highest salary of employee in operations " + o_sal);
        System.out.println("empployee who are going to retire after one year : " + r_count);
    }
    
}
