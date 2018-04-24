//Get the date of birth from user and display if the student is eligible to vote?

import java.util.*;
class a
{

public static void main(String[] args)
{
	Scanner obj=new Scanner(System.in);
	
	string stdname[]=new string[10];
	int date[]=new int[10];
	int month[]=new int[10];
	int year[]=new int[10];
	 
	for(i=0;i<10;i++)
	{
		System.out.println("enter the student name");
		stdname[i]=obj.next();
		
		System.out.println("enter dob");
		date[i]=obj.nextInt();
		month[i]=obj.nextInt();
		year[i]=obj.nextInt();
	}
	
	for(i=0;i<10;i++)
	{
	newdate[i]=31-date[i];
	newmonth[i]=12-month[i];
	newyear[i]=2017-year[i];
	}
	
	for(i=0;i<10;i++)
	{
	if(newyear[i]>18)
		{System.out.println(stdname[i]);
		System.out.println("can vote");
		}
	}

	for(i=0;i<10;i++)
	{	
	System.out.print(newyear[i]);
	System.out.print("age");
	System.out.print(newmont[i]);		
			
}
} 