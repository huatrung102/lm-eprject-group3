/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysController;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import org.netbeans.validation.api.Problem;
import org.netbeans.validation.api.Problems;
import org.netbeans.validation.api.Severity;
import org.netbeans.validation.api.Validator;
import org.netbeans.validation.api.ValidatorUtils;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.netbeans.validation.api.ui.GroupValidator;
import org.netbeans.validation.api.ui.swing.SwingValidationGroup;

/**
 *
 * @author Administrator PC
 */
public class ValidateHandle {
    private static class CompareValidator extends GroupValidator {
        JTextComponent comp1;
        JTextComponent comp2;
        private CompareValidator(JTextComponent c1,JTextComponent c2){
            comp1 = c1;
            comp2 = c2;
        }
        @Override
        protected void performGroupValidation(Problems problems) {
            Problem result = null;
            try {
                String val1 = comp1.getText();
                String val2 = comp2.getText();
                
                if(!val1.equals(val2)){
                    result = new Problem (comp1.getName() + "," + comp2.getName() +
                            " not matched ", Severity.FATAL);
                }                
            } catch (NumberFormatException e) {
                // We should never end up here, the other validators should have
                // taken care of any non-integer
            }
            if (result != null) {
                problems.add(result);
            }
        }
    }
    
    
    public static SwingValidationGroup checkNoBlank(JComponent comp){
        SwingValidationGroup group = SwingValidationGroup.create();
        group.add(comp,ValidatorUtils.cast(String.class, StringValidators.REQUIRE_NON_EMPTY_STRING));
        return group;
    }
    public static SwingValidationGroup checkCompare(JTextComponent c1,JTextComponent c2){
        SwingValidationGroup group = SwingValidationGroup.create( new CompareValidator(c1,c2));
        group.add(c1,ValidatorUtils.cast(String.class, StringValidators.REQUIRE_NON_EMPTY_STRING));
        group.add(c2,ValidatorUtils.cast(String.class, StringValidators.REQUIRE_NON_EMPTY_STRING));
        return group;
    }
    public static SwingValidationGroup checkOnlyEmail(JComponent comp){
        SwingValidationGroup group = SwingValidationGroup.create();
        group.add(comp,ValidatorUtils.cast(String.class, StringValidators.EMAIL_ADDRESS));
        return group;
    }
    public static SwingValidationGroup checkOnlyNumber(JComponent comp){
        SwingValidationGroup group = SwingValidationGroup.create();
        Validator<String> intValidator = 
                ValidatorUtils.merge(StringValidators.REQUIRE_VALID_NUMBER,
                StringValidators.REQUIRE_NON_NEGATIVE_NUMBER);
        group.add (comp, intValidator);
        return group;
    }
}
