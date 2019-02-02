package controller;

import com.google.gson.Gson;
import etc.Helpers;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class Controller {
    protected Validator validator;
    protected Gson gson;

    public Controller() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        gson = Helpers.gson();
    }
}
