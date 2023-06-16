package section21Code.data;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Section21Code_DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() {

		// Read the JSON file
		String jsonContentString = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "//src//test//java//section21Code//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Convert String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<Hashmap<String, String>> dataHashMap = mapper.readValue(jsonContentString,
				new TypeReference<List<Hashmap<String, String>>>() {
				});

		return dataHashMap;
	}
}
