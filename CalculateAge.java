//Calculate age from date of birth

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

public class CalculateAge
{
public static void main(String args[])throws Exception
{
Scanner sc=new Scanner(System.in);
for(int i=1;i<10;i++)
{
System.out.println("Enter the DOB in YYYY-MM-DD");

String input=sc.next();


SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
Calendar dob=Calendar.getInstance();
dob.setTime(sdf.parse(input));
System.out.println("Age is" +getAge(dob));
}
}
public static int getAge(Calendar dob)throws Exception
{

Calendar today=Calendar.getInstance();
int CurYear=today.get(Calendar.YEAR);
int dobYear=dob.get(Calendar.YEAR);
int age=CurYear-dobYear;

int curMonth=today.get(Calendar.MONTH);
int dobMonth=dob.get(Calendar.MONTH);
if(dobMonth>curMonth)
{
age--;
}
else if(dobMonth==curMonth)
{
int curDay=today.get(Calendar.DAY_OF_MONTH);
int dobDay=dob.get(Calendar.DAY_OF_MONTH);
if(dobDay>curDay)
{
age--;
}
}
return age;
}
}
