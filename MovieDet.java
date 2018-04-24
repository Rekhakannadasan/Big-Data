
/* Write a program to accept movie details like

Movie id
Name
Actor
Director
Release Date
Rating

Do the below analysis:

Display the total count of movies where rating is greater than 5
Display total count of movies where actor is Amitabh Bhachan
Display total count of movies where director is Karan Johar. */

import java.util.*;
public class MovieDet {

   
    public static void main(String[] args) {
        int count = 0;
        int rat_count = 0;
        int dir_count = 0;
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter how many movies you want to enter");
        n = sc.nextInt();
       for(int i = 0; i< n; i++)
       {
           System.out.println("Enter the movie id");
           int movieid = sc.nextInt();
           System.out.println("Enter the movie name");
           String name = sc.next();
           System.out.println("Enetr how many actors we like to enter");
           int t = sc.nextInt();
           String actor[] = new String[t];
           for(int j = 0; j<t ; j++)
           {
           System.out.println("Enter the name of actor");
           actor[j] = sc.next();
           if(actor[j].equals("AmitabhBachhan"))
           {
               count = count + 1;
               System.out.println("count is " + count);
           }
           }
           System.out.println("Enter the movie director");
           String director = sc.next();
           if(director.equals("KaranJohar"))
           {
               dir_count = dir_count + 1;
               System.out.println("count of dir is" + dir_count);
           }
           System.out.println("enter movie release date in yyyy-mm-dd");
           String moviedate = sc.next();
           System.out.println("Enter the rating in 0-10");
           int rating = sc.nextInt();
           if(rating > 5)
           {
               rat_count = rat_count + 1;
           }
           
           
       }
       System.out.println("Total count of movies with rating greater than 5 is " + rat_count);
       System.out.println("Total count of movies where actor is AmitabhBachhan is " + count);
       System.out.println("Total count of movies where director is KaranJohar " + dir_count);
    }
    
}
