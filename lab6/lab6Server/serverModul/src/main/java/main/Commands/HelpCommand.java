package main.Commands;

import interfaces.Command;

public class HelpCommand implements Command {

    @Override
    public void execute(){}
    @Override
    public String descr(){
        return ("\n====================\n\n" +
                "help - доступные команды\n" +
                "info - информация о коллекции\n" +
                "show - вывод всей коллекции\n" +
                "add - добавить новый элемент в коллекцию\n" +
                "update id - обновить значение элемента по id\n" +
                "remove_by_id id - удалить элемент из коллекции по его id\n" +
                "clear - очистить коллекцию\n" +
                "execute_script file_name - считать и исполнить скрипт\n" +
                "exit - завершить программу\n" +
                "add_if_max - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "sort - отсортировать коллекцию в естественном порядке\n" +
                "history - вывести последние 6 команд\n" +
                "group_counting_by_chapter - сгруппировать элементы коллекции по значению поля chapter, вывести количество элементов в каждой группе\n" +
                "count_less_than_chapter chapter - вывести количество элементов, значение поля chapter которых меньше заданного\n" +
                "count_greater_than_weapon_type weaponType - вывести количество элементов, значение поля weaponType которых больше заданного\n" +
                "\n====================\n");
    }
}
