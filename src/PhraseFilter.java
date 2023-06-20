

public class PhraseFilter implements Filter {
    
    private String whereString;
    private String phraseString;

    public PhraseFilter(String where, String phrase){
        whereString = where;
        phraseString = phrase;
    }

    public boolean satisfies(QuakeEntry qe){
        if(whereString.equals("start") && 
            qe.getInfo().startsWith(phraseString)){
                return true;
        }

        if(whereString.equals("end") && 
            qe.getInfo().endsWith(phraseString)){
                return true;
        }

        if(whereString.equals("any") && 
            qe.getInfo().contains(phraseString)){
                return true;
        }
        return false;
    }
}
