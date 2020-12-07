package data;

/**
 *
 * @author CyK
 */

//Node Class for the BinaryTreeSimple

public class NodeSimple {
    private String actividad;
    private NodeSimple left;
    private NodeSimple right;

    public NodeSimple() {
        this.actividad = null;
        this.left = null;
        this.right = null;
    }

    
    public NodeSimple(String actividad) {
        this.actividad = actividad;
        this.left = null;
        this.right = null;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public NodeSimple getLeft() {
        return left;
    }

    public void setLeft(NodeSimple left) {
        this.left = left;
    }

    public NodeSimple getRight() {
        return right;
    }

    public void setRight(NodeSimple right) {
        this.right = right;
    }
    
}
