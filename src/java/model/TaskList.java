package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static TaskList instance;
    private static final String DATA_FILE_PATH = "db/taskList.dat";
    private List<Task> tasks = new ArrayList<Task>();

    // <初期化処理>
    private TaskList() {};
    public static TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
            instance.load();
        }
        return instance;
    }
    // </初期化処理>


    // <ゲッター>
    public List<Task> getTasks() {
        return this.tasks;
    }
    // </ゲッター>


    // <タスクリストの操作>
    public void add(String taskName) {
        Task newTask = new Task(taskName);
        this.tasks.add(newTask);
        save();
    }
    // </タスクリストの操作>


    // <ファイルの読み書き>
    private void save() {
        try (
            FileOutputStream fos = new FileOutputStream(DATA_FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(this.tasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void load() {
        try (
            FileInputStream fis = new FileInputStream(DATA_FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                this.tasks = (List<Task>) obj;
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // </ファイルの読み書き>
}
