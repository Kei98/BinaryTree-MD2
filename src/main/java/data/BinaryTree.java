/*

*/
package data;

import java.util.ArrayList;

/**
 *
 * @author CyK
 */
public class BinaryTree {
    private ArrayList<String> orden;
    Node root;
    BinaryTreeSimple bts;
    
    public BinaryTree()
    {
        this.root = null;
        this.orden = new ArrayList<>();
        this.bts = new BinaryTreeSimple();
    }
    
    public BinaryTree(String actividad, int prioridad) {
        this.root = new Node(actividad, prioridad);
        this.orden = new ArrayList<>();
        this.bts = new BinaryTreeSimple(actividad);
    }
    
    /**
     * 
     * @param actividad:
     *                  activity defined by the user
     * @param prioridad: 
     *                  defined by the user
     * @return
     *          true: if the node was deleted
     *          false: if there was an error
     */
    public boolean deleteNode(String actividad, int prioridad) {
        //Verify if the node exists
        if(search(actividad, prioridad)){
            //Call to the recursive delete method
            this.root = delete(this.root, actividad.replaceAll("\\s", "").toUpperCase(), prioridad);
            this.bts.deleteNode(actividad);
            this.bts.size();
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
     * @param prioridad:
     *                  defined by the user
     * @return 
     *          node (root)
     */
    private Node delete(Node current, String actividad, int prioridad)  {
        //search by priority first
        if(prioridad < current.getPrioridad()){
            current.setLeft(delete(current.getLeft(), actividad, prioridad));
        }else if(prioridad > current.getPrioridad()) {
            current.setRight(delete(current.getRight(), actividad, prioridad));
        }else{
            //Once it's on the same priority, look for the activity
            if (actividad.compareTo(current.getActividad().
                    replaceAll("\\s", "").toUpperCase()) < 0)  {
                
                current.setLeft(delete(current.getLeft(), actividad, prioridad));
                
            }else if (actividad.compareTo(current.getActividad().
                    replaceAll("\\s", "").toUpperCase()) > 0) {
                
                current.setRight(delete(current.getRight(), actividad, prioridad));
            }else{
                //It's on the same priority and activity
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
                    second, set the current keys as the ones from minNode (activity, priority) 
                    finally, call this function on the right subtree with the minNode keys
                */
                
                Node minNodeVal = minValueNode(current.getRight());
                
                current.setActividad(minNodeVal.getActividad());
                
                current.setPrioridad(minNodeVal.getPrioridad());
                
                current.setRight(delete(current.getRight(), minNodeVal.getActividad().replaceAll("\\s", "").toUpperCase(), minNodeVal.getPrioridad()));
            }
        }
        return current;
    }
    
    /**
     * Method to obtain the node with the minimum value, 
     *  beginning from a particular node
     * @param current
     * @return 
     */
    private Node minValueNode(Node current)  {
        Node node = current;
        
        while (current.getLeft() != null)  {
            node = current.getLeft();
            current = current.getLeft();
        }
        return node;
    }
    
    /**
     * Method to insert a new node
     * @param actividad
     * @param prioridad 
     * @return:
     *          -1: a node with that activity already exists
     *           0: the activity is null (empty)
     *           1: the insertion ended successfully
     */
    public int insert(String actividad, int prioridad)  {
        //Check if a node with that activity exists
        if(!this.bts.search(actividad)){
            //Check that the priority is between the parameters
            // and that the activity isn't empty
            if(prioridad < 1 || prioridad > 5 || actividad.replaceAll("\\s", "").equals("")) {
                return 0;
            }
            if(this.root == null) {
                this.root = new Node(actividad.trim(), prioridad);
                this.bts.insert(actividad);
                this.bts.size();
                return 1;
            } else {
                this.root = insert(this.root, actividad.replaceAll("\\s", "").toUpperCase(), actividad, prioridad);
                this.bts.insert(actividad);
                this.bts.size();
                return 1;
            }
        }else {
            //It exists
            return -1;
        }
        
    }
    
    /**
     * Recursive insert method
     * @param current
     * @param actividad:
     *                  activity String without spaces and in upper case
     * @param actividadOriginal
     * @param prioridad
     * @return 
     */
    private Node insert(Node current, String actividad, String actividadOriginal, int prioridad) {
        //It has arrived to the node that is null, so it adds the new node
        if (current == null) {
            current = new Node(actividadOriginal.trim(), prioridad);
            return current;
        }
        //Selects its path depending on the priority
        if(prioridad < current.getPrioridad()){
            current.setLeft(insert(current.getLeft(), actividad, actividadOriginal, prioridad));
        } else if(prioridad > current.getPrioridad()){
            current.setRight(insert(current.getRight(), actividad, actividadOriginal, prioridad));
        } else {
            //Selects its path depending on the activity
            if (actividad.compareTo(current.getActividad().replaceAll("\\s", "").toUpperCase()) < 0) {
                current.setLeft(insert(current.getLeft(), actividad, actividadOriginal, prioridad));
                
            }else if (actividad.compareTo(current.getActividad().replaceAll("\\s", "").toUpperCase()) > 0) {
                current.setRight(insert(current.getRight(), actividad, actividadOriginal, prioridad));
                
            }else {
                //It found a node with the same keys, so it 
                //returns current
                //This never happen because of the restriction at the beginning
                return current;
            }
            
        }
        return current;
    }
    
    /**
     * Search method to know if the node with the activity and priority,
     * that were given by the user, exist
     * @param actividad
     * @param prioridad 
     * @return 
     */
    public boolean search(String actividad, int prioridad) {
        Node node = search(this.root, actividad.replaceAll("\\s", "").toUpperCase(), prioridad);
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
     * @param prioridad 
     * @return 
     */
    private Node search(Node current, String actividad, int prioridad) {
        if(prioridad < current.getPrioridad()){
            return search(current.getLeft(), actividad, prioridad);
        }else if (prioridad > current.getPrioridad()){
            return search(current.getRight(), actividad, prioridad);
        } else {
            if(current != null){
            }
            if (current == null || current.getActividad().replaceAll("\\s", "").
                    toUpperCase().compareTo(actividad) == 0) {
                return current;
            }
            
            if (actividad.compareTo(current.getActividad().replaceAll("\\s", "").toUpperCase()) < 0) {
                return search(current.getLeft(), actividad, prioridad);
            }
            
            return search(current.getRight(), actividad, prioridad);
        }
    }
    
    /**
     * Order the elements of the tree in a natural way
     * (from minimum value to maximum)
     * @return 
     */
    public ArrayList<String> inOrder() {
        this.orden.clear();
        return inOrder(this.root);
    }
    
    /**
     * Recursive inOrder method
     * @param current
     * @return 
     */
    private ArrayList<String> inOrder(Node current) {
        if (current != null) {
            inOrder(current.getLeft());
            this.orden.add(current.getActividad() + " | Prioridad: " + Integer.toString(current.getPrioridad()));
            inOrder(current.getRight());
        }
        return this.orden;
    }
    
    /**
     * Orders the tree elements as left sub-tree, root
     * and right subtree
     * @return 
     */
    public ArrayList<String> postOrder() {
        this.orden.clear();
        return postOrder(this.root);
    }
    
    /**
     * Recursive postOrder method
     * @param current
     * @return 
     */
    private ArrayList<String> postOrder(Node current) {
        if (current != null) {
            postOrder(current.getLeft());
            postOrder(current.getRight());
            this.orden.add(current.getActividad() + " | Prioridad: " + Integer.toString(current.getPrioridad()));
        }
        return this.orden;
    }
    
    /**
     * Orders the tree elements as left subtree, right subtree
     * and the root
     * @return 
     */
    public ArrayList<String> preOrder() {
        this.orden.clear();
        return preOrder(this.root);
    }
    
    /**
     * Recursive preOrder method
     * @param current
     * @return 
     */
    private ArrayList<String> preOrder(Node current) {
        if (current != null) {
            this.orden.add(current.getActividad() + " | Prioridad: " + Integer.toString(current.getPrioridad()));
            preOrder(current.getLeft());
            preOrder(current.getRight());
        }
        return this.orden;
    }
    
}