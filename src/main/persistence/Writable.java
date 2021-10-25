package persistence;

import org.json.JSONObject;

//Note this class is completely ripped from
//Github:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}