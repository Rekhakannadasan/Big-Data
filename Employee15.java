//Get 5 user details from the employees using hash map.

import java.util.*;
class Employee15
{
int id,age;
String name;
double salary;

public Employee15(int id,int age,String name,double salary)
{
this.id=id;
this.age=age;
this.name=name;
this.salary=salary;
}
}
class Hashex
{
public static void main(String args[])
{
HashMap<Integer,Employee15> map=new HashMap();
Employee15 e1=new Employee15(1,25,"Rekha",25000);
Employee15 e2=new Employee15(2,20,"Shalini",20000);
Employee15 e3=new Employee15(3,18,"Vishali",12000);
Employee15 e4=new Employee15(4,12,"Prem",2000);
Employee15 e5=new Employee15(5,28,"Viyona",22000);

map.put(1,e1);
map.put(2,e2);
map.put(3,e3);
map.put(4,e4);
map.put(5,e5);

map.remove(4);

for(Map.Entry<Integer,Employee15>entry:map.entrySet())
{
int key=entry.getKey();
Employee15 e=entry.getValue();
System.out.println(key+ " "+"Details:");
System.out.println(e.id+" " +e.age+" "+e.name+" "+e.salary);
}

System.out.println("After removing" +map);
}
}
