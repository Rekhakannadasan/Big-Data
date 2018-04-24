// Remove duplicates

import java.util.*;
class duplicate
{
public static void main(String args[])
{
int[] arr=new int[10];
int size=arr.length;

Scanner sc=new Scanner(System.in);
System.out.println("Enter the numbers");
for(int l=0;l<size;l++)
{
arr[l]=sc.nextInt();
}

for(int i=0;i<size;i++)
{



for(int j = i + 1;j<size;j++)
{
if(arr[i]==arr[j])
{
while(j<(size)-1)
{
arr[j]=arr[j+1];//shifting
j++;
}
size--;
}
}
}

System.out.println("The elements without duplicates are");
for(int k=0;k<size;k++)
{
System.out.println(arr[k]);
}
}
}
