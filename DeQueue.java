package StacksQueues;

 class Main
{
    int front;
    int size;
    int rear;
    int[] queue=new int[5];
    
    public void enqueue(int data)
    {
        queue[rear]=data;
        rear++;
        size++;
    } 

    public int dequeue()
    {
        int data=queue[front];
        front ++;
        size--;
        return data;
    }

    public void show()
    {
        for(int i=0;i<size;i++)
        {
            System.out.println(queue[front + i]);
        }
    }
}


public class DeQueue
{
    public static void main(String[] args) 
    {
        Main q=new Main();
        q.enqueue(67);
        q.enqueue(66);
        q.dequeue();
        q.show();

    }
}