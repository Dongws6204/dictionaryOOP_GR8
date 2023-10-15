package dictionaryJava;

public class Word {
    private  String wordTarget;
    private String wordExplain;

    /**
     * contructornull.
     *
     * @null
     */
    public Word(String wordTarget) {

        this.wordTarget = wordTarget;
    }

    /**
     * contructor.
     *
     * @wordTarget English
     * @wordExplain Vietnamese
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    /**
     * get english word.
     *
     * @return english_word
     */
    public String getWordTarget() {
        return wordTarget;
    }

    /**
     * get wordExplain.
     *
     * @return Vietnamese
     */
    public String getWordExplain() {
        return wordExplain;
    }

    /**
     * set Vietnamese definition.
     *
     * @set wordVietnamese
     */
    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

}
