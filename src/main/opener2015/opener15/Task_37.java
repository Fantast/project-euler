package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.FileUtils;
import utils.OtherUtils;
import utils.log.Logger;

import java.io.IOException;

//Answer :
public class Task_37 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_37());
        Logger.close();
    }

    String pattern = "_____В______Т____________________Т____________________________________И________________Е____________________________________________________________Ч____ВФФ";

    public void solving() throws IOException {
        for (String qoute : FileUtils.readLines("/downloads/quotes.txt")) {
            qoute = transform(qoute);
            if (qoute.length() == pattern.length()) {
                char[] pchars = pattern.toCharArray();
                char[] schars = qoute.toCharArray();
                boolean found = true;
                for (int i = 0; i < pchars.length; ++i) {
                    if (pchars[i] != '_' && pchars[i] != schars[i]) {
                        found = false;
                    }
                }

                if (found) {
                    System.out.println(qoute);
                    return;
                }
            }
        }
    }

    private String transform(String qoute) {
        qoute = qoute
                .toUpperCase()
                .replace('Ё', 'Е')
                .replace('Э', 'Е')
                .replace('Й', 'И')
                .replace('Щ', 'Ш')
                .replaceAll("Ъ|Ь|[^А-Я]+", "");
        qoute += OtherUtils.replicate("Ф", (3-qoute.length()%3)%3);
        return qoute;
    }
}
