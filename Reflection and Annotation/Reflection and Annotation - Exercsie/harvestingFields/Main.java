package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        Field[] allFields = RichSoilLand.class.getDeclaredFields();
        Field[] publicFields = RichSoilLand.class.getFields();

        while (!"HARVEST".equals(line)) {

            List<Field> currentTypeFields = new ArrayList<>();
            switch (line) {
                case "private":
                    for (Field field : allFields) {
                        if (Modifier.isPrivate(field.getModifiers())) {
                            field.setAccessible(true);
                            currentTypeFields.add(field);
                        }
                    }
                    currentTypeFields.forEach(e -> System.out.printf("private %s %s\n",
                            e.getType().getSimpleName(), e.getName()));
                    break;
                case "protected":
                    for (Field field : allFields) {
                        if (Modifier.isProtected(field.getModifiers())) {
                            field.setAccessible(true);
                            currentTypeFields.add(field);
                        }
                    }
                    currentTypeFields.forEach(e -> System.out.printf("protected %s %s\n",
                            e.getType().getSimpleName(), e.getName()));
                    break;
                case "public":
                    Arrays.stream(publicFields).forEach(e -> System.out.printf("public %s %s\n",
                            e.getType().getSimpleName(), e.getName()));
                    break;
                case "all":
                    for (Field field : allFields) {
                        int modifiers = field.getModifiers();
                        String accessModifier = "";

                        if (Modifier.isPrivate(modifiers)) {
                            accessModifier = "private";
                        } else if (Modifier.isProtected(modifiers)) {
                            accessModifier = "protected";
                        } else if (Modifier.isPublic(modifiers)) {
                            accessModifier = "public";
                        }
                        System.out.printf("%s %s %s\n"
                                , accessModifier, field.getType().getSimpleName(), field.getName());
                    }
                    break;
            }
            line = scanner.nextLine();
        }
    }
}
