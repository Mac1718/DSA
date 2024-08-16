package StacksQueues;


class Main
{
    int size;
    int front;
    int rear;
    int [] queue=new int[5];

    public void enqueue(int data)
    {
        queue[rear]=data;
        rear++;
        size++;
    } 

    public void display()
    {
        for(int i=0;i<size;i++)
        {
            System.out.print(" " +queue[i]);
        }
    }
}

public class EnQueue 
{
    //FIFO-FIRST IN FIRST OUT;
    public static void main(String[] args) 
    {
        Main q = new Main();
        q.enqueue(5);
        q.enqueue(50);
        q.enqueue(500);
        q.display();

    }    
}
