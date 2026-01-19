package gr.huadit.DTO;

/*
    DTO Class.
 */

import gr.huadit.Interfaces.DTO;

public record Profile(String name, int age, double weight, double height, String gender) implements DTO {
    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nWeight: " + weight + "\nHeight: " + height + "\nGender: " + gender;
    }
}
