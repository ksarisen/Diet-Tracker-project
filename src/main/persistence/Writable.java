package persistence;

import org.json.JSONObject;

// Referenced by JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
