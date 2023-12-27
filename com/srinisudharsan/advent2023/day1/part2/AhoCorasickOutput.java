public class AhoCorasickOutput {
    private String firstString;
    private int firstStringIndex;
    private String lastString;
    private int lastStringIndex;

    public AhoCorasickOutput(String firstString, int firstStringIndex, String lastString, int lastStringIndex){
        this.firstString = firstString;
        this.firstStringIndex = firstStringIndex;
        this.lastString = lastString;
        this.lastStringIndex = lastStringIndex;
    }

    public String getFirstString(){
        return this.firstString;
    }

    public int getFirstStringIndex(){
        return this.firstStringIndex;
    }

    public String getLastString(){
        return this.lastString;
    }

    public int getLastStringIndex(){
        return this.lastStringIndex;
    }
}
