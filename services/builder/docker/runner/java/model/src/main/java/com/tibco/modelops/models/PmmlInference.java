package com.tibco.modelops.models;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.EvaluatorUtil;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.HasGroupFields;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.ModelEvaluatorFactory;
import org.jpmml.evaluator.OutputField;
import org.jpmml.model.PMMLUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PmmlInference implements com.tibco.modelops.modelRunner.inference.Inference {
    static Logger log = LoggerFactory.getLogger(PmmlInference.class);
    
    private Evaluator modelEvaluator = null;
    private Map<String, Object> result = null;
    
	@Override
	public void initialize(Properties prop) throws Exception {
		// "JPMMLModel_URI", "JPMMLModel_plugin"
		log.info("Properties : " + prop);
		File model = new File(prop.getProperty("JPMMLModel_URI")); //"iris_rf.pmml"
        PMML pmml = readPMML(model, true);

        ModelEvaluatorFactory mef = ModelEvaluatorFactory.newInstance();
        modelEvaluator = (Evaluator) mef.newModelEvaluator(pmml);
        modelEvaluator.verify();
        
        printArgumentsOfModel(modelEvaluator);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getVersion() {
		return null;
	}

	@Override
	public void evaluate(Map<String, Object> dataIn) throws Exception {
		List<InputField> inputFields = modelEvaluator.getInputFields();
		log.info(modelEvaluator.toString());
		Map<FieldName, ?> results = modelEvaluator.evaluate(buildArguments(dataIn, inputFields));

		List<OutputField> targets = modelEvaluator.getOutputFields();
		
		this.result = new HashMap<String, Object>();
		for (OutputField field : targets) {
			FieldName fieldName = field.getFieldName();
			Object targetValue = results.get(fieldName);
			this.result.put(fieldName.getValue(), targetValue);
		}
	}

	@Override
	public Map<String, Object> getResult() {
		return this.result;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
    public Map<FieldName, ?> buildArguments(Map<String, Object> dataIn, List<InputField> inputFields) {
		if(modelEvaluator instanceof HasGroupFields){
			HasGroupFields hasGroupfields = (HasGroupFields)modelEvaluator;
			List<InputField> groupFields = hasGroupfields.getGroupFields();
			List<? extends Map<FieldName, ?>> inputRecords = null;
			inputRecords = EvaluatorUtil.groupRows(hasGroupfields, inputRecords);
		}
		
		log.info(dataIn.toString());
		log.info(inputFields.toString());
		Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
		for(InputField inputField : inputFields){
			FieldName name = inputField.getName();
			FieldValue value = inputField.prepare(dataIn.get(name.getValue()));
			arguments.put(name, value);
		}

        return arguments;
    }

    public void printArgumentsOfModel(Evaluator modelEvaluator2){
        System.out.println("### Active Fields of Model ####");
        for(InputField fieldName : modelEvaluator2.getActiveFields()){
            System.out.println("Field Name: "+ fieldName);
        }
    }
    
	public PMML readPMML(File file, boolean acceptServiceJar) throws Exception {

		if(acceptServiceJar){

			if(isServiceJar(file, PMML.class)){
				URL url = (file.toURI()).toURL();

				return PMMLUtil.load(url);
			}
		}

		try(InputStream is = new FileInputStream(file)){
			return PMMLUtil.unmarshal(is);
		}
	}
	
	private boolean isServiceJar(File file, Class<?> clazz){

		try(ZipFile zipFile = new ZipFile(file)){
			ZipEntry serviceZipEntry = zipFile.getEntry("META-INF/services/" + clazz.getName());

			return (serviceZipEntry != null);
		} catch(IOException ioe){
			return false;
		}
	}
}
