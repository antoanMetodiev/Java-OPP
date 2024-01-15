package StackOfStrings;

import java.util.ArrayList;

public class StackOfStrings {

    // ⦁	Private field: data: ArrayList<String>
    //⦁	Public method: push(String item): void
    //⦁	Public method: pop(): String
    //⦁	Public method: peek(): String
    //⦁	Public method: isEmpty(): boolean
    private ArrayList<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public String peek() {
        return data.get(0);
    }

    public String pop() {
        return data.remove(0);
    }

    public void push(String item) {
        this.data.add(0, item);
    }
}
