import dictionaryJava.DictionaryCommandLine;
import dictionaryJava.DictionaryManagement;

public class Main {
//    public static void main(String[] args) {
//        DictionaryManagement dictionaryManagement = new DictionaryManagement();
//        DictionaryCommandline commandline = new DictionaryCommandline(dictionaryManagement);
//        commandline.dictionaryBasic();
//    }

    public static void main(String[] args) {
        DictionaryManagement dm = new DictionaryManagement();
        DictionaryCommandLine cmd = new DictionaryCommandLine(dm);
        cmd.dictionaryAdvanced();
    }
}
