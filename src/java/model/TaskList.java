package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private static TaskList instance;
    private static final String DATA_FILE_PATH = "db/taskList.dat";
    private int nextId = 1;
    private List<Task> tasks = new ArrayList<Task>();

    // <初期化処理>
    private TaskList() {};
    public static TaskList getInstance() {
        if (instance == null) {
            load();
        }
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }
    // </初期化処理>


    // <ゲッター>
    public List<Task> getTasks() {
        return this.tasks.stream()
            .filter(task -> !task.getDone())
            .toList();
    }

    public List<Task> getDoneTasks() {
        return this.tasks.stream()
            .filter(task -> task.getDone())
            .toList();
    }

    public int getNextId() {
        return nextId;
    }

    public Task findTaskById(int id) {
        return this.tasks.stream()
            .filter(task -> task.getId() == id)
            .findFirst()
            .orElse(null);
    }
    // </ゲッター>


    // <タスクリストの操作>
    public void add(String taskName) {
        Task newTask = new Task(nextId, taskName);
        nextId++;
        this.tasks.add(newTask);
        save();
    }
    // </タスクリストの操作>


    // <ファイルの読み書き>
    public void save() {
        try (
            FileOutputStream fos = new FileOutputStream(DATA_FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void load() {
        try (
            FileInputStream fis = new FileInputStream(DATA_FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            Object obj = ois.readObject();
            if (obj instanceof TaskList) {
                instance = (TaskList) obj;
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // </ファイルの読み書き>
}
