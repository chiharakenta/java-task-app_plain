package model;
import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private Boolean done;

    // コンストラクタ
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    // ゲッター
    public String getName() {
        return name;
    }

    public Boolean getDone() {
        return done;
    }
}
