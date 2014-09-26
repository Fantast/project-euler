package tasks;

import utils.log.Logger;

//Answer :
public class Task_ extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_());
        Logger.close();
    }

    public void solving() {

    }
}
