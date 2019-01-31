package controller;

import com.google.gson.Gson;
import etc.Helpers;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class BaseController {
    protected Validator validator;
    protected Gson gson;

    public BaseController() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        gson = Helpers.gson();
    }
}
