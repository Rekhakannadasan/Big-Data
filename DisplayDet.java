
package displaydet;
import java.util.*;

public class DisplayDet {

    public void display()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = sc.next();
        System.out.println("Enter your date as yyyy-mm-dd");
        String dob = sc.next();
        System.out.println("Enter your address");
        String address = sc.next();
        System.out.println("Enter your marital status");
        String marital_status = sc.next();
        System.out.println("Enter your PG details");
        String PG = sc.next();
        System.out.println("Enter your UG details");
        String UG = sc.next();
        System.out.println(name);
        System.out.println("date of birth is : " + dob);
        System.out.println("address is " + address);
        System.out.println("Marital status is " + marital_status);
        System.out.println("pg details " + PG);
        System.out.println("ug details " + UG);
    }
    public static void main(String[] args) {
        DisplayDet dd = new DisplayDet();
        dd.display();
    }
    
}
