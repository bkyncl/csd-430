/* StudentValidator.java
 * Module 10 Assignment
 * Name: Brittany Kyncl
 * Date: 7.16.23
 * Course: CSD430
 * Implements custom validator for the StudentID field.
 */

package com.JSF_Example;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("studentValidator")
public class StudentValidator implements Validator<Integer> {

    @Override
    public void validate(FacesContext context, UIComponent component, Integer value) throws ValidatorException {
        // Convert the integer value to a string
        String studentId = String.valueOf(value);
        
        // Perform validation logic
        if (studentId.length() < 4 || studentId.length() > 6 || containsNonNumerical(studentId)) {
            // If the student ID is less than 4 characters long, greater than 6 characters, or contains non-numerical values, throw a ValidatorException 
        	//with an error message
            throw new ValidatorException(new FacesMessage("Student ID must be between 4 and 6 characters long and contain only numerical values."));
        }
    }
    public boolean containsNonNumerical(String str) {
        return str.matches(".*[^0-9].*");
    }
}