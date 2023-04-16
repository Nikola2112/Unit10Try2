import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Task2 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/file2.txt"));

        // Считываем заголовок и определяем индексы столбцов "name" и "age"
        String header = reader.readLine();
        String[] columns = header.split(" ");
        int nameColumnIndex = getColumnIndex(columns, "name");
        int ageColumnIndex = getColumnIndex(columns, "age");

        // Считываем данные и создаем список объектов User
        List<User> userList = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(" ");
            String name = values[nameColumnIndex];
            int age = Integer.parseInt(values[ageColumnIndex]);
            User user = new User(name, age);
            userList.add(user);
        }
        reader.close();

        // Преобразуем список объектов в JSON и записываем его в файл user.json
        Gson gson = new Gson();
        String json = gson.toJson(userList);
        FileWriter writer = new FileWriter("src/user.json");
        writer.write(json);
        writer.close();

    }

    private static int getColumnIndex(String[] columns, String columnName) {
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equals(columnName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Column with name " + columnName + " not found");
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
