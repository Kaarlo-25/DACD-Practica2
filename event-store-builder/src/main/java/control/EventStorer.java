package control;


import com.google.gson.JsonObject;

public interface EventStorer {
	void storeEvents(JsonObject jsObject);
}
