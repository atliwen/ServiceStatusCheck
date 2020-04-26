package com.atliwen.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.validation.Valid;
import java.util.*;

public class ValidList<E> implements List<E>
{
    @Valid
    private List<E> list = new LinkedList();

    public ValidList() {
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    public boolean add(E e) {
        return this.list.add(e);
    }

    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    public boolean containsAll(Collection c) {
        return this.list.containsAll(c);
    }

    public boolean addAll(Collection c) {
        return this.list.addAll(c);
    }

    public boolean addAll(int index, Collection c) {
        return this.list.addAll(index, c);
    }

    public boolean removeAll(Collection c) {
        return this.list.removeAll(c);
    }

    public boolean retainAll(Collection c) {
        return this.list.retainAll(c);
    }

    public void clear() {
        this.list.clear();
    }

    public E get(int index) {
        return this.list.get(index);
    }

    public E set(int index, E element) {
        return this.list.set(index, element);
    }

    public void add(int index, E element) {
        this.list.add(index, element);
    }

    public E remove(int index) {
        return this.list.remove(index);
    }

    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    public ListIterator<E> listIterator() {
        return this.list.listIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return this.list.listIterator(index);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

    @JsonGetter("list")
    public List<E> getList() {
        return this.list;
    }

    @JsonSetter("list")
    public void setList(List<E> list) {
        this.list = list;
    }
}
