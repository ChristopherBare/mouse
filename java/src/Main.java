import java.awt.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TimeZone;

public class Main {
    private static final int START_HOUR = 8;
    private static final int END_HOUR = 17;
    private static final int ONE_MINUTE = 60000;
    private static final int TEN_SECONDS = 10000;

    public static void main(String[] args) {
        try {
            TimeZone timeZone = TimeZone.getTimeZone("EST");
            Calendar calendar = Calendar.getInstance(timeZone);

            Robot robot = new Robot();
            robot.setAutoWaitForIdle(false);

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    checkMouseMovement(robot, calendar, timer, () -> moveMouse(robot));
                }
            };

            timer.schedule(task, 0, TEN_SECONDS);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void checkMouseMovement(Robot robot, Calendar calendar, Timer timer, Runnable mouseMoveCallback) {
        Point startingPosition = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Sleeping for 1 minute...");
        try {
            Thread.sleep(ONE_MINUTE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Point currentPosition = MouseInfo.getPointerInfo().getLocation();

        if (!startingPosition.equals(currentPosition)) {
            System.out.println("Mouse moved to position " + currentPosition);
            System.out.println("Sleeping for 1 minute...");
            try {
                Thread.sleep(ONE_MINUTE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Mouse not moving");
            System.out.println("Moving mouse for you");
            mouseMoveCallback.run();
            System.out.println("Movement made at " + Calendar.getInstance().getTime() + " to position " + currentPosition);
        }

        System.out.println("Sleeping for 10 seconds...");
        try {
            Thread.sleep(TEN_SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        calendar.setTimeInMillis(System.currentTimeMillis());
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        if (currentHour < START_HOUR || currentHour > END_HOUR) {
            timer.cancel();
            System.out.println("Work hours over. Exiting program.");
        }
    }

    public static void moveMouse(Robot robot) {
        Point currentPosition = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove((int) currentPosition.getX(), (int) (currentPosition.getY() + 100));
        robot.mouseMove((int) currentPosition.getX(), (int) (currentPosition.getY() - 100));
    }
}
