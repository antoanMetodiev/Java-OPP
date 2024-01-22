package Тelephony;

import Тelephony.Callable;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers; // phoneNumbers
    private List<String> urls;    // browseUrls

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    public List<String> getNumbers() {
        return this.numbers;
    }

    public List<String> getUrls() {
        return this.urls;
    }

    @Override
    public String browse() {
        for (int i = 0; i < this.urls.get(0).length(); i++) {
            char symbol = this.urls.get(0).charAt(i);
            if (Character.isDigit(symbol)) {
                return "Invalid URL!";
            }
        }
        return String.format("Browsing: %s!", this.getUrls().get(0));
    }

    @Override
    public String call() {
        for (int i = 0; i < this.numbers.get(0).length(); i++) {
            char symbol = this.numbers.get(0).charAt(i);
            if (!Character.isDigit(symbol)) {
                return "Invalid number!";
            }
        }
        return String.format("Calling... %s", this.getNumbers().get(0));
    }
}
