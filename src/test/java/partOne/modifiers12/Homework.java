package partOne.modifiers12;

public class Homework {
    public static void main(String[] args) {
        News news = new News();
        RussianNews russianNews = new RussianNews();
        // Первый способ: вызов из класса в том же пакете
        news.getNews();
        // Второй способ вызов через наследника
        russianNews.getNews();
    }
}
