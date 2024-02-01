package code.person;

public class Pet {

    private String name;
    private int age;
    private String food;
    private String voice;
    private String color;

    Pet(String name, int age)
    {
        this(name, age, null, null, null);
    }

    Pet(String name, int age, String voice)
    {
        this(name, age, null, voice, null);
    }

    Pet(String name, int age, String food, String voice, String color)
    {
        this.name = name;
        this.age = age;
        this.food = food;
        this.voice = voice;
        this.color = color;
    }

    public void sayVoice() {
        System.out.println(voice);
    }

    public void getAllInformation() {
        System.out.println("Имя: " + this.name + "\n" +
                "Возраст: " + this.age + "\n" +
                "Еда: " + this.food + "\n" +
                "Издает звук: " + this.voice + "\n" +
                "Цвет: " + this.color);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFood() {
        return food;
    }

    public String getVoice() {
        return voice;
    }

    public String getColor() {
        return color;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
