package gr.huadit.DTO;
import java.util.ArrayList;
import java.util.List;

public class TotalFiles {
    List<TrackPointResults> results = new ArrayList<>();
    
    public void addResults(TrackPointResults key) {
        results.add(key);
    }

    public List<String> getResults() {
        return new List<String>;
    }
}
