import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> taskMap;
    private final HashMap<Integer, Subtask> subtaskMap;
    private final HashMap<Integer, Epic> epicMap;
    private int id;

    public TaskManager() {
        taskMap = new HashMap<>();
        subtaskMap = new HashMap<>();
        epicMap = new HashMap<>();
        id = 0;
    }


    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtaskMap.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicMap.values());
    }


    public void deleteTasks() {
        taskMap.clear();
    }

    public void deleteSubtasks() {
        for (Epic epic : epicMap.values()) {
            epic.getSubtasksForEpic().clear();
            changeStatusEpic(epic);
        }
        subtaskMap.clear();
    }

    public void deleteEpics() {
        epicMap.clear();
        subtaskMap.clear();
    }


    public Task getTaskId(int id) {
        return taskMap.get(id);
    }

    public Subtask getSubtaskId(int id) {
        return subtaskMap.get(id);
    }

    public Epic getEpicId(int id) {
        return epicMap.get(id);
    }


    public void newTask(Task task) {
        if (!taskMap.containsValue(task)) {
            id++;
            task.setId(id);
            taskMap.put(task.getId(),task);
        } else {
            System.out.println("Такая задача уже есть");
        }
    }

    public void newSubtask(Subtask subtask) {
        if (!subtaskMap.containsValue(subtask)) {
            id++;
            subtask.setId(id);
            Epic epic = epicMap.get(subtask.getEpicIdForSubtask());
            if (epic == null) {
                System.out.println("Невозможно сохранить подзадачу");
                return;
            }
            epic.addSubtasksForEpic(subtask);
            changeStatusEpic(epic);
            subtaskMap.put(subtask.getId(),subtask);
        } else {
            System.out.println("Такая подзадача уже есть");
        }
    }

    public void newEpic(Epic epic) {
        if (!epicMap.containsValue(epic)) {
            id++;
            epic.setId(id);
            epicMap.put(epic.getId(), epic);
        } else {
            System.out.println("Такой эпик уже есть");
        }
    }


    public void updateTask(Task task) {
        Task taskUpdate = taskMap.get(task.getId());
        taskUpdate.setTitle(task.getTitle());
        taskUpdate.setDescription(task.getDescription());
        taskUpdate.setStatus(task.getStatus());
    }

    public void updateSubtask(Subtask subtask) {
        Subtask subtaskUpdate = subtaskMap.get(subtask.getId());
        subtaskUpdate.setTitle(subtask.getTitle());
        subtaskUpdate.setDescription(subtask.getDescription());
        subtaskUpdate.setStatus(subtask.getStatus());
        changeStatusEpic(epicMap.get(subtask.getEpicIdForSubtask()));
    }

    public void updateEpic(Epic epic) {
        Epic epicUpdate = epicMap.get(epic.getId());
        epicUpdate.setTitle(epic.getTitle());
        epicUpdate.setDescription(epic.getDescription());
    }


    public void deleteTaskById(int id) {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
        }
    }

    public void deleteSubtaskById(int id) {
        if (subtaskMap.containsKey(id)) {
            Subtask subtask = subtaskMap.get(id);
            Epic epic = epicMap.get(subtask.getEpicIdForSubtask());
            epic.deleteSubtaskForEpic(id);
            changeStatusEpic(epic);
            subtaskMap.remove(id);
        }
    }

    public void deleteEpicById(int id) {
        if (epicMap.containsKey(id)) {
            HashMap<Integer, Subtask> subtasksEpic = epicMap.get(id).getSubtasksForEpic();
            for (Integer subtasksId : subtasksEpic.keySet()) {
                subtaskMap.remove(subtasksId);
            }
            epicMap.remove(id);
        }
    }


    public ArrayList<Subtask> getSubtasksEpic(int id) {
        HashMap<Integer, Subtask> subtasksEpic = epicMap.get(id).getSubtasksForEpic();
        return new ArrayList<>(subtasksEpic.values());
    }


    private void changeStatusEpic(Epic epic) {
        if (epic.getSubtasksForEpic().isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            int newStatus = 0;
            int doneStatus = 0;
            HashMap<Integer, Subtask> forStatus = epic.getSubtasksForEpic();
            for (Subtask subtaskCopy : forStatus.values()) {
                if (subtaskCopy.getStatus() == Status.IN_PROGRESS) {
                    epic.setStatus(Status.IN_PROGRESS);
                    return;
                } else if (subtaskCopy.getStatus() == Status.NEW) {
                    newStatus++;
                } else if (subtaskCopy.getStatus() == Status.DONE) {
                    doneStatus++;
                }
            }
            if (newStatus == forStatus.size()) {
                epic.setStatus(Status.NEW);
            } else if (doneStatus == forStatus.size()) {
                epic.setStatus(Status.DONE);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        }
    }


}
