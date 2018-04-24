// Generate pin

import java.util.*;
class pin
{
public static void main(String[] args)
{
int num,rem,sum=0,sum1=0,length1=0;

Scanner obj=new Scanner(System.in);
char symbol[]={'!','@','#','$','%','^','&','*','+'};

System.out.println("Enter your name");	
String name=obj.next();
length1=name.length();
char[] ch=name.toCharArray();
System.out.println("Enter your number");
num=obj.nextInt();

//convert int to String
String num1=String.format("%d",num);

//convert to char
char[] ch1=num1.toCharArray();
while(num>0)
{
rem=num%10;
sum=sum+rem;
num=num/10;
}
if(sum>9)
{
while(sum>0)
{
rem=sum%10;
sum1=sum1+rem;
sum=sum/10;
}
}
System.out.print(ch[0]);
System.out.print(symbol[sum1]);
System.out.print(ch1[0]);
System.out.print(ch[length1-1]);
}
}
		




	
