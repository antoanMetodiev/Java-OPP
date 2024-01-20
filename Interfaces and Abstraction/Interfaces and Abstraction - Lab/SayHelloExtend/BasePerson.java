package SayHelloExtend;

public abstract class BasePerson implements Person {
    // -name: String
    //#BasePerson(name)
    //+getName(): String
    //-setName(): void

    private String name;

    protected BasePerson(String name) {
        this.name = name;
    }
}
