package org.perf.blog.tests.student;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.perf.blog.config.CouchDbClient;
import com.perf.blog.model.StudentDetail;
import com.perf.blog.util.CommanUtil;

public class StudentServiceImpl  {

	private static CouchDbClient dbClient;
	
	static{
		dbClient =  new CouchDbClient();
	}
	
	public static void main(String[] args) {
		new StudentServiceImpl().doProcess();
	}
	
	void doProcess(){
		
		String uri = this.save();
		findById(uri);
		findAny();
	}
	
	public String save() {
		String id  = CommanUtil.generateUUID();
		System.out.println(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", id);
		map.put("studentDetail", getStudentObject());
		dbClient.save(map);
		return id;
	}
	
	
	public StudentDetail getStudentObject(){
		StudentDetail detail = new StudentDetail();
		detail.setId(Long.parseLong("21"));
		detail.setName("Paulo Dybala");
		detail.setAge("23");
		detail.setClub("FC juventus");
		detail.setCountry("Argentina");
		detail.setAddress("Turin");
		return detail;
	}
	
	@SuppressWarnings("resource")
	public void findById(String id) {
		String uri = new CouchDbClient().getBaseUri() + "perficient/" + id;
		JsonObject jsonObject = new CouchDbClient().findAny(JsonObject.class, uri);
		assertNotNull(jsonObject);
		JsonElement element = jsonObject.get("studentDetail"); 
		System.out.println(element);
	}
	
	public void findAny() {
		System.out.println(dbClient.getBaseUri());
		String uri = dbClient.getBaseUri() + "perficient";
		JsonObject jsonObject = dbClient.findAny(JsonObject.class, uri);
		assertNotNull(jsonObject);
	}

	public void merge() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", "b90a2ae6833246bc8d800ad4919ae036");
		map.put("_rev", "1-aa7c423222c6621617d561c24326d099");
		map.put("studentDetail",getStudentObject());
		if(dbClient == null){
			dbClient =  new CouchDbClient();
		}
		dbClient.update(map);
	}
	
	
}
