public class Person {
    private String name;
    private String sex;

    public Person(String name) {
        this.name = name;
        this.sex = "male";
    }

    public void print() {
        System.out.println(this.name);
    }

    public String getName() {
        return this.name;
    }
}
