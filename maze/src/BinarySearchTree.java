public class BinarySearchTree<E extends Comparable<E>> {
    private int size;
    private Node<E> root;

    public BinarySearchTree(){
        this.root=null;
        this.size=0;
    }

    public int size(){
        return size;
    }

    public boolean contains(E data){
        return contains(root, data);
    }

    private boolean contains(Node<E> root, E data){
        if(root == null){
            return false;
        }
        int comparison = data.compareTo(root.data);
        if(comparison==0){
            return true;
        }
        else if (comparison < 0){
            return contains(root.left, data);
        } else{
            return contains(root.right, data);
        }
    }

    public void add(E data){
        this.root=add(this.root, data);
    }

    private Node<E> add(Node<E> root, E data){
        if(root == null) return new Node<>(data);
        int comparison = data.compareTo(root.data);
        if(comparison == 0) return root;
        if(comparison < 0){
            root.left=add(root.left, data);
            return root;
        }
        else {
            root.right = add(root.right,data);
            return root;
        }
    }

    public String toString(){
        return toString(this.root);
    }

    private String toString(Node<E> root){
        if(root == null) return "";
        StringBuilder builder = new StringBuilder();
        builder.append(toString(root.left));
        builder.append(" ");
        builder.append(root.data);
        builder.append(" ");
        builder.append(toString(root.right));
        return builder.toString();
    }

    public void remove(E data){
        this.root=this.remove(this.root,data);
    }

    private Node<E> remove(Node<E> root, E data){
        if(root == null) return null;
        int comparison = data.compareTo(root.data);
        if(comparison < 0){
            root.left=remove(root.left,data);
            return root;
        } else if(comparison > 0)
        {
            root.right=remove(root.right,data);
            return root;
        } else {
            if(root.left==null&&root.right==null) return null;
            else if(root.left != null && root.right == null) return root.left;
            else if(root.left == null && root.right != null) return root.right;
            else{
                Node<E> current = root.left;
                while(current.right != null){
                    current = current.right;
                }
                root.data = current.data;
                remove(root.left, root.data);
            }
        }
        return null;
    }

        private static class Node<E>{
            private E data;
            private Node<E> left;
            private Node<E> right;

            public Node(E data){
                this.data=data;
            }
        }
}
