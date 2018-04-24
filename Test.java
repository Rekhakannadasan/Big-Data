//Sort using Tree Set

import java.util.*;
class Test
{
public static void main(String args[])
{
TreeSet t=new TreeSet(new MySorting());
t.add(5);
t.add(3);
t.add(2);
t.add(1);
t.add(4);
System.out.println(t);
}
}
class MySorting implements Comparator
{
public int Compare(Object ob1,Object ob2);
{
Integer i1=(Integer)ob1;
Integer i2=(Integer)ob2;
if(i1>i2)
{
return +1;
}
else if(i1<i2)
{
return -1;
}
else return 0;
}}