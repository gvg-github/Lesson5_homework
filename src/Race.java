import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by GVG on 29.08.2017.
 */
public class Race {
    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
