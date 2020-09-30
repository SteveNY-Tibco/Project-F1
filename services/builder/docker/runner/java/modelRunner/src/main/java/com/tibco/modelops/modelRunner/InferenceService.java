package com.tibco.modelops.modelRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLClassLoader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibco.modelops.modelRunner.inference.Inference;

public class InferenceService {
	private static final Logger LOGGER =
		  LoggerFactory.getLogger(InferenceService.class);
	  
	private Inference myModel = null;
	public void initialize(Properties prop) throws Exception {
		String modelPlugin = prop.getProperty("JPMMLModel_plugin");
		LOGGER.info("Load model plugin : " + myModel);
		myModel = (Inference)newObject(modelPlugin);
		LOGGER.info("Initialize model plugin : " + myModel);
		myModel.initialize(prop);
		LOGGER.info("Model plugin is ready : name = " + myModel.getName() + ", version = " + myModel.getVersion());		
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
	    	dumpClasspath(classLoader);
		    cls = classLoader.loadClass(name);
	    } else {
	    	cls = Class.forName(name);
	    }
	    return cls.newInstance();
	}
	
    public static void dumpClasspath(ClassLoader loader)
    {
    	 LOGGER.info("Classloader " + loader + ":");

        if (loader instanceof URLClassLoader) {
            URLClassLoader ucl = (URLClassLoader)loader;
            LOGGER.info("\t" + Arrays.toString(ucl.getURLs()));
        }
        else {
        	 LOGGER.info("\t(cannot display components as not a URLClassLoader)");
        }

        if (loader.getParent() != null)
            dumpClasspath(loader.getParent());
    }
}
