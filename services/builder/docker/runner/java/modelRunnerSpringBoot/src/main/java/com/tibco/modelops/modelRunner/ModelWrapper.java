package com.tibco.modelops.modelRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
 
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.modelops.modelRunner.model.Model;

public class ModelWrapper {
	private static final Logger LOGGER =
		  LoggerFactory.getLogger(ModelWrapper.class);
	  
	private Model myModel = null;
	public void initialize(Properties prop) throws Exception {
		myModel = (Model)newObject(prop.getProperty("JPMMLModel_plugin"));
		myModel.initialize(prop);
	}
	
	public void evaluate(String dataIn) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            //Convert Map to JSON
            map = mapper.readValue(dataIn, new TypeReference<Map<String, Object>>(){});
             
            //Print JSON output
            LOGGER.info("JSON to map : " + map);
            
            this.myModel.evaluate(map);

        } 
        catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public String getResult() {
        return mapToJson(this.myModel.getResult());
	}
	
	public void destroy() {
		this.myModel.destroy();
	}

    public String mapToJson(Map<String, Object> map){
        
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = "";
        try {
            jsonResp = mapperObj.writeValueAsString(map);
            LOGGER.info("map to JSON : " + jsonResp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResp;
    }
	
	public static Object newObject(String name)  throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	    return newObject(null, name);
	}
	
	public static Object newObject(ClassLoader classLoader, String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException  {
	    Class<?> cls = null;
	    if(null!=classLoader) {
//	    	dumpClasspath(classLoader);
		    cls = classLoader.loadClass(name);
	    } else {
	    	cls = Class.forName(name);
	    }
	    return cls.newInstance();
	}
}
