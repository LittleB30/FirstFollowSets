import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
    private final String LAMBDA = "lambda";

    /**
     * Constructs a Context-Free Grammar from a given file.
     * @param fileName the name of the file to be read
     */
	public CFGrammar(String fileName) {
        productionRules = new ArrayList<>();
        nonTerminals = new LinkedHashSet<>();
        terminals = new HashSet<>();
        readGrammar(fileName);
        initializeSets();
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
    private void readGrammar(String fileName) {
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

        scan.close();
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

    /**
     * Puts empty sets into the first and follow sets maps and calculates their sets.
     */
    private void initializeSets() {
        firstSets = new LinkedHashMap<>();
        followSets = new LinkedHashMap<>();
        for (String symbol : nonTerminals) {
            Set<String> temp = new HashSet<>();
            firstSets.put(symbol, temp);
        }
        for (String symbol : nonTerminals) {
            Set<String> temp = new HashSet<>();
            followSets.put(symbol, temp);
        }
        findFirstSets();
        findFollowSets();
    }

    /**
     * Finds the first set of each non-terminal symbol of the CFGrammar.
     */
    private void findFirstSets() {
        Map<String,List<String[]>> dependencies = findFirstDependencies();
        while (firstIteration(dependencies));
    }

    /**
     * Finds the CFRules that each non-terminal symbol depends on to calculate their first sets.
     * @return a map of the dependencies <non-terminal, List of right sides of CFRules>
     */
    private Map<String,List<String[]>> findFirstDependencies() {
        Map<String,List<String[]>> dependencies = new HashMap<>();
        for (String symbol : nonTerminals) {
            List<String[]> temp = new ArrayList<>();
            dependencies.put(symbol, temp);
        }
        for (CFRule rule : productionRules) {
            dependencies.get(rule.left).add(rule.right);
        }
        return dependencies;
    }

    /**
     * Performs a single iteration of calculating the first sets of each non-terminal symbol.
     * @param dependencies a map of the dependencies <non-terminal, List of right sides of CFRules>
     * @return true if a change was made to the firstSets map, false otherwise
     */
    private boolean firstIteration(Map<String,List<String[]>> dependencies) {
        boolean changeMade = false;
        boolean addLambda;
        for (Entry<String, List<String[]>> entry : dependencies.entrySet()) {
            for (String[] right : entry.getValue()) {
                addLambda = (nonTerminals.contains(right[0]))? !firstSets.get(right[0]).contains(LAMBDA) : true;
                changeMade = firstSetHelper(firstSets.get(entry.getKey()), right, addLambda) || changeMade;
            }
        }
        return changeMade;
    }

    /**
     * Recursive method to calculate the first set of a given string.
     * @param set string set to be added to
     * @param str string array corresponding to the right side of a CFRule
     * @param addLambda boolean specifying whether or not lambda should be added
     * @return true if a change was made to the given set, false otherwise
     */
    private boolean firstSetHelper(Set<String> set, String[] str, boolean addLambda){
        if (terminals.contains(str[0])) {
            return set.add(str[0]);
        } else if (nonTerminals.contains(str[0])) {
            if (addLambda) {
                return set.addAll(firstSets.get(str[0]));
            } else {
                Set<String> temp = new HashSet<>();
                temp.addAll(firstSets.get(str[0]));
                temp.remove(LAMBDA);
                return set.addAll(temp);
            }
        } else if (firstSets.get(str[0]).contains(LAMBDA)) {
                return firstSetHelper(set, new String[]{str[0]}, false) || 
                firstSetHelper(set, Arrays.copyOfRange(str, 1, str.length), true);
        } else { 
            return firstSetHelper(set, new String[]{str[0]}, true);
        }
    }

    /**
     * Finds the follow set of each non-terminal symbol of the CFGrammar.
     */
    private void findFollowSets() { //TODO implement findFollowSets
        
    }

    /**
     * Finds the follow set of a given string.
     * @param str the string to be evaluated
     * @return the follow set of the string
     */
    private Set<String> followSet(String str) {
        return null;
    }
}
