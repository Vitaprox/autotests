package code.person;

public class Program {

    public static void main(String[] args) {
        Pet snow = new Pet("Snow", 16);
        snow.getAllInformation();
        snow.setColor("grey");
        snow.getAllInformation();

        Pet bars = new Pet("Bars", 5, "Meow");
        bars.sayVoice();
        System.out.println(bars.getAge());

        Pet rex = new Pet("Rex", 7, "ProPlan", "bark", "black");
        rex.sayVoice();
    }
}
