package co.edu.uptc.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SimpleList<T> implements List<T> {
    private T[] array;

    public SimpleList() {
        array = (T[]) java.lang.reflect.Array.newInstance(Object.class, 0);
    }

    public SimpleList(int length) {
        array = (T[]) java.lang.reflect.Array.newInstance(Object.class, length);
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array == null;
    }

    @Override
    public boolean contains(Object o) {
        for(T obj : array)
            if(obj.equals(o)) return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public Object[] toArray() {
        return (Object[]) array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public boolean add(T e) {
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), array.length + 1);
        for(int i = 0; i < newArray.length; i++) {
            if(i == newArray.length + 1) {
                newArray[i] = e;
                array = newArray;
                return true;
            }
            newArray[i] = array[i];
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), array.length - 1);
        boolean isRemoved = false;
        for(int i = 0; i < array.length; i++) {
            if(array[i].equals(o)) {
                isRemoved = true;
                continue;
            }
            newArray[i] = array[i];
        }
        if(isRemoved) array = newArray;
        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        array = (T[]) java.lang.reflect.Array.newInstance(Object.class, 0);
    }

    @Override
    public T get(int index) {
        return index >= array.length ? array[index] : null;
    }

    @Override
    public T set(int index, T element) {
        if(index < array.length) {
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
    public T remove(int index) {
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), array.length - 1);
        T removed = null;
        for(int i = 0; i < array.length; i++) {
            if(i == index) {
                removed = array[i];
                continue;
            }
            newArray[i] = array[i];
        }
        if(removed != null) array = newArray;
        return removed;
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
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
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
