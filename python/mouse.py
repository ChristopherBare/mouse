import pyautogui as mouse
import time
import datetime
datetime.tzinfo = "EST"
now = datetime.datetime.now().time()
start = datetime.time(8)
end = datetime.time(17)
mouse.failsafe = False

while(start.hour <= now.hour <= end.hour):
    starting_position = mouse.position()
    print("sleeping 1 minute")
    time.sleep(60)
    if(mouse.position() != starting_position):
        print("mouse moved to position {}".format(mouse.position()))
        print("sleeping 1 minute")
        time.sleep(60)
    else:
        print("mouse not moving")
        print("moving mouse for you")
        mouse.moveTo(mouse.position().x, mouse.position().y + 100)
        mouse.moveTo(mouse.position().x, mouse.position().y - 100)
        print("pressing shift 3 times")
        mouse.press('shift', presses=3)
        print("movement made at {} to position {}".format(now, mouse.position()))
    time.sleep(10)
