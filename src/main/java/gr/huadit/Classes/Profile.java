package gr.huadit.Classes;

public class Profile {
    private String Name;
    private int age;
    private double weight;
    private double height;
    private String gender;

    public Profile(String name, int age, double weight, double height, String gender) {
        this.Name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
