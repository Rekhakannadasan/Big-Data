import java.util.*;
class Puzz {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first word");
        String name1 = sc.next();
        char wrd1 = name1.charAt(0);
        char wrd1end = name1.charAt(4);
        System.out.println("Enter the second word");
        String name2 = sc.next();
        char wrd2 = name2.charAt(0);
        char wrd2end = name2.charAt(4);
        //char word2[] = name2.toCharArray();
        if(wrd1 == wrd2 && name1.length()== 5 && name2.length()==5)
        {
        System.out.println("Enter the third word");
        String name3 = sc.next();
        char wrd3 = name3.charAt(0);
        char wrd3end = name3.charAt(4);  
        if(wrd3 == wrd2end)
        {
        System.out.println("Enter the fourth word");
        String name4 = sc.next();
        char wrd4 = name4.charAt(0);
        char wrd4end = name4.charAt(4);
        if(wrd4 == wrd1end && wrd4end == wrd3end && name3.length() == 5 && name4.length() == 5)
        {
         
            for(int i = 0; i < 5; i++)
         {
             System.out.print(name1.charAt(i));
         }
            System.out.print("\n");
         for(int i = 1; i < 5; i++)
         {
             //System.out.println(name1.charAt(i));
             System.out.print(name2.charAt(i));
             if(i == 4)
             {
                 for(int j = 1; j < 4; j++)
                 {
                     System.out.print(name3.charAt(j));
                 }
             }
             
            if(i<4)
            {
             System.out.print(" " + " " + " " +name4.charAt(i) + "\n");
            }
            else
            {
                System.out.println(name4.charAt(i));
            }
             
         }
         
         
        }
        else
        {
            System.out.println("Enter the value with first and last name as same and the wrd should be of 5 letters");
            System.exit(0);
        }
          
        }
        else
        {
            System.out.println("Enter the value with first and last name as same and the wrd should be of 5 letters");
            System.exit(0);
        }
        }
        else
        {
            System.out.println("Enter the value with first and last name as same and the wrd should be of 5 letters");
            System.exit(0);
            
            
        }
        
        
        
    }
    
}


output:
C:\Users\KG Computers\Desktop>java Puzz
Enter the first word
apple
Enter the second word
apple
Enter the third word
eagle
Enter the fourth word
eagle
apple
p   a
p   g
l   l
eagle