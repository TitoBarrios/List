package co.edu.uptc.test;

import co.edu.uptc.model.SimpleList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleListTest {
    private SimpleList<String> list;

    @BeforeEach
    void setUp() {
        list = new SimpleList<>();
        list.add("julian");
        list.add("hildovar");
        list.add("laura");
        list.add("carlos");
    }

    @Test
    void testIsEmpty() {
        assertFalse(list.isEmpty(), "La lista no debería estar vacía.");
        list.clear();
        assertTrue(list.isEmpty(), "La lista debería estar vacía después de clear().");
    }

    @Test
    void testSize() {
        assertEquals(4, list.size(), "La lista debería tener 4 elementos.");
        list.add("luz");
        assertEquals(5, list.size(), "La lista debería tener 5 elementos después de añadir uno.");
    }

    @Test
    void testAdd() {
        assertTrue(list.add("luz"), "El elemento debería agregarse correctamente.");
        assertEquals("luz", list.get(4), "El elemento 'luz' debería estar en la posición 4.");
    }

    @Test
    void testAddAtIndex() {
        list.add(2, "bryan");
        assertEquals("bryan", list.get(2), "El elemento 'bryan' debería estar en la posición 2.");
    }

    @Test
    void testAddAll() {
        List<String> newElements = List.of("miguel", "dario");
        assertTrue(list.addAll(newElements), "Se deberían agregar elementos adicionales.");
        assertEquals(6, list.size(), "La lista debería tener 6 elementos.");
    }

    @Test
    void testAddAllAtIndex() {
        List<String> newElements = List.of("bryan", "miguel");
        assertTrue(list.addAll(1, newElements), "Debería insertar la colección en el índice 1.");
        assertEquals("bryan", list.get(1), "El primer elemento insertado debería ser 'bryan'.");
        assertEquals("miguel", list.get(2), "El segundo elemento insertado debería ser 'miguel'.");
    }

    @Test
    void testRemoveByIndex() {
        String removed = list.remove(2);
        assertEquals("laura", removed, "Debería eliminar 'laura' de la posición 2.");
        assertEquals(3, list.size(), "La lista debería tener 3 elementos.");
    }

    @Test
    void testRemoveByObject() {
        assertTrue(list.remove("laura"), "Debería eliminar el elemento 'laura'.");
        assertFalse(list.contains("laura"), "La lista ya no debería contener 'laura'.");
    }

    @Test
    void testClear() {
        list.clear();
        assertTrue(list.isEmpty(), "La lista debería estar vacía después de clear().");
    }

    @Test
    void testContains() {
        assertTrue(list.contains("julian"), "La lista debería contener 'julian'.");
        assertFalse(list.contains("bryan"), "La lista no debería contener 'bryan'.");
    }

    @Test
    void testContainsAll() {
        List<String> sublist = List.of("julian", "laura");
        assertTrue(list.containsAll(sublist), "La lista debería contener todos los elementos de la sublista.");
    }

    @Test
    void testRetainAll() {
        List<String> toRetain = List.of("julian", "laura");
        assertTrue(list.retainAll(toRetain), "Se debería conservar solo los elementos especificados.");
        assertEquals(2, list.size(), "La lista debería tener 2 elementos después de retainAll().");
    }

    @Test
    void testRemoveAll() {
        List<String> toRemove = List.of("julian", "laura");
        assertTrue(list.removeAll(toRemove), "Debería eliminar los elementos especificados.");
        assertEquals(2, list.size(), "La lista debería tener 2 elementos después de removeAll().");
    }

    @Test
    void testIndexOf() {
        assertEquals(1, list.indexOf("hildovar"), "El índice de 'hildovar' debería ser 1.");
        assertEquals(-1, list.indexOf("bryan"), "Debería devolver -1 si el elemento no está.");
    }

    @Test
    void testLastIndexOf() {
        list.add("laura");
        assertEquals(4, list.lastIndexOf("laura"), "El último índice de 'laura' debería ser 4.");
    }

    @Test
    void testGet() {
        assertEquals("laura", list.get(2), "El elemento en el índice 2 debería ser 'laura'.");
    }

    @Test
    void testSet() {
        list.set(2, "miguel");
        assertEquals("miguel", list.get(2), "El elemento en el índice 2 debería ser 'miguel'.");
    }

    @Test
    void testToArray() {
        Object[] expected = {"julian", "hildovar", "laura", "carlos"};
        assertArrayEquals(expected, list.toArray(), "El array debería coincidir con los elementos de la lista.");
    }

    @Test
    void testSubList() {
        List<String> subList = list.subList(1, 3);
        assertEquals(2, subList.size(), "La sublista debería tener 2 elementos.");
        assertEquals("hildovar", subList.get(0), "El primer elemento de la sublista debería ser 'hildovar'.");
    }

    @Test
    void testOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(10), "Debería lanzar una excepción si el índice está fuera de rango.");
    }

    @Test
    void testIterator() {
        var iterator = list.iterator();
        assertTrue(iterator.hasNext(), "El iterador debería tener elementos.");
        assertEquals("julian", iterator.next(), "El primer elemento del iterador debería ser 'julian'.");
    }
}
