package selenium.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

import selenium.testComponents.baseTest;

public class test1 extends baseTest {
	
	
	@Test(groups= {"selectFill"})
	public void SelectList() throws IOException {
		
		l1.SelectMenu();
		
	}
	@Test(dataProvider = "getData",groups= {"selectFill"})
	public void fillForm(HashMap<String,String> map) throws IOException, InterruptedException {
		l1.fillForm(map.get("name"),map.get("email"));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		String filePath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.json";
		
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper() ;
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		
		
		if(data==null||data.isEmpty()) {
			throw new RuntimeException("data is null");
		}
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}

















