package model;
import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private Boolean done;

    // コンストラクタ
    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.done = false;
    }

    // ゲッター
    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Boolean getDone() {
        return done;
    }

    // セッター
    public void setName(String name) {
        this.name = name;
    }

    public void check() {
        this.done = true;
    }
}
