package Trees;

public class BasicTree 
{
    public static void main(String[] args) 
    {
     BinaryTree tree=new BinaryTree();  
     tree.insert(6); 
     tree.insert(0);
     tree.insert(7);
     tree.insert(3);
     tree.insert(5);

     tree.inorder();
    }
}

class Node
{
    int data;
    Node right;
    Node left;

    public Node(int data)
    {
        this.data=data;
    }
}

class BinaryTree
{
    Node root;

    public void insert(int data)
    {
        root =insertRec(root,data);
    }

    public Node insertRec(Node root,int data)
    {
        if(root==null)
        {
            root=new Node(data);
        }
        else if(data<root.data)
        {
            root.left= insertRec(root.left, data);
        }
        else if(data>root.data)
        {
            root.right= insertRec(root.right, data);
        }

        return root;
    }

    public void inorder()
    {
        inorderRec(root);
    }

    public void inorderRec(Node root)
    {
        if(root != null)
        {
            inorderRec(root.left);
            System.out.println(root.data);
            inorderRec(root.right);
        }
    }


}