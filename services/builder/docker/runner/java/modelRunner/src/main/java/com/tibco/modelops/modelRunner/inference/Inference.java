package com.tibco.modelops.modelRunner.inference;

import java.util.Map;
import java.util.Properties;

public interface Inference {
	void initialize(Properties prop) throws Exception;
	String getName();
	String getVersion();
	void evaluate(Map<String, Object> dataIn) throws Exception;
	public Map<String, Object> getResult() ;
	void destroy();
}
