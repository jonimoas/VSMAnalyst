package VSM;

import java.util.ArrayList;

public class Document {

    ArrayList<Term> contents = new ArrayList();
    int id;

    public Document(int id) {
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
                contents.add(new Term(term, 0));
            }
        }
    }

    public void Balanceid(ArrayList<Term> terms) {
        for (Term t : contents) {
            for (Term z : terms) {
                if (z.GetTerm().trim().equals(t.GetTerm().trim())) {
                    t.setId(z.id());
                }
            }
        }
    }

    public void Show() {
        for (Term t : contents) {
            System.out.println(t.GetTerm() + " " + t.GetTimes());
        }
    }

    public ArrayList<Term> Contents() {
        return contents;
    }
}
