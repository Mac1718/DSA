package StacksQueues;

class DynamicStack 
{
    int capacity=2;
    int top=0;
    int dstack[]=new int[capacity];

    public void push(int data)
    {
        if(size()==capacity)
            expand();
        data=dstack[top];
        top++;
    }
             
    private void expand()
    {
        int length=size();
        int newStack[]=new int[capacity*2];
        System.arraycopy(dstack, 0, newStack, 0, length);
        dstack=newStack;
        capacity*=2;
    }

    public int pop()
    {
        int data=0;
        if(top<=-1)
        {
            System.out.println("Stack Underflow");
        }
        else
        {
            top--;
            data=dstack[top];
            dstack[top]=0;
        }
        return data;
    }

    public void display()
    {
        for(int n : dstack)
        {
            System.out.println(n);
        }
    }

    public int size()
    {
        return top;
    }

    public class DynamicArrayStack
    {
    public static void main(String[] args) 
    {
        DynamicStack num=new DynamicStack();
        num.push(23);
        num.push(34);
        num.push(44);
        num.push(54);
        num.push(64);
        num.display();
    }    
}
}
