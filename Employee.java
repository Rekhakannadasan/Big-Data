//Sort 5 employees using Tree Set

import java.util.*;
class Employee
{
int empid;
String name;
int age;
Employee(int empid,String name,int age)
{
this.empid=empid;
this.name=name;
this.age=age;
}
}

class MySorting implements Comparator
{
public int compare(Object o1,Object o2)
{
Employee e1=(Employee)o1;
Employee e2=(Employee)o2;

if(e1.empid==e2.empid)
return 0;
else if(e1.empid>e2.empid)
return 1;
else 
return -1;
}
}

class tree
{
public static void main(String args[])
{
TreeSet t=new TreeSet(new MySorting());
t.add(new Employee(1,"Rekha",25));
t.add(new Employee(2,"Vishali",5));
t.add(new Employee(3,"Viyona",15));
t.add(new Employee(4,"Shalini",20));
t.add(new Employee(5,"Prema",30));
System.out.println("Sort by empid");
Collections.sort(t,new MySorting());
Iterator itr=t.iterator();
while(itr.hasNext())
{
Employee em=(Employee)itr.next();
System.out.println(em.empid+" "+em.name+" "+em.age);
}
}
}