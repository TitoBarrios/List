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

    public SimpleList(T[] array) {
        this.array = array;
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
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public T next() {
                if (!hasNext())
                    return null;
                index += 1;
                return array[index];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return (Object[]) array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) array;
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
        array = newArray;
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
        for(int i = 0; i < array.length; i++)
            for(int j = 0; i < c.size(); j++){
                if(c.contains(array[i])) break;
                if(j == c.size() - 1) return false;
            }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(Object.class, array.length + c.size());
        Iterator iterator = c.iterator();
        for(int i = 0; i < newArray.length; i++) {
            if(i >= array.length){
                newArray[i] = (T) iterator.next();
                continue;
            }
            newArray[i] = array[i];
        }
        array = newArray;
        return true;
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
        return index >= array.length ? array[index] : null;
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
        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), toIndex - fromIndex);
        for(int i = 0; i < newArray.length; i++)
            newArray[i] = array[i + fromIndex];
        return new SimpleList<>(newArray);
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
