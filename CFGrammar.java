import java.util.Map;
import java.util.Set;

/**
 * A class defining a Context-Free Grammar (CFG) containing a list of production rules.
 * @author Alex Smith (alsmi14)
 */
public class CFGrammar {
    private Set<String> nonTerminals;
    private Set<String> terminals;
    private Map<String,Set<String>> firstSets;
    private Map<String,Set<String>> followSets;

	public CFGrammar(String file) {

	}

	public void print() {

	}

    /**
     * An inner class defining a context-free production rule used for the Entry class.
     */
    private class CFRule {
        private String left;
        private String[] right;

        /**
         * Constructs a CFRule from a given string.
         * @param rule the rule to be parsed
         */
        public CFRule(String rule) {
            parseRule(rule);
        }

        /**
         * Gets a copy the left variable.
         * @return this.left
         */
        public String getLeft() { 
            return String.valueOf(left);
        }

        /**
         * Gets a copy of the right variable.
         * @return this.right
         */
        public String[] getRight() {
            return right.clone();
        }

        /**
         * Returns a string representation of the CFRule.
         */
        public String toString() {
            String temp = left + " ::= ";
            for (String t : right) {
                temp += t + (t.equals(right[right.length-1])? "":" ");
            }
            return temp;
        }

        /**
         * Parses a rule from a string and initializes the left and right instance variables.
         * @param rule the rule to be parsed
         */
        private void parseRule(String rule) {
            String[] r = rule.split("::=");
            left = r[0].trim();
            right = r[1].trim().split("\\s+");
        }
    }

    private void readGrammar(String file) {

    }

    private void findFirstSets() {

    }

    private void findFollowSets() {
        
    }
}
