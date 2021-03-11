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
public interface IFact {

    String getName();

    Object getValue();

    int getLevel();

    String getQuestion();

    void setLevel(int newLevel);
}
