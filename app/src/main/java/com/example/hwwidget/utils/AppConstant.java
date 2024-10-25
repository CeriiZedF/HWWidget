package com.example.hwwidget.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstant {
    public static final List<String> CARD_ID_ELEMENT =
            Arrays.asList(
                        "task_radio_option_1",
                        "task_radio_option_2",
                        "task_radio_option_3",
                        "task_radio_option_4");

    public static final List<String> CARD_TEXT_TASK1 =
            Arrays.asList(
                    "Какой язык программирования является объектно-ориентированным?",
                    "Java", // (правильный ответ)
                    "HTML",
                    "CSS",
                    "SQL"
            );

    public static final List<String> CARD_TEXT_TASK2 =
            Arrays.asList(
                    "Что такое инкапсуляция?",
                    "Скрытие данных", // (правильный ответ)
                    "Наследование",
                    "Полиморфизм",
                    "Агрегация"
            );

    public static final List<String> CARD_TEXT_TASK3 =
            Arrays.asList(
                    "Какой из этих типов данных является примитивным в Java?",
                    "int",            // (правильный ответ)
                    "String",
                    "ArrayList",
                    "HashMap"
            );

    public static final List<String> CARD_TEXT_TASK4 =
            Arrays.asList(
                    "Что такое интерфейс в Java?",
                    "Абстрактный класс",
                    "Класс без методов", // (правильный ответ)
                    "Класс с реализацией",
                    "Свойство класса"
            );

    public static final List<String> CARD_TEXT_TASK5 =
            Arrays.asList(
                    "Что делает оператор `==` в Java?", // Вопрос 5
                    "Сравнивает ссылки", // (правильный ответ)
                    "Сравнивает значения",
                    "Проверяет тип",
                    "Преобразует тип"
            );
}
