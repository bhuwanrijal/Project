package opal;
import java.text.DecimalFormat;
import java.util.Scanner;
public class Opal {
    public static DecimalFormat df=new DecimalFormat(".##");
    public static void main(String[] args) {
        int x,y;
        double money=2.7;
       Scanner sc=new Scanner (System.in);
       double user[][] ={
                            {8.84,10.2,7.9,8.8,6.7,2.47,0},
                            {10.1,8.8,5,8.58,4.94,0,0},
                            {5.04,5.04,4.28,4.28,5.04,3.52,0},
                            {15.15,13.63,15.8,6.62,7.06,4.94,0}
                       };
       display(user);
       sum(user);
        
       for (x=0;x<=3;x++)
       {
        y=6; 
        System.out.println("\n Enter the value of user "+ (x+1) +" on sunday");
         abc:
        user[x][y]=sc.nextDouble();
          while(true){
           if(money<user[x][y] || user[x][y]<0) 
            {
                System.out.println("\nPlease enter the correct value again and the value shouldn't be more than $2.7 ");
                    user[x][y]=sc.nextDouble();        
            }
           else{
               break;
           }
        }  
       }
       average_fare(user);
       System.exit(0);
    }

    private static void display(double user_array[][] ) {
        double a;
       System.out.println("The daily opal fare of each user");
       System.out.println("--------------------------------------------- ");
        for(int x=0;x<=3;x++)
        {
            double sum=0;
           System.out.print("\t User " +(x+1));
           for(int y=0;y<=6;y++)
           {
               a=user_array[x][y];
               sum = sum + a;
               System.out.print("\t" + user_array[x][y]);
           }
           System.out.println();
        }
    }

    private static void sum(double[][] user_sum) {
        double a;
        System.out.println("\nThe partial sum of 6 days of each user: ");
        System.out.println("--------------------------------------------- ");
        for(int x=0;x<=3;x++)
        {
            double sum=0;
           for(int y=0;y<=6;y++)
           {
               a=user_sum[x][y];
               sum = sum + a;
           }
           System.out.println("The sum of the user " +(x+1)+ " is "+df.format(sum));
        }
    }

    private static void average_fare(double[][] user_average) {
      double a,user_avg;
       System.out.println("The average of each user");
       System.out.println("-------------------------------- ");
        for(int x=0;x<=3;x++)
        {
            double sum=0;
           for(int y=0;y<=6;y++)
           {
               a=user_average[x][y];
               sum = sum + a;   
           }
           user_avg=sum/7;
           System.out.println("The average user of " +(x+1)+ " is "+df.format(user_avg));
        }  
    }
   
}
