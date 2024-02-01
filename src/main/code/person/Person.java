package code.person;

public class Person {

    String name;        // имя
    int age;            // возраст

    //Конструктор 1
    Person()
    {
        this("Undefined", 18);
    }

    //Конструктор 2
    Person(String name)
    {
        this(name, 18);
    }

    //Конструктор 3
    Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    void displayInfo(){
        System.out.printf("Name: %s \tAge: %d\n", name, age);
    }
}