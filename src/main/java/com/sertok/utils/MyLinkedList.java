package com.sertok.utils;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T>,IMyLinkedList<T> {
    public static int INIT_CAPACITY = 3;
    private Object[] arr = new Object[INIT_CAPACITY];
    private int index = 0;
    private int level = 0;
    private Node<T> first = new Node<>(null, null, (T[]) arr);
    private Node last = first;
    private Node current = null;
    private int size = 0;


    public MyLinkedList() {
        first.next = last.next;
    }

    public MyLinkedList(int size) {
        INIT_CAPACITY = size;
        arr = new Object[INIT_CAPACITY];
        first.next = last.next;
    }

    public void add(T i) {
        if (index == INIT_CAPACITY) {
            arr = new Object[INIT_CAPACITY];
            last.next = new Node<>(last, null, (T[]) arr);
            index = 0;
            last = last.next;
        }
        arr[index] = i;
        index++;
        size++;
    }


    public T get(int i) {
        T value;
        int level = i / INIT_CAPACITY;
        int index = i % INIT_CAPACITY;
        if (this.current == null || this.level > level) {
            this.level = 0;
            this.current = first;
        }
        for (int j = this.level; j < level; j++) {
            this.level = level;
            current = current.next;
        }
        value = (T) current.value[index];
        return value;
    }
    public T getFirst(){
        return first.value[0];
    }

    public T getLast(){
        int i=0;
        while (i<INIT_CAPACITY&&last.value[i]!=null)
            i++;
        return (T) last.value[i-1];
    }

    public void removeLast(){
        if(last.value[0]==null){
            last=last.prev;
        }
        int i=0;
        while (i<INIT_CAPACITY&&last.value[i]!=null)
            i++;
        last.value[i-1]=null;
    }


    /*  public void remove(int i) {
        get(i);
        countRemove++;
        Object[] arr = new Object[this.current.value.length - 1];
        current.value[i % INIT_CAPACITY] = null;
        T value;
        int index = 0;
        for (int j = 0; j < this.current.value.length; j++) {
            value = (T) this.current.value[j];
            if (value != null) {
                arr[index] = value;
                index++;
            }

        }
        Node newNode = new Node(this.current.prev, this.current.next, arr);
        if (this.current.prev != null)
            this.current.prev.next = newNode;
        if (this.current.next != null)
            this.current.next.prev = newNode;
        this.current = newNode;
    }*/

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator<>(first);
    }
}
