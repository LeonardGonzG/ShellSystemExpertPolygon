/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoShellExpertSystem.base.fact;

/**
 *
 * @author prog
 */
public class BooleanFact implements IFact {
   
    protected String theName;
    protected boolean theValue;
    protected int theLevel;
    protected String theQuestion;

    public BooleanFact(String newName, boolean newValue, String newQuestion, 
            int newLevel) {
        this.theName = newName;
        this.theValue = newValue;
        this.theLevel = newLevel;
        this.theQuestion = newQuestion;
    }
    
    
    @Override
    public String getName() {
        return theName;
    }

    @Override
    public Object getValue() {
        return theValue;
    }

    @Override
    public int getLevel() {
        return theLevel;
    }

    @Override
    public String getQuestion() {
        return theQuestion;
    }

    @Override
    public void setLevel(int newLevel) {
        theLevel = newLevel;
    }

    @Override
    public String toString() {
        String res = "";
        
        if (!theValue){
            res += "!";
        }
        
        res += theName + " (" + theLevel + ")";
        
        return res;
    } 
}
