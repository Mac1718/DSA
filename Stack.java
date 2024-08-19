package StacksQueues;

public class Stack 
{
    int top=0;
    int maxsize=3;
    int stack[]=new int[maxsize];


    public void push(int data)
    {
        if(top==maxsize)
        {
            System.out.println("Stack is full");
        }
        else
        {
        stack[top]=data;
        top++;
        }
    }

    public int pop()
    {
        int data=0;
        if(top<=-1)
        {
            System.out.println("Stack is empty");
        }
        else
        {
        top--;
        data=stack[top];
        stack[top]=0;
        }
        return data;
    }

    public int peek()
    {
        int data;
        data=stack[top-1];
        return data;
    }

    public void show()
    {
        for(int n:stack)
        {
            System.out.println(n);
        }
    }

    public int size()
    {
        return top;
    }

    public boolean isEmpty()
    {
        return top<=0;
    }
    public static void main(String[] args)
    {
        Stack nums =new Stack();
        nums.push(15);
        nums.push(7);
       System.out.println(nums.pop());
        nums.show();
        System.out.println(nums.size()+" is the Size");
        System.out.println("Empty = "+nums.isEmpty());
    }
}
