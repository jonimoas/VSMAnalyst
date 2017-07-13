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
public class QueryCollection {

    ArrayList<Query> contents = new ArrayList();
    ArrayList<Term> terms = new ArrayList();
    boolean compiled;

    public QueryCollection() {
        this.compiled = false;
    }

    public void add(Query q) {
        contents.add(q);
    }

    public void compile(DocumentCollection c) {
        terms = c.getTerms();
        for (Query q : contents) {
            for (Term t : terms) {
                for (Term k : q.contents()){
                    if(t.GetTerm().trim().equals(k.GetTerm().trim())){
                        t.addQuery(q.id);
                        k.setId(t.id());
                    }
                }
            }
            q.removeZeros();
        }
    }

    public int[][] DocTermSparse(DocumentCollection c) {
        int sparse[][] = new int[c.getTerms().size()][contents.size()];
        for (int i = 0; i < c.getTerms().size(); i++) {
            for (int j = 0; j < contents.size(); j++) {
                sparse[i][j] = 0;
            }
        }

        for (Query d : contents) {
            for (Term t : d.contents()) {
                sparse[t.id() - 1][d.id - 1] = t.GetTimes();
            }
        }
        return sparse;
    }

}
