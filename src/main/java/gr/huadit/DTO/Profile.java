package gr.huadit.DTO;

/*
    DTO Class.
 */

public record Profile(String name, int age, double weight, double height, String gender) {
    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nWeight: " + weight + "\nHeight: " + height + "\nGender: " + gender;
    }
}
