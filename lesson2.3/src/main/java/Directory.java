import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {

    private Map<String, List<String>> directory_maps = new HashMap<>();
    private List<String> phone_number_list;

    public void add(String surname, String phone_number) {
        if (directory_maps.containsKey(surname)) {
            phone_number_list = directory_maps.get(surname);
            phone_number_list.add(phone_number);
            directory_maps.put(surname, phone_number_list);
        } else {
            phone_number_list = new ArrayList<>();
            phone_number_list.add(phone_number);
            directory_maps.put(surname, phone_number_list);
        }
    }

    public List<String> get(String surname) {
        return directory_maps.get(surname);
    }
}
