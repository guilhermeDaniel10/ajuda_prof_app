package com.ajudaprof.ajuda_prof_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExists extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    private Object fieldValues;

    private String message;

    public ResourceAlreadyExists(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldName, fieldValue));
        this.message = super.getMessage();
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceAlreadyExists(String resourceName, String[] fieldArrName, Object fieldValue) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldArrName.toString(), fieldValue));
        this.message = super.getMessage();
        this.resourceName = resourceName;
        this.fieldName = fieldArrName.toString();
        this.fieldValue = fieldValue;
    }

    public ResourceAlreadyExists(String resourceName, String[] fieldArrName, Object[] fieldValues) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldArrName.toString(), fieldValues.toString()));
        this.message = super.getMessage();
        this.resourceName = resourceName;
        this.fieldName = fieldArrName.toString();
        this.fieldValues = fieldValues;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Object getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Object fieldValues) {
        this.fieldValues = fieldValues;
    }
}
