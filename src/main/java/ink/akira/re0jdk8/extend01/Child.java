package ink.akira.re0jdk8.extend01;

public class Child extends Parent {
    private String name;

    public Child() {
        super("Akira");
    }

    public void printName(){
        System.out.println("super.name: " + super.name);
        System.out.println("this.name: " + this.name);
    }
}
