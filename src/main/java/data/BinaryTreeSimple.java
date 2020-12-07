package data;

import java.util.ArrayList;

/**
 *
 * @author CyK
 */
public class BinaryTreeSimple {
    private NodeSimple root;
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
    
    public boolean deleteNode(String actividad) {
        //Condición para saber si el nodo buscado existe
        if(search(actividad)){
            NodeSimple root = this.root;
            this.root = delete(this.root, actividad.replaceAll("\\s", "").toUpperCase());
            return true;
        }
        return false;
    }
    
    private NodeSimple delete(NodeSimple current, String actividad)  {
        if (actividad.compareTo(current.getActividad()) < 0)  {
            
            current.setLeft(delete(current.getLeft(), actividad));
            
        }else if (actividad.compareTo(current.getActividad()) > 0) {
            
            current.setRight(delete(current.getRight(), actividad));
        }else{
            if(current.getLeft() == null && current.getRight() == null) {
                return null;
                
            }else if (current.getLeft() == null) {
                return current.getRight();
                
            }else if (current.getRight() == null) {
                return current.getLeft();

            }
            
            NodeSimple minNodeVal = minValueNode(current.getRight());
            
            current.setActividad(minNodeVal.getActividad());
            
            current.setRight(delete(current.getRight(), minNodeVal.getActividad()));
        }
        
        return current;
    }
    
    private NodeSimple minValueNode(NodeSimple current)  {
        NodeSimple node = current;
        
        while (current.getLeft() != null)  {
            node = current.getLeft();
            current = current.getLeft();
        }
        return node;
    }
    
    public boolean insert(String actividad)  {
        actividad = actividad.replaceAll("\\s", "").toUpperCase();
        if(actividad.equals("")) {
            return false;
        }
        if(this.root == null) {
            this.root = new NodeSimple(actividad);
            return true;
        } else {
            this.root = insert(this.root, actividad);
            return true;
        }
    }
    
    private NodeSimple insert(NodeSimple current, String actividad) {
        if (current == null) {
            current = new NodeSimple(actividad);
            return current;
        }else {
            if (actividad.compareTo(current.getActividad()) < 0) {
                current.setLeft(insert(current.getLeft(), actividad));
            }else if (actividad.compareTo(current.getActividad()) > 0) {
                current.setRight(insert(current.getRight(), actividad));
            }else {
                current = null;
                return current;
            }
            
        }
        return current;
    }
    
    public boolean search(String actividad) {
        System.out.println("search 2 " +actividad);
        NodeSimple node = search(this.root, actividad.replaceAll("\\s", "").toUpperCase());
        if (node != null) {
            System.out.println("Encontró el nodo SIMPLE");
            return true;
        }
        else {
            System.out.println("nodo SIMPLE: NULO");
            return false;
        }
    }
    
    
    private NodeSimple search(NodeSimple current, String actividad) {
        System.out.println("Search 2 SIMPLE: " + actividad);
        if(current != null){
            System.out.println(current.getActividad());
        }
        if (current == null || current.getActividad().compareTo(actividad) == 0) {
            return current;
        }
            
        if (actividad.compareTo(current.getActividad()) < 0) {
            return search(current.getLeft(), actividad);
        }
            
        return search(current.getRight(), actividad);
    }
    
    public void size() {
        this.size = 0;
        System.out.println(size(this.root));
    }
    
    
    private int size(NodeSimple current) {
        if (current != null) {
            size(current.getLeft());
            this.size++;
            size(current.getRight());
        }
        return this.size;
    }
}
