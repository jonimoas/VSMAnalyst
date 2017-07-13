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
public class DocumentCollection {

    ArrayList<Document> contents = new ArrayList();
    ArrayList<Term> terms = new ArrayList();
    boolean compiled;

    public DocumentCollection() {
        this.compiled = false;
    }

    public void add(Document p) {
        contents.add(p);
    }

    public void compile() {
        boolean exists = false;
        int id = 1;
        for (Document p : contents) {
            for (Term t : p.Contents()) {
                exists = false;
                for (Term termin : terms) {
                    if (t.GetTerm().equals(termin.GetTerm())) {
                        exists = true;
                        termin.adddoc(p.id);
                    }
                }
                if (!exists) {
                    Term temp = new Term(t.GetTerm(), id);
                    id++;
                    temp.adddoc(p.id);
                    terms.add(temp);
                }
            }
        }
        for (Document p : contents) {
            p.Balanceid(terms);
            for (Term t : terms) {
                for (Term k : p.Contents()) {
                    if (t.GetTerm().equals(k.GetTerm())) {
                        k.docs = t.getdocs();
                    }
                }
            }
        }

        this.compiled = true;
    }

    public String DocTerm() {
        String result = "";
        for (Term t : terms) {
            for (int i : t.getdocs()) {
                result = result + (i + " ");
            }
            result = result + "\n";
        }
        return result;
    }

    public String TermTable() {
        String result = "";
        for (Term t : terms) {
            result = result + t.GetTerm() + "\n";
        }
        return result;
    }

    public String TermIdf() {
        String result = "";
        for (Term t : terms) {
            result = result + (t.IDF(contents.size()) +"\n");
        }
        return result;
    }

    public String TermF() {
        String result = "";
        for (Term t : terms) {
            result = result + (t.GetTerm() + " " + (double) t.getdocs().size() / terms.size() + "\n");
        }
        return result;
    }

    public String TermTimes() {
        String result = "";
        for (Term t : terms) {
            result = result + (t.GetTerm() + " " + t.getdocs().size() + "\n");
        }
        return result;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public ArrayList<Document> GetDocs() {
        return contents;
    }

    public int[][] DocTermSparse() {
        int sparse[][] = new int[terms.size()][contents.size()];
        for (int x = 0; x < terms.size(); x++) {
            for (int y = 0; y < contents.size(); y++) {
                sparse[x][y] = 0;
            }
        }
        for (Document d : contents) {
            for (Term t : d.Contents()) {
                sparse[t.id() - 1][d.id - 1] = t.GetTimes();
            }
        }
        return sparse;
    }

    public double[][] tfidfSparse() {
        double sparse[][] = new double[terms.size()][contents.size()];
        for (int x = 0; x < terms.size(); x++) {
            for (int y = 0; y < contents.size(); y++) {
                sparse[x][y] = 0;
            }
        }
        for (Document d : contents) {
            for (Term t : d.Contents()) {
                sparse[t.id() - 1][d.id - 1] = t.IDF(contents.size());
            }
        }
        System.out.println("tfidf matrix done");
        return sparse;
    }

    public double[][] tfidfSparseW() {
        double sparse[][] = new double[terms.size()][contents.size()];
        for (int x = 0; x < terms.size(); x++) {
            for (int y = 0; y < contents.size(); y++) {
                sparse[x][y] = 0;
            }
        }
        for (Document d : contents) {
            for (Term t : d.Contents()) {
                sparse[t.id() - 1][d.id - 1] = t.GetTimes() * t.IDF(contents.size());
            }
        }
        System.out.println("tfidf matrix done");
        return sparse;
    }
}
