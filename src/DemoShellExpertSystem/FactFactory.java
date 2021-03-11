package DemoShellExpertSystem;

import DemoShellExpertSystem.base.fact.BooleanFact;
import DemoShellExpertSystem.base.fact.IFact;
import DemoShellExpertSystem.base.fact.IntegerFact;

class FactFactory {

    static IFact fact(IFact f, Motor m) {
        IFact newFact;
        if (f instanceof IntegerFact) {
            newFact = createIntFact(f, m);
        } else {

            newFact = createBoolFact(f, m);
        }
        return newFact;
    }

    static IFact createIntFact(IFact f, Motor m) {
        int valor = m.askIntValue(f.getQuestion());
        return new IntegerFact(f.getName(), valor, null, 0);
    }

    static IFact createBoolFact(IFact f, Motor m) {
        boolean valorB = m.askBoolValue(f.getQuestion());
        return new BooleanFact(f.getName(), valorB, null, 0);
    }

    static IFact fact(String strFact) {
        strFact = strFact.trim();
        if (strFact.contains("=")) {

            strFact = strFact.replaceFirst("^\\(", "");
            String[] nameValue = strFact.split("[=()]");
            if (nameValue.length >= 2) {
                String question = null;
                if (nameValue.length == 3) {
                    question = nameValue[2].trim();
                }
                return new IntegerFact(nameValue[0].trim(), Integer.parseInt(nameValue[1].trim()), question, 0);
            }

        } else {

            boolean value = true;
            if (strFact.startsWith("!")) {
                value = false;
                strFact = strFact.substring(1).trim();
            }

            strFact = strFact.replaceFirst("^\\(", "");
            String[] nameQuestion = strFact.split("[()]");
            String question = null;
            if (nameQuestion.length == 2) {
                question = nameQuestion[1].trim();
            }
            return new BooleanFact(nameQuestion[0].trim(), value, question, 0);
        }

        return null;
    }
}
