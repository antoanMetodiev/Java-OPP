package SayHelloExtend;

public class European extends BasePerson {
    public European(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return this.getName();
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
