package lessonEleven;

import org.junit.jupiter.api.Test;

public class homeWorkEleven {

    @Test
    public void task1() {
        String firstStr = "\"\nЯ помню чудное мгновенье";
        String secondStr = "\"\nПередо мной явилась ты";
        String thirdStr = "\"\nКак мимолетное виденье";
        String fourthStr = "\"\nКак гений чистой красоты!";
        System.out.println(String.join("\n\n", firstStr, secondStr, thirdStr, fourthStr));
    }

    @Test
    public void task2() {
        String firstWord = "Java";
        String secondWord = "лучший";
        String thirdWord = "язык";
        String fourthWord = "программирования";
        System.out.printf("%s - %s %s %s", firstWord, secondWord, thirdWord, fourthWord);
    }

    @Test
    public void task3() {
        String textLang ="Обожаю изучать новые языки";
        String str = textLang.substring(7);
        String str2 = textLang.substring(7, 14);
        System.out.println(str);
        System.out.println(str2);
    }

    @Test
    public void tasks4() {
        String textIndex = "Домашнее задание не проблема";
        int firstIndex = textIndex.indexOf("не");
        int lastIndex = textIndex.lastIndexOf("не");
        int lastIndex2 = textIndex.indexOf("не", 9);
        int lastIndex3 = textIndex.indexOf("не ");
        System.out.println(firstIndex);
        System.out.println(lastIndex);
        System.out.println(lastIndex2);
        System.out.println(lastIndex3);
    }

}
