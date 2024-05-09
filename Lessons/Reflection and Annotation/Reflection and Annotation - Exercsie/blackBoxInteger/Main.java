package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        Class<BlackBoxInt> blackBoxInt = BlackBoxInt.class;

        Method[] declaredMethods = blackBoxInt.getDeclaredMethods();

        Constructor<?>[] declaredConstructors = blackBoxInt.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[1];
        constructor.setAccessible(true);

        BlackBoxInt blackBox = (BlackBoxInt) constructor.newInstance();
        Field[] declaredFields = blackBoxInt.getDeclaredFields();
        Field innerValue = declaredFields[1];

        String line = scanner.nextLine();
        while (!"END".equals(line)) {

            String[] tokens = line.split("_");
            String methodName = tokens[0];
            int commandNumber = Integer.parseInt(tokens[1]);

            Method currentCommandMethod = blackBoxInt.getDeclaredMethod(methodName, int.class);
            currentCommandMethod.setAccessible(true);
            currentCommandMethod.invoke(blackBox, commandNumber);

            innerValue.setAccessible(true);
            System.out.println(innerValue.get(blackBox));

            line = scanner.nextLine();
        }
    }
}
