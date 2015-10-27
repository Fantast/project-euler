package opener15;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Answer :
public class Task_35 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_35());
        Logger.close();
    }

    int n = 1017;
    Expr tx[] = new Expr[n];
    Expr ty[] = new Expr[n];
    Expr dx[] = new Expr[n];
    Expr dy[] = new Expr[n];

    public void solving() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("/downloads/task35.html"));

        Element body = doc.getDocumentElement();
        NodeList divs = body.getElementsByTagName("div");
        System.out.println(divs.getLength());
        for (int i = 0; i < 1; ++i) {
//        for (int i = 0; i < divs.getLength(); i+=2) {
            Element div = (Element) divs.item(i);
            NodeList maths = div.getElementsByTagName("math");

            tx[i / 2] = createMath((Element) maths.item(0));
            ty[i / 2] = createMath((Element) maths.item(1));
        }
    }

    public List<Element> getChildren(Node element) {
        Element e = (Element) element;
        NodeList children = e.getChildNodes();
        List<Element> result = new ArrayList<>();
        for (int i = 0; i < children.getLength(); i++) {
            Node item = children.item(i);
            if (item instanceof Element) {
                result.add((Element) item);
            }
        }
        return result;
    }

    public Expr createMath(Element math) throws TransformerException {
        List<Element> children = getChildren(math);
        assert children.size() == 1;
        return parseExpr(children.get(0));
    }

    private RowEl parseRowEl(Element rowEl) {
        switch (rowEl.getTagName()) {
            case "mrow":
                return parseRow(rowEl);
            case "mfrac":
                return parseFrac(rowEl);
            case "mo":
                return parseOperator(rowEl);
            case "mn":
                return parseNumber(rowEl);
            case "msqrt":
                return parseSqrt(rowEl);
            case "mfenced":
                return parseBraces(rowEl);
        }
        throw new IllegalArgumentException("Shouldn't happen");
    }

    private Expr parseExpr(Element expr) {
        RowEl rowEl = parseRowEl(expr);
        return (Expr) rowEl;
    }

    private Row parseRow(Element rowEl) {
        assert rowEl.getTagName().equals("mrow");
        return new Row(parseRowElList(rowEl));
    }

    private List<RowEl> parseRowElList(Element parent) {
        List<RowEl> result = new ArrayList<>();
        for (Element e : getChildren(parent)) {
            result.add(parseRowEl(e));
        }

        List<Expr> exprList = new ArrayList<>();

        int curr = 0;
        if (isMinus(result.get(0))) {
            Negate negate = new Negate((Expr) result.get(1));
        }

        for (int i = curr; i < result.size(); ++i) {
            List<Expr> mult = new ArrayList<>();
            for (int j = i + 1; j < result.size(); ++i) {


            }

        }

        return result;
    }

    public boolean isMinus(RowEl el) {
        return el instanceof Operator && ((Operator) el).text.equals("-");
    }

    public boolean isPlus(RowEl el) {
        return el instanceof Operator && ((Operator) el).text.equals("+");
    }

    public boolean isMult(RowEl el) {
        return !isMinus(el) && !isPlus(el);
    }

    private Frac parseFrac(Element frac) {
        List<Element> children = getChildren(frac);
        return new Frac(parseExpr(children.get(0)), parseExpr(children.get(1)));
    }

    private Number parseNumber(Element number) {
        return new Number(Integer.parseInt(number.getTextContent().trim()));
    }

    private Operator parseOperator(Element operator) {
        return new Operator(operator.getTextContent().trim());
    }

    private Sqrt parseSqrt(Element sqrt) {
        List<Element> children = getChildren(sqrt);
        assert children.size() == 1;
        return new Sqrt(parseExpr(children.get(0)));
    }

    private Braces parseBraces(Element braces) {
        return new Braces(parseRowElList(braces));
    }

    static abstract class RowEl {
    }

    static abstract class Expr extends RowEl {
    }

    static class Row extends Expr {
        List<RowEl> elements;

        public Row(List<RowEl> elements) {
            this.elements = elements;
        }
    }

    static class Frac extends Expr {
        Expr numer;
        Expr denom;

        public Frac(Expr numer, Expr denom) {
            this.numer = numer;
            this.denom = denom;
        }
    }

    static class Operator extends RowEl {
        String text;

        public Operator(String text) {
            assert text.equals("+") || text.equals("-") || text.equals("&#x2062;");
            this.text = text;
        }
    }

    static class Negate extends Expr {
        Expr of;

        public Negate(Expr of) {
            this.of = of;
        }
    }

    static class Number extends Expr {
        int value;

        public Number(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    static class Braces extends Expr {
        List<RowEl> elements;

        public Braces(List<RowEl> elements) {
            this.elements = elements;
        }
    }

    static class Sqrt extends Expr {
        Expr of;

        public Sqrt(Expr of) {
            this.of = of;
        }
    }
}

