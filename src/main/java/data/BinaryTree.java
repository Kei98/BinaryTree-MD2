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
    
    public boolean deleteNode(String actividad, int prioridad) {
        //Condición para saber si el nodo buscado existe
        if(search(actividad, prioridad)){
            Node root = this.root;
            System.out.println("Antes del delete");
            this.root = delete(this.root, actividad.replaceAll("\\s", "").toUpperCase(), prioridad);
            System.out.println("Después del delete");
            this.bts.deleteNode(actividad);
            System.out.println("Después del segundo delete");
            this.bts.size();
            return true;
        }
        return false;
    }
    
    private Node delete(Node current, String actividad, int prioridad)  {
        if(prioridad < current.getPrioridad()){
            current.setLeft(delete(current.getLeft(), actividad, prioridad));
        }else if(prioridad > current.getPrioridad()) {
            current.setRight(delete(current.getRight(), actividad, prioridad));
        }else{
            System.out.println("Delete 2 COMPLEJO mismo nivel");
            if (actividad.compareTo(current.getActividad().
                    replaceAll("\\s", "").toUpperCase()) < 0)  {
                
                current.setLeft(delete(current.getLeft(), actividad, prioridad));
                
            }else if (actividad.compareTo(current.getActividad().
                    replaceAll("\\s", "").toUpperCase()) > 0) {
                
                current.setRight(delete(current.getRight(), actividad, prioridad));
            }else{
                System.out.println("Delete 2 COMPLEJO misma actividad");
                if(current.getLeft() == null && current.getRight() == null) {
                    return null;
                    
                }else if (current.getLeft() == null) {
                    return current.getRight();
                    
                }else if (current.getRight() == null) {
                    return current.getLeft();
                    
                }
                
                Node minNodeVal = minValueNode(current.getRight());
                
                current.setActividad(minNodeVal.getActividad());
                
                current.setPrioridad(minNodeVal.getPrioridad());
                
                current.setRight(delete(current.getRight(), minNodeVal.getActividad().replaceAll("\\s", "").toUpperCase(), minNodeVal.getPrioridad()));
            }
        }
        return current;
    }
    
    private Node minValueNode(Node current)  {
        Node node = current;
        
        while (current.getLeft() != null)  {
            node = current.getLeft();
            current = current.getLeft();
        }
        return node;
    }
    
    public int insert(String actividad, int prioridad)  {
        if(!this.bts.search(actividad)){
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
            return -1;
        }
        
    }
    
    private Node insert(Node current, String actividad, String actividadOriginal, int prioridad) {
        if (current == null) {
            current = new Node(actividadOriginal.trim(), prioridad);
            return current;
        }
        if(prioridad < current.getPrioridad()){
            current.setLeft(insert(current.getLeft(), actividad, actividadOriginal, prioridad));
        } else if(prioridad > current.getPrioridad()){
            current.setRight(insert(current.getRight(), actividad, actividadOriginal, prioridad));
        } else {
            
            if (actividad.compareTo(current.getActividad().replaceAll("\\s", "").toUpperCase()) < 0) {
                current.setLeft(insert(current.getLeft(), actividad, actividadOriginal, prioridad));
                
            }else if (actividad.compareTo(current.getActividad().replaceAll("\\s", "").toUpperCase()) > 0) {
                current.setRight(insert(current.getRight(), actividad, actividadOriginal, prioridad));
                
            }else {
                current = null;
                return current;
            }
            
        }
        return current;
    }
    
    public boolean search(String actividad, int prioridad) {
        Node node = search(this.root, actividad.replaceAll("\\s", "").toUpperCase(), prioridad);
        if (node != null) {
            System.out.println("Encontró el nodo COMPLEJO");
            return true;
        }
        else {
            System.out.println("nodo COMPLEJO: NULO");
            return false;
        }
    }
    
    
    private Node search(Node current, String actividad, int prioridad) {
        System.out.println("Entra a search 2 COMPLEJO: " + actividad);
        if(prioridad < current.getPrioridad()){
            return search(current.getLeft(), actividad, prioridad);
        }else if (prioridad > current.getPrioridad()){
            return search(current.getRight(), actividad, prioridad);
        } else {
            System.out.println("Entra a else search 2 COMPLEJO: " + actividad);
            if(current != null){
                System.out.println("Entra a else search 2 COMPLEJO: " + current.getActividad().replaceAll("\\s", "").
                    toUpperCase());
            }
            if (current == null || current.getActividad().replaceAll("\\s", "").
                    toUpperCase().compareTo(actividad) == 0) {
                System.out.println("Encontró el nodo: COMPLEJO");
                return current;
            }
            
            if (actividad.compareTo(current.getActividad().replaceAll("\\s", "").toUpperCase()) < 0) {
                return search(current.getLeft(), actividad, prioridad);
            }
            
            return search(current.getRight(), actividad, prioridad);
        }
    }
    
    public ArrayList<String> inOrder() {
        this.orden.clear();
        return inOrder(this.root);
    }
    
    
    private ArrayList<String> inOrder(Node current) {
        if (current != null) {
            inOrder(current.getLeft());
            this.orden.add(current.getActividad() + " | Prioridad: " + Integer.toString(current.getPrioridad()));
            inOrder(current.getRight());
        }
        return this.orden;
    }
    
    public ArrayList<String> postOrder() {
        this.orden.clear();
        return postOrder(this.root);
    }
    
    
    private ArrayList<String> postOrder(Node current) {
        if (current != null) {
            postOrder(current.getLeft());
            postOrder(current.getRight());
            this.orden.add(current.getActividad() + " | Prioridad: " + Integer.toString(current.getPrioridad()));
        }
        return this.orden;
    }
    
    public ArrayList<String> preOrder() {
        this.orden.clear();
        return preOrder(this.root);
    }
    
    
    private ArrayList<String> preOrder(Node current) {
        if (current != null) {
            this.orden.add(current.getActividad() + " | Prioridad: " + Integer.toString(current.getPrioridad()));
            preOrder(current.getLeft());
            preOrder(current.getRight());
        }
        return this.orden;
    }
    
}