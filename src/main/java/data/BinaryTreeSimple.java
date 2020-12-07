package data;


/**
 *
 * @author CyK
 */
public class BinaryTreeSimple {
    
    private NodeSimple root;
    //size to verify if the add and delete methods are working correctly
    private int size;
    
    public BinaryTreeSimple()
    {
        this.root = null;
        this.size = 0;
    }
    
    public BinaryTreeSimple(String actividad) {
        this.root = new NodeSimple(actividad);
        this.size = 0;
    }
    /**
     * 
     * @param actividad:
     *                  activity defined by the user
     * @return
     *          true: if the node was deleted
     *          false: if there was an error
     */
    public boolean deleteNode(String actividad) {
        //Verify if the selected node exists
        if(search(actividad)){
            //Calls the recursive delete method
            this.root = delete(this.root, actividad.replaceAll("\\s", "").toUpperCase());
            return true;
        }
        return false;
    }
    
    /**
     * Recursive delete method
     * @param current:
     *                  refers to the current node
     * @param actividad:
     *                  defined by the user
     * @return 
     *          node (root)
     */
    private NodeSimple delete(NodeSimple current, String actividad)  {
        
        if (actividad.compareTo(current.getActividad()) < 0)  {
            
            current.setLeft(delete(current.getLeft(), actividad));
            
        }else if (actividad.compareTo(current.getActividad()) > 0) {
            
            current.setRight(delete(current.getRight(), actividad));
        }else{
            //Actividad equals current.getActividad()
            
            //Check if it is a leaf or the root without alone
            if(current.getLeft() == null && current.getRight() == null) {
                return null;
                
            //Check if it has only a child
            }else if (current.getLeft() == null) {
                return current.getRight();
                
            }else if (current.getRight() == null) {
                return current.getLeft();

            }
            /* When the node has 2 children:
                first, get the node (minNode) with the minimum value of the right subtree
                second, set the current key as the one from minNode 
                finally, call this function on the right subtree with the minNode key
            */
            
            NodeSimple minNodeVal = minValueNode(current.getRight());
            
            current.setActividad(minNodeVal.getActividad());
            
            current.setRight(delete(current.getRight(), minNodeVal.getActividad()));
        }
        
        return current;
    }
    
    /**
     * Method to obtain the node with the minimum value, 
     *  beginning from a particular node
     * @param current
     * @return 
     */
    private NodeSimple minValueNode(NodeSimple current)  {
        NodeSimple node = current;
        
        while (current.getLeft() != null)  {
            node = current.getLeft();
            current = current.getLeft();
        }
        return node;
    }
    
    /**
     * Method to insert a new node
     * @param actividad
     * @return 
     */
    public boolean insert(String actividad)  {
        //Convert String to its equivalent in Upper Case and without spaces
        actividad = actividad.replaceAll("\\s", "").toUpperCase();
        if(actividad.equals("")) {
            return false;
        }
        if(this.root == null) {
            //Call recursive insert
            this.root = new NodeSimple(actividad);
        } else {
            this.root = insert(this.root, actividad);
        }
        return true;
    }
    
    /**
     * Recursive insert method
     * @param current
     * @param actividad
     * @return (root)
     */
    private NodeSimple insert(NodeSimple current, String actividad) {
        //It has arrived to the node where current is null, so it adds the new node
        if (current == null) {
            current = new NodeSimple(actividad);
            return current;
        }else {
            //Select the path to follow
            if (actividad.compareTo(current.getActividad()) < 0) {
                current.setLeft(insert(current.getLeft(), actividad));
                
            }else if (actividad.compareTo(current.getActividad()) > 0) {
                current.setRight(insert(current.getRight(), actividad));
                
            }else {
                return current;
            }
            
        }
        return current;
    }
    
    /**
     * Search method to know if the node with the activity, 
     * that was given by the user, exists
     * @param actividad
     * @return 
     */
    public boolean search(String actividad) {
        //Call search recursive method
        NodeSimple node = search(this.root, actividad.replaceAll("\\s", "").toUpperCase());
        if (node != null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Recursive search method
     * @param current
     * @param actividad
     * @return 
     */
    private NodeSimple search(NodeSimple current, String actividad) {
        if(current != null){
        }
        if (current == null || current.getActividad().compareTo(actividad) == 0) {
            return current;
        }
            
        if (actividad.compareTo(current.getActividad()) < 0) {
            return search(current.getLeft(), actividad);
        }
            
        return search(current.getRight(), actividad);
    }
    
    /**
     * Method that gets the size of the tree,
     * it's use to verify the relation between this class
     * and the BinaryTree's one
     */
    public void size() {
        this.size = 0;
        System.out.println(size(this.root));
    }
    
    /**
     * Method that returns the number of existing nodes
     * in the tree
     * @param current
     * @return 
     */
    private int size(NodeSimple current) {
        if (current != null) {
            size(current.getLeft());
            this.size++;
            size(current.getRight());
        }
        return this.size;
    }
}
