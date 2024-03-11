package partTwo.restLessonPOJO8;

public class Homework {
    public static void main(String[] args) {
        Role admin = new Role(2, "admin");
        Role client = new Role(3, "client");
        Role admin2 = new Role(2, "admin");
        Role admin3 = new Role(2, "admin");

        System.out.println("Рефлексивность - объект равен самому себе: " + admin.equals(admin));
        System.out.println("Симметричность - если А не равно Б, то Б не равно А: " + (admin.equals(client) == client.equals(admin)));
        System.out.println("Транзитивность - если А равно Б, а Б равно C, то A должно быть равно C: " +
                (admin.equals(admin2) == admin2.equals(admin3) == admin.equals(admin3)));
        System.out.println("Консистентность - павторные вызовы должны возвращать одно и то же значение: " +
                (admin.equals(admin2) == admin.equals(admin2) == admin.equals(admin2)));
        System.out.println("Условие с NULL - при сравнении объекта с NULL должно возвращать false: " +
                (false == admin.equals(null)));
    }
}
