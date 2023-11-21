import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNameGenerator {
    private List<String> names;

    public RandomNameGenerator() {
        names = new ArrayList<>();
    }

    public void addNames() {
        String[] addingNames = {"Masha", "Misha", "Andrei","Igor", "Vika", "Pasha", "Arkasha","Anton"};
        for (int i = 0; i < addingNames.length; i++) {
            names.add(addingNames[i]);
        }
    }

    public String generateRandomName() {
        addNames();
        Collections.shuffle(names);
        return names.remove(0);
    }
}