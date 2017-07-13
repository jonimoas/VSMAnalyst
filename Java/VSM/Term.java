package VSM;

import java.util.ArrayList;

public class Term {

    String term;
    int times,id;
    ArrayList<Integer> docs = new ArrayList();
    ArrayList<Integer> queries = new ArrayList();

    public Term(String term, int id) {
        this.term = term.trim();
        times = 1;
        this.id = id;
    }

    public void Increase() {
        times++;
    }
    
    public void addQuery(int i){
        queries.add(i);
    }

    public String GetTerm() {
        return term;
    }

    
    public int GetTimes(){
        return times;
    }


    public void adddoc(int id) {
        docs.add(id);
    }

    public ArrayList<Integer> getdocs() {
        return docs;
    }

    public double IDF(int N) {
        double result = Math.log((N + 1) / docs.size());
        if (result == Double.NEGATIVE_INFINITY) {
            return 0.0;
        }
        return result;
    }
    
    
    public int id(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public ArrayList<Integer> getQueries(){
        return queries;
    }
}
