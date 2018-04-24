import java.util.*;
class Age
{
public static void main(String[] args)
{
Scanner obj=new Scanner(System.in);
String stdname[]=new String[10];
int date[]=new int[10];
int month[]=new int[10];
int year[]=new int[10];
int newdate[]=new int[10];
int newmonth[]=new int[10];
int newyear[]=new int[10];
int i;
	 
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
	int newdate[i]=31-date[i];
	int newmonth[i]=12-month[i];
	int newyear[i]=2017-year[i];
	}
	
	for(i=0;i<10;i++)
	{
	if(newyear[i]>18)
		{
                System.out.println(stdname[i]);
		System.out.println("Eligible for voting");
		}
	}

	for(i=0;i<10;i++)
	{	
	System.out.print(newyear[i]);
	System.out.print("age");
	System.out.print(newmonth[i]);			
}
}
}
	
	
