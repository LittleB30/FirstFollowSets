/**
 * Driver class to run the CFGrammer.
 * @author Alex Smith (alsmi14)
 */
public class printFF {
    public static void main(String[] args) {
        // if (args.length < 1) {
        //     System.out.println("Usage: printFF fileName);
        //     return;
        // }
        // String file = args[0];
        String file = "G1.txt";
        CFGrammar grammar = new CFGrammar(file);
        grammar.printAll();
    }
}
