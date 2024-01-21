package com.oncolator.app.Utility;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_HR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_HR_PostInduction_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_IR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_IR_PostInduction_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_PostProphase_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_Prophase_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_SR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_SR_PostInduction_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.BaseParameters;
import com.oncolator.app.entity.enums.ParameterTypeEnum;

public final class JacksonUtility {
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static BaseParameters deserializeParameters(ParameterTypeEnum type, String parameters){
        if (parameters == null || parameters.isEmpty() || type == null) {
            return null;
        }
        BaseParameters params = null;
        try {
            switch(type){
                case ALL_ICICLE_PROPHASE : params = objectMapper.readValue(parameters, ALL_ICICLE_Prophase_Parameters.class); break;
                case ALL_ICICLE_PostProphase : params = objectMapper.readValue(parameters, ALL_ICICLE_PostProphase_Parameters.class); break;
                case ALL_ICICLE_SR_PostInduction : params = objectMapper.readValue(parameters, ALL_ICICLE_SR_PostInduction_Parameters.class); break;
                case ALL_ICICLE_IR_PostInduction : params = objectMapper.readValue(parameters, ALL_ICICLE_IR_PostInduction_Parameters.class); break;
                case ALL_ICICLE_HR_PostInduction : params = objectMapper.readValue(parameters, ALL_ICICLE_HR_PostInduction_Parameters.class); break;
                case ALL_ICICLE_SR_PostConsolidation : params = objectMapper.readValue(parameters, ALL_ICICLE_SR_PostConsolidation_Parameters.class); break;
                case ALL_ICICLE_IR_PostConsolidation : params = objectMapper.readValue(parameters, ALL_ICICLE_IR_PostConsolidation_Parameters.class); break;
                case ALL_ICICLE_HR_PostConsolidation : params = objectMapper.readValue(parameters, ALL_ICICLE_HR_PostConsolidation_Parameters.class); break;
            }
        } catch (Exception e) {
            System.out.println("error in parsing string with parameters : " + parameters);
            e.printStackTrace();
        }
        return params;
    }
    
    public static Boolean[] deserializeCheckList(String checkList){
        Boolean[] response = null;
        if(checkList == null)
        {
            return response;
        }
        try {
            response = objectMapper.readValue(checkList, Boolean[].class);
        } catch (Exception e) {
            System.out.println("error in parsing checkList : " + checkList);
            e.printStackTrace();
        }
        return response;
    }
    
    public static Float[] deserializeDoseList(String doses){
        Float[] response = null;
        try {
            response = objectMapper.readValue(doses, Float[].class);
        } catch (Exception e) {
            System.out.println("error in parsing doses : " + doses);
            e.printStackTrace();
        }
        return response;
    }

    public static String serializeDoses(List<Float> doses) {
        String response = null;
        try {
            response = objectMapper.writeValueAsString(doses);
        } catch (Exception e) {
            System.out.println("error in serializing doses : " + doses);
            e.printStackTrace();
        }
        return response;
    }
    
}
