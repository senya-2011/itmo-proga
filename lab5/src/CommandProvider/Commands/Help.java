package CommandProvider.Commands;

import java.sql.SQLOutput;

public class Help{
    public static void HelpCommand(){
        System.out.println("\n====================\n");

        System.out.println("help - доступные команды");
        System.out.println("info - информация о коллекции");
        System.out.println("show - вывод всей коллекции");
        System.out.println("add - добавить новый элемент в коллекцию");
        System.out.println("update id {element} - обновить значение элемента по id");
        System.out.println("remove_by_id id - удалить элемент из коллекции по его id");
        System.out.println("clear - очистить коллекцию");
        System.out.println("save - сохранить коллекцию в файл");
        System.out.println("execute_script file_name - считать и исполнить скрипт");
        System.out.println("exit - завершить программу (без сохранения в файл)");
        System.out.println("add_if_max {element} - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("sort - отсортировать коллекцию в естественном порядке");
        System.out.println("history - вывести последние 6 команд");
        System.out.println("group_counting_by_chapter - сгруппировать элементы коллекции по значению поля chapter, вывести количество элементов в каждой группе");
        System.out.println("count_less_than_chapter chapter - вывести количество элементов, значение поля chapter которых меньше заданного");
        System.out.println("count_greater_than_weapon_type weaponType - вывести количество элементов, значение поля weaponType которых больше заданного");
        System.out.println("cls - очистка консоли");

        System.out.println("\n====================\n");
    }
}
