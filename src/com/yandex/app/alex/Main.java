package com.yandex.app.alex;

import com.yandex.app.alex.model.Epic;
import com.yandex.app.alex.model.Subtask;
import com.yandex.app.alex.model.Task;
import com.yandex.app.alex.services.Status;
import com.yandex.app.alex.services.TaskManager;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Помыть машину", "привести в презентабельный вид для продажи", Status.NEW);
        taskManager.newTask(task1); // создали новую задачу

        task1.setStatus(Status.DONE);

        taskManager.updateTask(task1);

        Epic epic1 = new Epic("Пройти тему Практикума", "Тема включает 2 урока");
        Epic epic2 = new Epic("Купить стол", "Нужен компьютерный стол для работы");

        Subtask subtask1 = new Subtask("Пройти урок 1", "Тема урока: Инкапсуляция");
        Subtask subtask2 = new Subtask("Пройти урок 2", "Тема урока: Наследование");
        Subtask subtask3 = new Subtask("Выбрать стол на Озон", "Компьютерный стол не дороже 3000");

        taskManager.newEpic(epic1);
        taskManager.newEpic(epic2);

        subtask1.setEpicIdForSubtask(epic1.getId());
        subtask2.setEpicIdForSubtask(epic1.getId());
        subtask3.setEpicIdForSubtask(epic2.getId());

        taskManager.newSubtask(subtask1);
        taskManager.newSubtask(subtask2);
        taskManager.newSubtask(subtask3);

        System.out.println(taskManager.getTasks());
        System.out.println();
        System.out.println(taskManager.getSubtasks());
        System.out.println();
        System.out.println(taskManager.getEpics());

        subtask1.setStatus(Status.IN_PROGRESS);
        subtask2.setStatus(Status.NEW);
        subtask3.setStatus(Status.DONE);

        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        taskManager.updateSubtask(subtask3);

        System.out.println();
        System.out.println(taskManager.getSubtasks());
        System.out.println();
        System.out.println(taskManager.getEpics());

        taskManager.deleteSubtasks();
        System.out.println();
        System.out.println(taskManager.getSubtasks());
        System.out.println();
        System.out.println(taskManager.getEpics());



    }
}
