package DemoShellExpertSystem;

import DemoShellExpertSystem.base.FactsBase;
import DemoShellExpertSystem.base.RulesBase;
import DemoShellExpertSystem.base.fact.IFact;
import DemoShellExpertSystem.base.rule.Rule;
import java.util.ArrayList;

/**
 *
 * @author leogonz.gut
 */
public class Motor {

    private FactsBase bdf;
    private RulesBase bdr;
    private HumanInterface ihm;
    private int maxLevelRule;

    public Motor(HumanInterface _ihm) {
        this.ihm = _ihm;
        this.bdf = new FactsBase();
        this.bdr = new RulesBase();
    }

    int askIntValue(String question) {
        return this.ihm.askIntValue(question);
    }

    boolean askBoolValue(String question) {
        return this.ihm.askBoolValue(question);
    }

    private int canApply(Rule _r) {
        int maxLevel = -1;

        for (IFact f : _r.getPremises()) {
            IFact foundFact = this.bdf.find(f.getName());
            if (foundFact == null) {
                if (f.getQuestion() != null) {
                    foundFact = FactFactory.fact(f, this);
                    this.bdf.addFact(foundFact);
                } else {
                    return -1;
                }
            }

            if (!foundFact.getValue().equals(f.getValue())) {
                return -1;
            }
            maxLevel = Math.max(maxLevel, foundFact.getLevel());
        }

        return maxLevel;
    }

    private Rule findUsableRule(RulesBase bdrLocale) {
        for (Rule r : bdrLocale.getRules()) {
            int nivel = canApply(r);
            if (nivel != -1) {
                this.maxLevelRule = nivel;
                return r;
            }
        }
        return null;
    }

    public void solve() {
        RulesBase bdrLocale = new RulesBase();
        bdrLocale.setRules(this.bdr.getRules());

        this.bdf.clear();

        Rule r = findUsableRule(bdrLocale);
        while (r != null) {

            IFact newFact = r.getConclusion();
            newFact.setLevel(this.maxLevelRule + 1);
            this.bdf.addFact(newFact);

            bdrLocale.remove(r);

            r = findUsableRule(bdrLocale);
        }
        this.ihm.printFacts(this.bdf.geFacts());
    }

    public void addRule(String str) {
        String[] splitName = str.split(":");
        if (splitName.length == 2) {
            String name = splitName[0].trim();

            String rule = splitName[1].trim();
            rule = rule.replaceFirst("^IF", "");
            String[] splitPremConcl = rule.split("THEN");
            if (splitPremConcl.length == 2) {

                ArrayList<IFact> premises = new ArrayList<>();
                String[] premisesStr = splitPremConcl[0].split(" AND ");
                for (String prem : premisesStr) {
                    IFact premisa = FactFactory.fact(prem.trim());
                    premises.add(premisa);
                }

                String conclusionStr = splitPremConcl[1].trim();
                IFact conclusion = FactFactory.fact(conclusionStr);

                this.bdr.addRule(new Rule(name, premises, conclusion));
            }
        }
    }

}
