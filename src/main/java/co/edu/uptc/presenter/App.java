package co.edu.uptc.presenter;

import java.util.ArrayList;

import co.edu.uptc.model.SimpleList;
import co.edu.uptc.view.Console;

public class App {
    private SimpleList<Integer> list;
    public App(){
        list = new SimpleList<>();

    }
    
    public void run() {
             
    }

    private void testAddInt(){
        list.add(5);
        list.add(6);
        list.add(1);
        list.add(10);
        list.add(38);
    }

    private void testAddIndex(){
        list.add(2, 100);
    }

    private void testContains(){
        list.contains(5);
    }

    private void testAddColection(){
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list.addAll(list2);
    }

    private void testSet(){
        list.set(0, 40);
    }

    private void testRemove(){
        list.remove(3);
    }

    private String testIndexOf(){
        return String.valueOf(list.indexOf(1));
    }

    private String testLastIndexOf(){
        return String.valueOf(list.lastIndexOf(1));
    }

    private void testAddAllParametrized(){
        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(5);
        list3.add(10);
        list3.add(15);
        list3.add(20);
        list.addAll(list3);
    }

    private void printList(){
        StringBuilder numbers = new StringBuilder();
        for (Integer integer : list) {
            numbers.append(">").append(integer);
        }
        Console.log(numbers.toString());
    }


    public static void main(String[] args) {
        App app = new App();
        Console.initialize();
        app.run();
    }
}
