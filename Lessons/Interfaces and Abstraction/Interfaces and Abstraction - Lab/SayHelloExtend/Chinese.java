package SayHelloExtend;

public class Chinese extends BasePerson{
    public Chinese(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return this.getName();
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }
}
