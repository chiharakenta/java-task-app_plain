package util;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataStore {

    public static void save(String path, Object obj) {
        try (
            FileWriter fw = new FileWriter(path);
        ) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(obj, fw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T load(String path, Class<T> classOfT) {
        try (
            FileReader fr = new FileReader(path);
        ) {
            Gson gson = new Gson();
            return gson.fromJson(fr, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
