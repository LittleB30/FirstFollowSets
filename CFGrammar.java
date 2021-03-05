import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

/**
 * A class defining a Context-Free Grammar (CFG) containing a list of production rules.
 * @author Alex Smith (alsmi14)
 */
public class CFGrammar {
    private List<CFRule> productionRules;
    private Set<String> nonTerminals;
    private Set<String> terminals;
    private Map<String,Set<String>> firstSets;
    private Map<String,Set<String>> followSets;

    /**
     * Constructs a Context-Free Grammar from a given file.
     * @param fileName the name of the file to be read
     */
	public CFGrammar(String fileName) {
        productionRules = new ArrayList<>();
        nonTerminals = new HashSet<>();
        terminals = new HashSet<>();
        firstSets = new HashMap<>();
        followSets = new HashMap<>();
        readGrammar(fileName);
        findFirstSets();
        findFollowSets();
	}

    /**
     * Prints the rules, symbols, and first/follow sets.
     */
	public void printAll() {
        printRules();
        printSymbols();
        printFirst();
        printFollow();
	}

    /**
     * Prints the rules.
     */
    public void printRules() {
        System.out.println();
        for (CFRule rule : productionRules) {
            System.out.println(rule);
        }
	}

    /**
     * Prints the non-terminal and terminal symbols.
     */
    public void printSymbols() {
        System.out.println("\nNon-terminal symbols:    " + nonTerminals);
        System.out.println("Terminal symbols:    " + terminals);
	}

    /**
     * Prints the first sets.
     */
    public void printFirst() {
        System.out.println("\nFirst sets:\n===");
        System.out.println(firstSets);
        //for (Entry<String, Set<String>> e : firstSets.entrySet()) {}
	}

    /**
     * Prints the follow sets.
     */
    public void printFollow() {
        System.out.println("\nFollow sets:\n===");
        System.out.println(followSets);
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
                temp += t + (t == right[right.length-1]? "":" ");
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

    /**
     * Reads a grammar from a file into the production rules arraylist.
     * @param fileName the name of the file to be read
     */
    private void readGrammar(String fileName) { //TODO implement readGrammar
        Scanner scan = null;
		try {
			scan = new Scanner(new File(fileName));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return;
        }
        scan.nextLine(); //skip the grammar title

        while (scan.hasNext()) {
            productionRules.add(new CFRule(scan.nextLine()));
        }

        findSymbols();
    }

    /**
     * Finds the non-terminal and terminal symbols of the grammar.
     */
    private void findSymbols() {
        //look along the left side for non-terminals
        for (CFRule rule : productionRules) {
            nonTerminals.add(rule.left);
        }
        //then look along the right side for symbols that are not in the non-terminals set
        for (CFRule rule : productionRules) {
            for (String symbol : rule.right) {
                if (!nonTerminals.contains(symbol)) {
                    terminals.add(symbol);
                }
            }
        }
    }

    private void findFirstSets() { //TODO implement findFirstSets

    }

    private void findFollowSets() { //TODO implement findFollowSets
        
    }
}
