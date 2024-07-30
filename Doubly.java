package LinkedLists;

class Node
{
    int data;
    Node next;
    Node prev;
    Node node;
}

public class Doubly 
{
    public static void main(String[] args)
    {
        LinkedList lists=new LinkedList();
        lists.insert(5);
        lists.insert(7);
        lists.insert(18);
        lists.show();
    }
}

class DLL
{
    Node head;
    public void insert(int data)
    {
        Node node=new Node();
        node.data=data;
        node.next=head;
        node.prev=null;
        
        if(head!=null)
        {
            head.prev=node;
        }

        head=node;
    }

    public void show()
    {
        Node n=head;
        while(n.next!=null)
        {
            System.out.println(n);
        }
        System.out.println(n);
    }
}
