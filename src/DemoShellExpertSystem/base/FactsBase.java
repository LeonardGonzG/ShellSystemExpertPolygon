/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoShellExpertSystem.base;

import DemoShellExpertSystem.base.fact.IFact;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prog
 */
public class FactsBase {
    protected List<IFact> theFacts = null;

    public FactsBase() {
        theFacts = new ArrayList<>();
    }

    public List<IFact> geFacts() {
        return theFacts;
    }
    
    public void addFact(IFact newFact){
        theFacts.add(newFact);
    }
    
    public void clear(){
        theFacts.clear();
    }
    
    public IFact find(String factName){
        for (IFact fact : theFacts) {
            if (fact.getName().equals(factName)){
                return fact;
            }
        }
        
        return null;
    }
    
    public Object getFactValue(String factName){
        IFact fact = find(factName);
        
        if (fact != null){
            return fact.getValue();
        }
        
        return null;
    }
    
}
