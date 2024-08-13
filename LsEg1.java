package Searching;
import java.util.Scanner;
public class LsEg1 
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of array");
        int size=sc.nextInt();
        int [] arr=new int[size];
        System.out.println("Enter Array Elements");

        for(int i=0;i<size;i++)
        {
            arr[i]=sc.nextInt();       
        }
        System.out.println("Enter Target Value");
        int target=sc.nextInt();

        int result=linearuserSearch(arr,target);
        if(result!=-1)
        {
            System.out.println("Element Found at "+result);
        }
        else
        {
            System.out.println("Element not found");
        }
        sc.close();
    }

    public static int linearuserSearch(int[]arr,int target)
    {
        for(int j=0;j<arr.length;j++)
            if (arr[j]==target) 
            {
                return j;    
            }
            return -1;
    }
}