package section21Code.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Section21Code_DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		// Read the JSON file
		String jsonContentString = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "//src//test//java//section21Code//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Convert String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		// List<HashMap<String, String>> dataHashMap =
		// mapper.readValue(jsonContentString,
		// new TypeReference<List<HashMap<String, String>>>() {
		// });

		List<HashMap<String, String>> dataHashMap = new Gson().fromJson(jsonContentString,
				new TypeToken<List<HashMap<String, String>>>() {
				}.getType());

		return dataHashMap;
	}
}
