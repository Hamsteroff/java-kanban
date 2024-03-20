package com.yandex.app.alex.model;

import com.yandex.app.alex.services.Status;

public class Subtask extends Task {
    private int epicIdForSubtask;

    public Subtask(String title, String description) {
        super(title, description, Status.NEW);
        epicIdForSubtask = 0;
    }

    public void setEpicIdForSubtask(int id) {
        if (epicIdForSubtask == 0) {
            epicIdForSubtask = id;
        } else {
            System.out.println("У подзадачи уже есть эпик");
        }
    }

    public int getEpicIdForSubtask() {
        return epicIdForSubtask;
    }
}
