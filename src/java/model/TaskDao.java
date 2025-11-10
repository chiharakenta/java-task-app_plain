package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TaskDao implements Serializable {
    private static TaskDao instance;
    private static final String DATA_FILE_PATH = "db/taskList.json";
    private int nextId = 1;
    private List<Task> tasks = new ArrayList<Task>();

    // <初期化処理>
    private TaskDao() {};
    public static TaskDao getInstance() {
        if (instance == null) {
            load();
        }
        if (instance == null) {
            instance = new TaskDao();
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

    public void update(int id, String taskName) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setName(taskName);
            save();
        }
    }
    // </タスクリストの操作>


    // <ファイルの読み書き>
    public void save() {
        try (
            FileWriter fw = new FileWriter(DATA_FILE_PATH);
        ) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, fw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void load() {
        try (
            FileReader fr = new FileReader(DATA_FILE_PATH);
        ) {
            Gson gson = new Gson();
            instance = gson.fromJson(fr, TaskDao.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // </ファイルの読み書き>
}
