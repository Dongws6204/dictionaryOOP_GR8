import dictionaryJava.DictionaryCommandline;
import dictionaryJava.DictionaryManagement;

public class Main {
    public static void main(String[] args) {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandline commandline = new DictionaryCommandline(dictionaryManagement);
        commandline.dictionaryBasic();
    }
}
