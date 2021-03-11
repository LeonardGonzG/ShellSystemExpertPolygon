/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoShellExpertSystem.base;

import DemoShellExpertSystem.base.rule.Rule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prog
 */
public class RulesBase {
    protected List<Rule> theRules;

    public RulesBase() {
        this.theRules = new ArrayList<>();
    }

    public List<Rule> getRules() {
        return theRules;
    }

    public void setRules(List<Rule> newRules) {
         
        for (Rule r : newRules) {
            Rule ruleCopy = new Rule(r.getName(), r.getPremises(), r.getConclusion());
            this.theRules.add(ruleCopy);
           } 
    }
    
    public void clearBase(){
    	theRules.clear();
    }
    
    public void addRule(Rule newRule){
        theRules.add(newRule);
    }
    
    public void addRules(List<Rule> newRules){
        Rule newRule;
        
        for (Rule rule : newRules) {
            newRule = new Rule(rule.getName(), rule.getPremises(), rule.getConclusion());
            addRule(newRule);
        }
        
    }
    
    public void remove(Rule rule){
        theRules.remove(rule);
    }
    
    
}
