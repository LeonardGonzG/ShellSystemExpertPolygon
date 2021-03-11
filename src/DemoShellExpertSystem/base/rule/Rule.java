/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoShellExpertSystem.base.rule;

import DemoShellExpertSystem.base.fact.IFact;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author prog
 */
public class Rule {
    protected String theName;
    protected List<IFact> thePremises;
    protected IFact theConclusion;

    public Rule(String newName, List<IFact> newPremises, IFact newConclusion) {
        this.thePremises = newPremises;
        this.theConclusion = newConclusion;
        this.theName = newName;
    }

    public String getName() {
        return theName;
    }

    public void setName(String newName) {
        this.theName = newName;
    }

    public List<IFact> getPremises() {
        return thePremises;
    }

    public void setPremises(List<IFact> newPremises) {
        this.thePremises = newPremises;
    }

    public IFact getConclusion() {
        return theConclusion;
    }

    public void setConclusion(IFact newConclusion) {
        this.theConclusion = newConclusion;
    }

    @Override
    public String toString() {
        String res = theName + " : IF (";
        StringJoiner joiner = new StringJoiner(" AND ");
        
        for (IFact premise : thePremises) {
            joiner.add(premise.toString());
        }
        
        res += joiner.toString() + ") THEN " + theConclusion.toString();
        
        return res;
    }
    
    
    
}
