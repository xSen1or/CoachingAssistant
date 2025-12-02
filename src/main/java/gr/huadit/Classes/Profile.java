package gr.huadit.Classes;

public class Profile {
    private final String name;
    private final int age;
    private final double weight;
    private final double height;
    private final String gender;

    public Profile(String name, int age, double weight, double height, String gender) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nWeight: " + weight + "\nHeight: " + height + "\nGender: " + gender;
    }
}
