package animals;

public class Animal {
    // "{име} {възраст} {пол}".
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    private void setGender(String gender) {
        if (gender.equals("Male") || gender.equals("Female")) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    private void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String produceSound() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s\n" +
                        "%s %d %s\n" +
                        "%s"
                , this.getClass().getSimpleName(),
                this.name,
                this.age,
                this.gender,
                this.produceSound());
    }
}