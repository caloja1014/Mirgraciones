/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cicularlinkedlist;

/**
 *
 * @author CLOJA
 */
public class Node<E> {
    private Node<E> next;
    private E data;
    
    public Node(E data){
        this.next=null;
        this.data=data;
    }

    /**
     * @return the next
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * @return the data
     */
    public E getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(E data) {
        this.data = data;
    }
    
}
