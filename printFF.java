/**
 * Driver class to run the CFGrammer.
 * @author Alex Smith (alsmi14)
 * @date 3/12/2021
 */
public class printFF {
    public static void main(String[] args) {
        // if (args.length < 1) {
        //     System.out.println("Usage: printFF fileName");
        //     return;
        // }
        // String fileName = args[0];
        String fileName = "G3.txt";
        CFGrammar grammar = new CFGrammar(fileName);
        grammar.printAll();
    }
}
