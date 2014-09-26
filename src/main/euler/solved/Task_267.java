package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigDecimal;
import java.math.MathContext;

//Answer : 0.999992836187
public class Task_267 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_267());
        Logger.close();
    }
    long bill = 1000000000;
    BigDecimal bbill = BigDecimal.valueOf(bill);
    int n = 1000;

    final double eps = 1e-13;
    public void solving() {
        //S = (1+2f)^p * (1-f)^(1000-p)
        //                    9*ln(10) - 1000*ln(1-f)
        //ln => .. ==> p >= [------------------------] = f(f)
        //                     ln( (1+2f)/(1-f) )
        //==>
        // http://www.wolframalpha.com/input/?i=minimum+[%289*ln%2810%29+-+1000*ln%281-f%29%29%2Fln%28+%281%2B2*f%29%2F%281-f%29+%29]%2C+f%3E0%2C+f%3C1
        //  min(f) = f(~0.147) = 431.3
        //==> p>=432
        // prob(win) = sum(i=432,1000)C(1000, i) / sum(j=0,1000)C(1000, j)

        System.out.println(new BigDecimal("10715009310986706622976830743544649495896214324259539098932264049072379314512407232073833609668321252964102895207546891849796548713857479451589212314427736531426024278186731964268619965741567042236755578759455447651526922789161844269327874331783632919701274345086491008794818316051766995043968604093048")
                .divide(
                new BigDecimal("10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376"))
                .round(new MathContext(12)));
    }
}
