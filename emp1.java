import java.util.*;
class emp1
{
public static void main(String args[])
{
String empname;
int empid;
int age;


TreeSet<empl>empid=new TreeSet<empl>(new Myempid());
        empid.add(new empl("Ram",3000));
        empid.add(new empl("John",6000));
        empid.add(new empl("Crish",2000));
        empid.add(new empl("Tom",2400));
        for(empl e:empid){
            System.out.println(e);
        }
    }

   
 
class Myempid implements Comparator<empl>
{
public int compare(empl e1,empl e2) 
{
if(e1.empid() > e2.empid()){
            return 1;
        } else {
            return -1;
        }
  }
}
}