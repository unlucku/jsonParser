import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class parser {
	public static int counter;
	public static ArrayList<String> usedNames = new ArrayList<String>();
	public static void main(String[] args) throws JSONException {
		String x = "";
		JSONObject toParse = new JSONObject(x);
		parseObject(toParse, "x" + getCounter());
	}
	public static void parseObject(JSONObject toParse, String objectName) {
		JSONArray names = toParse.names();
		String parent = objectName;
		usedNames.add(parent);
		System.out.println("JSONObject " + parent + " = new JSONObject();");
		for (int i = 0; i < names.length(); i++) {
			String name = names.getString(i);
			Object value = toParse.get(name);
			if (value instanceof JSONObject) {
				String nameToUse = usedNames.contains(name) ? name+((int) (Math.random()*10)) : name;
				parseObject((JSONObject) value, nameToUse );
				System.out.println(parent + ".put(\"" + name + "\", " + nameToUse + ");");
			}
			else if (value instanceof JSONArray) {
				String nameToUse = usedNames.contains(name) ? name+((int) (Math.random()*10)) : name;
				parseObject((JSONArray) value, nameToUse);
				System.out.println(parent + ".put(\"" + name + "\", " + nameToUse + ");");
			}
			else if (value instanceof String) {
				System.out.println(parent + ".put(\"" + name + "\", \"" + value + "\");");
			}
			else if (value instanceof Long) {
				System.out.println(parent + ".put(\"" + name + "\", " + value + "L);");
			}
			else {
				System.out.println(parent + ".put(\"" + name + "\", " + value + ");");
			}
		}
	}
	public static void parseObject(JSONArray toParse, String objectName) {
		String parent = objectName;
		usedNames.add(parent);
		System.out.println("JSONArray " + parent + " = new JSONArray();");
		for (int i = 0; i < toParse.length(); i++) {
			Object value = toParse.get(i);
			if (value instanceof JSONObject) {
				parseObject((JSONObject) value, "x" + getCounter());
				System.out.println(parent + ".put(" + "x" + (counter-1) + ");");
			}
			else if (value instanceof JSONArray) {
				parseObject((JSONArray) value, "x" + getCounter());
				System.out.println(parent + ".put(" + "x" + (counter-1) + ");");
			}
			else if (value instanceof String) {
				System.out.println(parent + ".put(\"" + value + "\");");
			}
			else if (value instanceof Long) {
				System.out.println(parent + ".put(" + value + "L);");
			}
			else {
				System.out.println(parent + ".put(" + value + ");");
			}
		}
	}
	public static int getCounter() {
		return counter++;
	}
}
