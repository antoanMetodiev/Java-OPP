package Reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class reflectionClass = Reflection.class;

        System.out.println(reflectionClass);
        System.out.println(reflectionClass.getSuperclass());
        for (Class currentInterface : reflectionClass.getInterfaces()) {
            System.out.println(currentInterface);
        }

        Object reflectionObj = reflectionClass.getDeclaredConstructor().newInstance();
        System.out.println(reflectionObj);
    }
}
