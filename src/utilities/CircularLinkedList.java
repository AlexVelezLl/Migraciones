/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author Alex Velez
 * @param <E> elemento
 */
public class CircularLinkedList<E> implements Iterable{
    private Node<E> first;
    private int efectivo;
    
    public CircularLinkedList(){
        first = null;
        efectivo = 0;
    }
    

    public boolean addFirst(E element) {
        if(element== null) return false;
        Node<E> node = new Node(element);
        if(isEmpty()){
            first= node;
            node.setNext(node);
            node.setPrevious(node);
        }
        else{
            node.setPrevious(first.getPrevious());
            node.setNext(first);
            first.getPrevious().setNext(node);
            first.setPrevious(node);
            first = node;
        }
        efectivo++;
        return true;
    }

    
    public boolean addLast(E element) {
        if(element==null) return false;
        Node<E> node = new Node(element);
        if(isEmpty()){
            first = node;
            first.setNext(node);
            first.setPrevious(node);
        }else{
            node.setNext(first);
            node.setPrevious(first.getPrevious());
            first.getPrevious().setNext(node);
            first.setPrevious(node);
        }
        efectivo++;
        return true;
    }

    
    public boolean removeFirst() {
        if(isEmpty()) return false;
        first.setData(null);//Help GC
        if(efectivo==1){
            first.setNext(null);
            first.setPrevious(null);
            first = null;
        }else{
            first.getPrevious().setNext(first.getNext());
            first.getNext().setPrevious(first.getPrevious());
            Node<E> tmp = first;
            first = first.getNext();
            tmp.setPrevious(null);
            tmp.setNext(null);
        }
        efectivo--;
        return true;
    }

    
    public boolean removeLast() {
        if(isEmpty()) return false;
        first.getPrevious().setData(null);
        if(efectivo==1){
            first.setNext(null);
            first.setPrevious(null);
            first = null;
        }else{
            first.getPrevious().getPrevious().setNext(first);
            first.getPrevious().setData(null);
            first.getPrevious().setNext(null);
            Node<E> tmp = first.getPrevious();
            first.setPrevious(first.getPrevious().getPrevious());
            tmp.setNext(null);
        }
        efectivo--;
        return true;
    }

   
    public boolean isEmpty() {
        return efectivo==0;
    }

   
    public CircularLinkedList<E> slicing(int start, int end) {
        CircularLinkedList cdll = new CircularLinkedList();
        if(!(start>=end||start<0||start>=efectivo)){
            if(end>efectivo) end = efectivo;
            Node<E> node = getNode(start);
            for(int i=start;i<end;i++){
                cdll.addLast(node.getData());
                node = node.getNext();
            }
        }
        return cdll;
    }
    
    private Node<E> getNode(int index){
        Node<E> p = first;
        for(int i=0;i<index;i++)p=p.getNext();
        return p;
    }
    
    
    public E getFirst() {
        if(isEmpty()) return null;
        return first.getData();
    }

    
    public E getLast() {
        if(isEmpty()) return null;
        return first.getPrevious().getData();
    }

    
    public int size() {
        return efectivo;
    }

    
    public boolean contains(E element) {
        if(element==null||isEmpty()) return false;
        Node<E> p = first;
        do{
            if(element.equals(p.getData()))return true;
            p = p.getNext();
        } while(p!=first);
        return false;
    }

    public boolean set(int index, E element) {
        if(index<0||index>=efectivo||element==null) return false;
        Node<E> node = getNode(index);
        node.setData(element);
        return true;
    }

    
    public E get(int index) {
        if(index<0||index>=efectivo) return null;
        return getNode(index).getData();
    }

   
    public boolean insert(int index, E element) {
        if(index == 0) return addFirst(element);
        if(index==efectivo) return addLast(element);
        if(index<0||index>efectivo||element==null) return false;
        Node<E> node = new Node(element);
        Node<E> p = getNode(index-1);
        node.setNext(p.getNext());
        node.setPrevious(p);
        p.getNext().setPrevious(node);
        p.setNext(node);
        efectivo++;
        return true;
    }

    
    public boolean remove(int index) {
        if(index==0) return removeFirst();
        if(index==efectivo-1) removeLast();
        if(index<0||index>=efectivo)return false;
        Node<E> node = getNode(index);
        node.setData(null);
        node.getNext().setPrevious(node.getPrevious());
        node.getPrevious().setNext(node.getNext());
        node.setPrevious(null);
        node.setNext(null);
        efectivo--;
        return true;
    }

    
    public void reverse() {
        if(!isEmpty()){
            Node<E> oldFirst = first;
            Node<E> q = first.getPrevious();
            Node<E> p = first;
            first = q;
            do{
                Node<E> tmpP=p.getPrevious();
                Node<E> tmpN=p.getNext();
                p.setPrevious(tmpN);
                p.setNext(tmpP);
                p = tmpN;
            }while(p!=oldFirst);
        }
    }
    
    @Override
    public String toString(){
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> p = first;
        do{
            sb.append(p.getData()).append(",");
            p = p.getNext();
        }while(p!=first);
        return sb.substring(0,sb.length()-1)+"]";
    }
    
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof CircularLinkedList)) return false;
        CircularLinkedList<E> other = (CircularLinkedList<E>) o;
        if(efectivo!=other.efectivo) return false;
        Node<E> p = first;
        Node<E> q = other.first;
        do{
            if(!p.getData().equals(q.getData())) return false;
            p = p.getNext();
            q = q.getNext();
        } while(p!=first);
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.first);
        hash = 43 * hash + this.efectivo;
        return hash;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator(){
            private Node<E> p = first;
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public E next() {
                if(p==null) throw new NoSuchElementException();
                p = p.getNext();
                return p.getPrevious().getData();
            }
        
            
        };
    }
}

class Node<E>{
    private E data;
    private Node<E> next;
    private Node<E> previous;
    
    public Node(E element){
        data = element;
    }
    public E getData(){
        return data;
    }
    
    public Node<E> getNext(){
        return next;
    }
    
    public Node<E> getPrevious(){
        return previous;
    }
    
    public void setData(E data){
        this.data = data;
    }
    
    public void setNext(Node <E> next){
        this.next = next;
    }
    
    public void setPrevious(Node<E> previous){
        this.previous = previous;
    }
}
