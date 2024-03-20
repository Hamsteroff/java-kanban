package com.yandex.app.alex.model;

import com.yandex.app.alex.services.Status;

import java.util.HashMap;

public class Epic extends Task {

    private final HashMap<Integer, Subtask> subtasksForEpic;
    public Epic(String title, String description) {
        super(title, description, Status.NEW);
        subtasksForEpic = new HashMap<>();
    }

    public void addSubtasksForEpic(Subtask subtask) {
        if (!subtasksForEpic.containsKey(subtask.getId())) {
            subtasksForEpic.put(subtask.getId(), subtask);
        } else {
            System.out.println("Эта подзадача уже есть в Эпике");
        }
    }

    public HashMap<Integer, Subtask> getSubtasksForEpic() {
        return subtasksForEpic;
    }

    public void deleteSubtaskForEpic(int id) {
        if (subtasksForEpic.containsKey(id)) {
            subtasksForEpic.remove(id);
        } else {
            System.out.println("У этого Эпика нет такой подзадачи");
        }
    }
}
