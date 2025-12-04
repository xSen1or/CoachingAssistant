package gr.huadit.Classes;

/*

    Record classes: Με λίγα λόγια απλα ειναι DTO's (Data Transfer Objects)

 */

public record Profile(String name, int age, double weight, double height, String gender) {
    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nWeight: " + weight + "\nHeight: " + height + "\nGender: " + gender;
    }
}
