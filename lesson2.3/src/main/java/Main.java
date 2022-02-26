import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        task1();
        task2();

    }

    private static void task1() {
        Map<String, Integer> maps = new HashMap<>();
        String[] words = {
                "Кошка", "Солнце", "Дом",
                "Комната", "Игра", "Солнце",
                "Февраль", "Пони", "Скала", "Сон"
        };

        for (int i = 0; i < words.length; i++) {
            if (maps.containsKey(words[i])) {
                maps.put(words[i], maps.get(words[i]) + 1);
            }
            else {
                maps.put(words[i], 1);
            }
        }
        System.out.println(maps);
    }

    private static void task2() {
        Directory directory = new Directory();

        directory.add("Цаплин", "116");
        directory.add("Цаплин", "980");
        directory.add("Добров", "218");
        directory.add("Добров", "615");
        directory.add("Цыганов", "511");


        System.out.println(directory.get("Цаплин"));
        System.out.println(directory.get("Добров"));
        System.out.println(directory.get("Цыганов"));

    }
}
