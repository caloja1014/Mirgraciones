/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cicularlinkedlist;

import java.util.Objects;
import java.util.Iterator;
/**
 *
 * @author CLOJA
 */
public class CircularLinkedList<E> implements List<E> {
    private Node<E> last;
    private int efectivo;
    
    public CircularLinkedList(){
        this.efectivo=0;
        this.last=null;
    }
    

    @Override
    public boolean set(int index, E element) {
        if(isEmpty()||element ==null) return false;
        if(last.getNext()==last && index==0){
            last.getNext().setData(element);
        }
        else{
            
            Node<E> node= nodeIndex(index);
            node.setData(element);
        }
        return true;
        

    }

    @Override
    public boolean insert(int index, E element) {

        if(index==0 ) return addFirst(element);
        if (element==null||index<0||index>efectivo) return false;
        if(index==efectivo) return addLast(element);
        else{
            Node<E> q= nodeIndex(index-1);
            Node<E> p = q.getNext();
            Node<E> node= new Node<>(element);
            q.setNext(node);
            node.setNext(p);
            efectivo++;
            return true;
        }

    }

    @Override
    public boolean remove(int index) {
        if(isEmpty()||index>=efectivo||index<0) return false;
        if( index==0) {
            removeFirst();
        }
        else if(index==efectivo-1){
            removeLast();
        }
        else{
            Node<E> previous=nodeIndex(index-1);
            Node<E> node= previous.getNext();
            previous.setNext(node.getNext());
            node.setData(null);//help GC
            --efectivo; 
        }
        return true;
        

    }

    @Override
    public boolean isEmpty() {
        return efectivo==0;
    }

    @Override
    public List<E> slicing(int start, int end) {
        List<E> lista= new CircularLinkedList<>();
        if(isEmpty()||start<0||end>=efectivo||start>end) return lista;
        Node<E> nodeI=nodeIndex(start);
        Node<E> nodeF=nodeIndex(end);
        for(Node<E> p=nodeI;p!=nodeF;p=p.getNext()){
            lista.addLast(p.getData());
        }
        return lista;
            }

    @Override
    public void reverse() {
        // singly linked list
        Node<E> first= last.getNext();
        Node prev = null;  
        Node current = last.getNext() ;  
        Node next;  
        do 
        {  
            next = current.getNext();  
            current.setNext( prev);  
            prev = current;  
            current = next;  
        } while (current != (last));  

        // adjutsing the links so as to make the  
        // last node point to the first node  
        Node<E> node=new Node<>(last.getData());
        node.setNext(prev);
        last=first;
        last.setNext(node);
    }

    @Override
    public boolean addFirst(E element) {

        Node<E> node = new Node<>(element);
        if(element==null) return false;
        else if (isEmpty()) {
            last=node;
            last.setNext(node);
        }
        else{
            node.setNext(last.getNext());
            last.setNext(node);
            
        }
        ++efectivo;
        return true;

    }

    @Override
    public boolean addLast(E element) {
        Node<E> node= new Node<>(element);
        if(element==null) return false;
        else if(isEmpty()){
            last=node;
            last.setNext(node);
        }
        else{
            node.setNext(last.getNext());
            last.setNext(node);
            last= node;
        }
        ++efectivo;
        return true;
    }

    @Override
    public boolean removeFirst() {

        if(isEmpty()){
            return false;
        }
        else if(last.getNext()==last){   
            last=null;
            return true;
        }
        else{
            Node<E> tmp= last.getNext();
            last.setNext(last.getNext().getNext());
            tmp.setNext(null);
            
        }
        --efectivo;
        return true;

    }

    @Override
    public boolean removeLast() {
        if(isEmpty()) return false;
        else if (last.getNext()==last){
            last=null;
        }
        else{
            Node<E> node= getPrevious(last);
            node.setNext(last.getNext());
            last.setNext(null);
            last=node;
            
        }
        --efectivo;
        return true;
        
    }

    @Override
    public E getFirst() {
        return last.getNext().getData();
    }

    @Override
    public E getLast() {
        return last.getData();
    }

    @Override
    public E get(int index) {
        if(index>=efectivo || index<0) return null;
        return nodeIndex(index).getData();

    }

    @Override
    public int size() {
        return efectivo;
    }

    @Override
    public boolean contains(E element) {
        if(isEmpty()) return false;
        else if(element==last.getNext().getData()) return true;
        Node<E> q=this.last.getNext();
        do{
            if(q.getData()==element){
                    return true;
            }
            q=q.getNext();
        }while(q!=last.getNext());
        return false;


    }
    
    private Node<E> nodeIndex(int indice){
        int i=0;
        Node<E> node= this.last.getNext();
        
        while( i<indice && efectivo>indice){
            node=node.getNext();
            i++;
        }
        return node;
    } 
    
    private Node<E> getPrevious(Node<E> node){
        if (node==last.getNext())return null;
        
        Node<E> q=last.getNext();
        do{
            if(q==node){
                return q;
            }
            q=q.getNext();
        }while(q!= last.getNext());
        
        return null;
    }
    
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        Node<E> q=last.getNext();
        E data=q.getData();
        do{
            sb.append(data+",");
            q=q.getNext();
            data=q.getData();
        
        }
        while(last.getNext()!=q);
        
        return sb.substring(0,sb.length()-1)+"]";
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CircularLinkedList<E> l=(CircularLinkedList) obj;
        Node<E> q=this.last.getNext();
        Node<E> other= l.last.getNext();
        do{
            if(!q.getData().equals(other.getData())){
                    return false;
            }
            q=q.getNext();
            other=other.getNext();
        }while(q!=last.getNext());
        return true;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.last);
        hash = 67 * hash + this.efectivo;
        return hash;
    }
    
    public Iterator<E> iterator(){

        Iterator<E> it= new Iterator<E>() {
            Node<E> nodo=last;
         @Override
         public boolean hasNext() {
             return nodo.getNext()!=null;
         }

         @Override
         public E next() {
             Node<E> tmp=nodo;
             nodo=nodo.getNext();
             return tmp.getData();
             
         }
     } ;
     return it;
    }
    
}
