package SayHelloExtend;

public class Bulgarian extends BasePerson {
    public Bulgarian(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return this.getName();
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
