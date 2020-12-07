package data;

/**
 *
 * @author CyK
 */
public class Node {
    private String actividad;
    private int prioridad;
    private Node left;
    private Node right;

    public Node() {
        this.actividad = null;
        this.prioridad = 0;
        this.left = null;
        this.right = null;
    }

    
    public Node(String actividad, int prioridad) {
        this.actividad = actividad;
        this.prioridad = prioridad;
        this.left = null;
        this.right = null;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
}
