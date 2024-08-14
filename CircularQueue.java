
package StacksQueues;


class Cq
{
    int size;
    int front;
    int rear;
    int[] q=new int[5];


    public void enqueue(int data)
    {
        if(!isFull())
        {
        q[rear]=data;
        rear=(rear+1)%5;
        size++;
        }
        else
        {
            System.out.println("Queue is Full");
        }
        
    }

    public int  dequeue()
    {
        int data=0;
        if(!isEmpty())
        {
        data=q[front];
        front=(front+1)%5;
        size--;
        }
        else
        {
            System.out.println("Queue is Empty");
        }
        return data;
    }

    public void show()
    {
        for(int i=0;i<size;i++)
        {
            System.out.println(q[(front+i)%5]);
        }
        System.out.println();
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public boolean isFull()
    {
        return size==5;
    }
}
public class CircularQueue 
{
    public static void main(String[] args)
    {
        Cq queue=new Cq();
        queue.enqueue(56);
        queue.enqueue(34);
        queue.enqueue(39);
        queue.enqueue(28);
        queue.dequeue();
        queue.enqueue(11);
        queue.enqueue(200);
        queue.show();
        queue.size();
        System.out.println(queue.isFull());
        System.out.println(queue.isEmpty());
    }    
}
