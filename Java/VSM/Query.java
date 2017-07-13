/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VSM;

import java.util.ArrayList;

/**
 *
 * @author joni
 */
public class Query {
    ArrayList<Term> contents = new ArrayList();
    int id;

    public Query(int id) {
        this.id = id;
    }

    public void AddTerm(String term) {
        if (!term.trim().isEmpty()) {
            term = Utils.Porter.stripAffixes(term);
            boolean exists = false;
            for (Term t : contents) {
                if (term.equals(t.GetTerm())) {
                    t.Increase();
                    exists = true;
                }
            }
            if (!exists) {
                contents.add(new Term(term,0));
            }
        }
    }
    
    public void removeZeros(){
        ArrayList<Term> temp = new ArrayList();
        for (Term t : contents){
            if (t.id() != 0){
                temp.add(t);
            }
        }
        contents = temp;
    }
    
    public ArrayList<Term> contents(){
        return contents;
    }
    
}
