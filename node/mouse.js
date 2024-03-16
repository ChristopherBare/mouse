const robot = require('robotjs');

const START_HOUR = 8;
const END_HOUR = 17;
const ONE_MINUTE = 60000;
const TEN_SECONDS = 10000;

function moveMouse(x, y) {
    robot.moveMouseSmooth(x, y);
}

function run() {
    const startTime = new Date();

    setInterval(() => {
        const currentTime = new Date();
        const elapsedTime = currentTime - startTime;

        if (elapsedTime >= ONE_MINUTE) {
            // One minute has passed, perform mouse movement check
            console.log("Checking mouse movement...");
            startTime = currentTime;
        }
    }, TEN_SECONDS);
}

// Example usage of moveMouse function
moveMouse(100, 100);

run();
