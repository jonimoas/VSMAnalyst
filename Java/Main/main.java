/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import VSM.Document;
import VSM.DocumentCollection;
import XML.DOMParser;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import Utils.Printer;
import VSM.Query;
import VSM.QueryCollection;

/**
 *
 * @author joni
 */
public class main {

    static DOMParser QueriesDOM;
    static NodeList queries, docs;
    static DocumentCollection docs2;
    static QueryCollection query;

    public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, Exception {
        queries = new DOMParser("..\\IO\\input\\queries.xml").ReadnodeList("/catalog/doc");
        docs = new DOMParser("..\\IO\\input\\docs.xml").ReadnodeList("/catalog/doc");
        System.out.println("XML parsers setup");
        docs2 = makeCollection(docs);
        docs2.compile();
        System.out.println("VSM docs setup");
        query = makeQuery(queries);
        System.out.println("VSM query setup");
        query.compile(docs2);
        System.out.println("Preparing docterm matrices");
        Printer.arrayPrint(docs2.DocTermSparse(), "..\\IO\\VSM\\doc-term-sparse.txt");
        Printer.arrayPrint(docs2.tfidfSparseW(), "..\\IO\\VSM\\tfidf-sparse-W.txt");
        System.out.println("Preparing term matrix");
        Printer.print(docs2.TermTable(), "..\\IO\\VSM\\term.txt");
        Printer.print(docs2.TermIdf(), "..\\IO\\VSM\\termIdf.txt");
        System.out.println("Preparing query vectors");
        Printer.arrayPrint(query.DocTermSparse(docs2), "..\\IO\\VSM\\query-term.txt");
        System.out.println("Data Exported");
    }

    public static DocumentCollection makeCollection(NodeList l) {
        DocumentCollection temp = new DocumentCollection();
        int i = 0;
        while (i < l.getLength()) {
            String doc = l.item(i).getChildNodes().item(3).getTextContent();
            String[] terms = doc.split(" ");
            Document d = new Document(Integer.parseInt(l.item(i).getChildNodes().item(1).getTextContent()));
            for (String t : terms) {
                d.AddTerm(t);
            }
            temp.add(d);
            i++;
        }
        return temp;
    }

    public static QueryCollection makeQuery(NodeList l) {
        QueryCollection temp = new QueryCollection();
        int i = 0;
        while (i < l.getLength()) {
            String doc = l.item(i).getChildNodes().item(3).getTextContent();
            String[] terms = doc.split(" ");
            Query d = new Query(Integer.parseInt(l.item(i).getChildNodes().item(1).getTextContent()));
            for (String t : terms) {
                d.AddTerm(t);
            }
            temp.add(d);
            i++;
        }
        return temp;
    }
}
