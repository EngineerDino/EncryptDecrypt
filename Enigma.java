package encryptdecrypt;

public abstract class Enigma {
    private String mode;
    private int key;

    private printer thePrinter;


    //Constructors
    public Enigma() {
        this.mode = "enc";
        this.key = 0;
        this.thePrinter = new StandardPrinter();
    }
    public Enigma(String mode, int key, String out) {
        this.mode = mode;
        this.key = key;
        this.thePrinter = new FilePrinter(out);
    }

    //Getters and Setters....not all needed here but we could use them...
    public void setKey(int key) {
        this.key = key;
    }
    protected int getKey() {
        return this.key;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    protected String getMode() {
        return this.mode;
    }

    //Thats the internals of each special enigma....how does it do the encryption/decryption?
    protected abstract String encryptDecrypt(String input);

    //Thats the only method the user can call. Just: "Here, enigma, have this input, do your job!"
    public void doYourJob(String input) {
        this.thePrinter.print(encryptDecrypt(input));
    }
}



//Two different implementations of the Enigma Class. One shifts all letters by Unicode,
//the other only the alphabetical characters.
//They also contain a Printer and so we can just ask them to doYourJob(inputstring) and recieve a printed en/decrypted result.
class EnigmaUnicode extends Enigma {

    public EnigmaUnicode(String mode, int key, String out) {
        super(mode, key, out);
    }
    public EnigmaUnicode() {
        super();
    }

    @Override
    protected String encryptDecrypt(String input) {
        int key = "enc".equals(getMode()) ? getKey() : (-1) * getKey();

        StringBuilder result = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {
            result.append((char) (input.charAt(i) + key));
        }

        return result.toString();
    }
}


class EnigmaAlphabet extends Enigma {

    public EnigmaAlphabet(String mode, int key, String out) {
        super(mode, key, out);
    }
    public EnigmaAlphabet() {
        super();
    }

    @Override
    protected String encryptDecrypt(String input) {
        int key = "enc".equals(getMode()) ? getKey() : (26 - getKey());
        System.out.println(key);

        StringBuilder result = new StringBuilder(input.length());
        char shifted;

        for(char ch : input.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                shifted = (char) ((ch - 'A' + key) % 26 + 'A');
                result.append(shifted);
            } else if (ch >= 'a' && ch <= 'z') {
                shifted = (char) ((ch - 'a' + key) % 26 + 'a');
                result.append(shifted);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}