/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cicularlinkedlist;


/**
 *
 * @author CLOJA
 * @param <E>
 */
public interface List<E> {

    boolean set(int index, E element);
    boolean insert(int index, E element) ;
    boolean remove(int index) ;
    boolean isEmpty()   ; 
    List<E> slicing(int start, int end);
    void reverse();
    boolean addFirst(E element);
    boolean addLast(E element);
    boolean removeFirst();
    boolean removeLast();
    E getFirst();
    E getLast();
    E get(int index);
    int size();
    boolean contains(E element);
        
    
}
