package SayHelloExtend;

public abstract class BasePerson implements Person {
    private String name;

    protected BasePerson(String name) {
        this.name = name;
    }
}
