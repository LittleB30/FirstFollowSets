import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * @author Alex Smith (3/12/21)
 */
public class JUnitTesting {
    private CFGrammar grammar;
    private Map<String,Set<String>> firsts;
    private Map<String,Set<String>> follows;
    private static final String filePath = "C:\\Users\\alex1\\OneDrive - IL State University\\IT 327\\FirstFollowSets\\";

    @Test
    public void testAlexG1() {
        grammar = new CFGrammar(filePath + "AlexG1.txt");
        firsts = new HashMap<>();
        follows = new HashMap<>();

        //firsts
        HashSet<String> tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"a", "c", "lambda"}));
        firsts.put("S", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"a"}));
        firsts.put("A", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"c", "lambda"}));
        firsts.put("C", tempSet);

        //follows
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"b", "$"}));
        follows.put("S", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"a", "b", "c"}));
        follows.put("A", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"b", "$"}));
        follows.put("C", tempSet);

        assertTrue(grammar.getFirstSets().equals(firsts));
        assertTrue(grammar.getFollowSets().equals(follows));
    }

    @Test
    public void testAlexG2() {
        grammar = new CFGrammar(filePath + "AlexG2.txt");
        firsts = new HashMap<>();
        follows = new HashMap<>();

        //firsts
        HashSet<String> tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"(", "id"}));
        firsts.put("E", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"+", "lambda"}));
        firsts.put("E'", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"(", "id"}));
        firsts.put("T", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"*", "lambda"}));
        firsts.put("T'", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"(", "id"}));
        firsts.put("F", tempSet);

        //follows
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{")", "$"}));
        follows.put("E", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{")", "$"}));
        follows.put("E'", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"+", ")", "$"}));
        follows.put("T", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"+", ")", "$"}));
        follows.put("T'", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"+", "*", ")", "$"}));
        follows.put("F", tempSet);

        assertTrue(grammar.getFirstSets().equals(firsts));
        assertTrue(grammar.getFollowSets().equals(follows));
    }

    @Test
    public void testAlexG3() {
        grammar = new CFGrammar(filePath + "AlexG3.txt");
        firsts = new HashMap<>();
        follows = new HashMap<>();

        //firsts
        HashSet<String> tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"a", "b", "t"}));
        firsts.put("S", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"a", "b", "t", "lambda"}));
        firsts.put("A", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"b", "t"}));
        firsts.put("B", tempSet);

        //follows
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"$", "b", "t", "a"}));
        follows.put("S", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"b", "t", "a"}));
        follows.put("A", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"$", "b", "t", "a"}));
        follows.put("B", tempSet);

        assertTrue(grammar.getFirstSets().equals(firsts));
        assertTrue(grammar.getFollowSets().equals(follows));
    }

    @Test
    public void testG1() {
        grammar = new CFGrammar(filePath + "G1.txt");
        firsts = new HashMap<>();
        follows = new HashMap<>();

        //firsts
        HashSet<String> tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"a", "k", "lambda"}));
        firsts.put("S", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"a", "k"}));
        firsts.put("A", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"b", "t"}));
        firsts.put("B", tempSet);

        //follows
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"$", "b", "t"}));
        follows.put("S", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"a", "k", "b", "t"}));
        follows.put("A", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"$", "b", "t"}));
        follows.put("B", tempSet);

        assertTrue(grammar.getFirstSets().equals(firsts));
        assertTrue(grammar.getFollowSets().equals(follows));
    }

    @Test
    public void testG4() {
        grammar = new CFGrammar(filePath + "G4.txt");
        firsts = new HashMap<>();
        follows = new HashMap<>();

        //firsts
        HashSet<String> tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"asg", "if"}));
        firsts.put("S", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"bool"}));
        firsts.put("C", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"lambda", "else"}));
        firsts.put("E", tempSet);

        //follows
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"$", "else"}));
        follows.put("S", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"then"}));
        follows.put("C", tempSet);
        tempSet = new HashSet<>();
        tempSet.addAll(Arrays.asList(new String[]{"$", "else"}));
        follows.put("E", tempSet);

        assertTrue(grammar.getFirstSets().equals(firsts));
        assertTrue(grammar.getFollowSets().equals(follows));
    }
}
