package co.edu.uptc.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SimpleList<T> implements List<T> {
    
    private Node head;

    public SimpleList() {
        head = null;
    }

    @Override
    public int size() {
        int counter = 0;
        Node<T> aux = head;
        while (aux != null) {
            counter++;
            aux = aux.getNext();
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean add(T e) {
        if (head == null) {
            head = new Node<>(e);
        } else {
            Node<T> aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(new Node<>(e));
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> aux = head;
        while (aux != null) {
            if (aux.getData().equals(o)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            Node<T> aux = head;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T data = aux.getData();
                aux = aux.getNext();
                return data;
            }
        };
        return iterator;
    }

    @Override
    public Object[] toArray() {
        int size = size();
        Object[] array = new Object[size];

        Node<T> aux = head;
        int i = 0;
        while (aux != null) {
            array[i++] = aux.getData();
            aux = aux.getNext();
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] result = a;
        if(a.length < size()){
            result = Arrays.copyOf(a, size());
        }
        for(int i = 0; i < size(); i++){
            result[i] = (T) a[i];
        }
        if(a.length > size()){
            for(int i = size(); i < a.length; i++){
                result[i] = null;
            }
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        if (head.getData().equals(o)) {
            head = head.getNext();
            return true;
        }
        Node<T> aux = head;
        while (aux.getNext() != null) {
            if (aux.getNext().getData().equals(o)) {
                aux.setNext(aux.getNext().getNext());
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if(index > array.length || index < 0) return false;
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(Object.class, array.length + c.size());
        Iterator iterator = c.iterator();
        for(int i = 0; i < newArray.length; i++) {
            if(i >= index && iterator.hasNext()) {
                newArray[i] = (T) iterator.next();
                continue;
            }
            if(!iterator.hasNext()) {
                newArray[i] = array[i - c.size()];
            }
            newArray[i] = array[i];
        }
        array = newArray;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isModified = false;
        for(int i = 0; i < array.length; i++)
            for(int j = 0; i < c.size(); j++)
                if(c.contains(array[i])) {
                    isModified = true;
                    remove(i);
                    break;
                }
        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isModified = false;
        for(int i = 0; i < array.length; i++)
            for(int j = 0; i < c.size(); j++)
                if(!c.contains(array[i])) {
                    isModified = true;
                    remove(i);
                    break;
                }
        return isModified;
    }

    @Override
    public void clear() {
        array = (T[]) java.lang.reflect.Array.newInstance(Object.class, 0);
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if(index < array.length && index > 0) {
            array[index] = element;
            return array[index];
        }
        return null;
    }

    @Override
    public void add(int index, T element) {
        if(index >= array.length || index < 0) return;
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), array.length + 1);
        for(int i = 0; i < newArray.length; i++) {
            if(i == index) {
                newArray[i] = element;
                continue;
            }
            newArray[i] = array[i > index ? i - 1 : i];
        }
        array = newArray;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        SimpleList<T> subList = new SimpleList<>();
        Node<T> aux = head;
        int index = 0;
        while (index < fromIndex && aux != null) {
            aux = aux.getNext();
            index++;
        }
        while (index < toIndex && aux != null) {
            subList.add(aux.getData());
            aux = aux.getNext();
            index++;
        }
        return subList;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < array.length; i++)
            if(array[i].equals(o))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastFoundIndex = -1;
        for(int i = 0; i < array.length; i++)
            if(array[i].equals(o))
                lastFoundIndex = i;
        return lastFoundIndex;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    // Excluidos
    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

}
