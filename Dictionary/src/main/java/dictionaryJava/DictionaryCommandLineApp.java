package dictionaryJava;

public class DictionaryCommandLineApp {
    public static void main(String[] args) {
        DictionaryManagement dm = new DictionaryManagement();
        DictionaryCommandLine cmd = new DictionaryCommandLine(dm);
        cmd.dictionaryAdvanced();
    }
}
