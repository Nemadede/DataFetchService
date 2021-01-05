package org.datafetch.service.utils;

import java.util.NoSuchElementException;

public class Queue {
    private class Node{
        private Object value;
        private Node next;

        public Node(Object item){this.value = item;}
    }

    private Node front; // first
    private Node rear; // last
    private int count;

    private static volatile Queue queueInstance;
    private Queue(){}

    public static Queue getInstance(){
        if(queueInstance == null){
            queueInstance = new Queue();
        }
        return queueInstance;
    }


    public boolean isEmpty(){
        return front == null;
    }


    public void enqueue(Object item){
        Node node = new Node(item);

        if(isEmpty())
            front = rear = node;
        else {
            rear.next = node;
            rear = node;
        }

        count ++;
    }

    public Object dequeue(){
        if (isEmpty())
            throw new NoSuchElementException();

        if (front == rear){
            front = rear = null;
            count --;
            return front.value;
        }

        Node second = front.next;
        Object val = front.value;
        front.next = null;
        front = second;
        count --;
        return val;

    }

    public Integer getSize(){
        return count;
    }
    public void print(){
//        Integer list [] = new Integer[count];
        Node current = front;
        for (int i=0; i<count; i++) {
            System.out.println(current.value);
            current = current.next;
        }
    }


}
