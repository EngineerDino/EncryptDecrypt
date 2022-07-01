package encryptdecrypt;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
    
        
        String modus = "enc";
        String input = "";
        int key = 0;
        String out = "";
        String algo = "shift";
        
        //Dealing with the args from main.
        for (int i = 0; i < args.length; i = i + 2) {
            switch (args[i]) {
                case "-mode":
                    modus = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args [i + 1]);
                    break;
                case "-data":
                    input = args[i + 1];
                    break;
                case "-in":
                    input = "".equals(input) ? readInput(args[i + 1]) : input;
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-alg":
                    algo = args[i + 1];
                default:
                    break;
            }
        }

        //The Factory can make any Enigma...we coud decide not to give the modus key and out here but instead set later.
        EnigmaBuilder theFactory = new EnigmaBuilder();
        Enigma theEnigma = theFactory.buildEnigma(algo, modus, key, out);

        theEnigma.doYourJob(input);



        /*switch (modus) {
            case "dec":
                print(encDec(input, (-1) * key), out);
                break;
            case "enc":
                print(encDec(input, key), out);
                break;
            default:
                System.out.println("Error: Only allowed inputs are enc or dec!");
        }*/


        
    }

    private static void print(String input, String path) {
        if ("".equals(path)) {
            System.out.println(input);
        } else {
            File file = new File(path);
            try (PrintWriter printWriter = new PrintWriter(file)){

                printWriter.println(input);

            } catch (IOException e) {
                System.out.printf("Error! Cannot write or create %s", path);
            }

        }
    }
    private static String encDec(String input, int shift) {
            
            StringBuilder result = new StringBuilder(input.length());
            
            for (int i = 0; i < input.length(); i++) {
                result.append((char) (input.charAt(i) + shift));
            }
            
            return result.toString();
    }
               
    private static String readInput(String path) {

        try  {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.out.println("Error! File not found!");
            System.out.println(e.getMessage());
        }

        return "";
    }
        
    
}
