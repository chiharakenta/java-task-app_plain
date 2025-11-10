package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.DataStore;

public class TaskDao implements Serializable {
    private static TaskDao instance;
    private static final String DATA_FILE_PATH = "db/tasks.json";
    private int nextId = 1;
    private List<Task> tasks = new ArrayList<Task>();

    // <初期化処理>
    private TaskDao() {};
    public static TaskDao getInstance() {
        if (instance == null) {
            instance = DataStore.load(DATA_FILE_PATH, TaskDao.class);
        }
        if (instance == null) {
            instance = new TaskDao();
        }
        return instance;
    }
    // </初期化処理>


    // <ゲッター>
    public List<Task> findAll() {
        return this.tasks.stream()
            .filter(task -> !task.getDone())
            .toList();
    }

    public List<Task> findDone() {
        return this.tasks.stream()
            .filter(task -> task.getDone())
            .toList();
    }

    public Task findById(int id) {
        return this.tasks.stream()
            .filter(task -> task.getId() == id)
            .findFirst()
            .orElse(null);
    }
    // </ゲッター>


    // <タスクリストの操作>
    public void create(String taskName) {
        Task newTask = new Task(nextId, taskName);
        nextId++;
        this.tasks.add(newTask);
        DataStore.save(DATA_FILE_PATH, this);
    }

    public void updateName(int id, String taskName) {
        Task task = findById(id);
        if (task != null) {
            task.setName(taskName);
            DataStore.save(DATA_FILE_PATH, this);
        }
    }

    public void setDone(int id, Boolean done) {
        Task task = findById(id);
        if (task != null) {
            task.setDone(done);
            DataStore.save(DATA_FILE_PATH, this);
        }
    }
}
