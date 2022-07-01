package encryptdecrypt;

public class EnigmaBuilder {
    public Enigma buildEnigma (String algo, String modus, int key, String out) {
        switch (algo) {
            case "shift":
                return new EnigmaAlphabet(modus, key, out);
            case "unicode":
                return new EnigmaUnicode(modus, key, out);
            default:
                return null;
        }
    }
}
