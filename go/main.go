package main

import (
	"fmt"
	"github.com/go-vgo/robotgo"
	"time"
)

const (
	startHour  = 8
	endHour    = 17
	oneMinute  = time.Minute
	tenSeconds = 10 * time.Second
	exampleX   = 100
	exampleY   = 100
)

func moveMouse() {
	robotgo.MoveSmooth(exampleX, exampleY)
}

func checkMouseMovement() {
	startingPositionX, startingPositionY := robotgo.Location()

	fmt.Println("Sleeping for 1 minute...")
	time.Sleep(oneMinute)

	currentPositionX, currentPositionY := robotgo.Location()

	if startingPositionX == currentPositionX && startingPositionY == currentPositionY {
		fmt.Println("Mouse not moving")
		fmt.Println("Moving mouse for you")
		moveMouse()
		fmt.Printf("Movement made at %v to position %v %v\n", time.Now(), currentPositionX, currentPositionY)
	} else {
		fmt.Printf("Mouse moved to position %v\n", currentPositionX, currentPositionY)
		fmt.Println("Sleeping for 1 minute...")
		time.Sleep(oneMinute)
	}

	fmt.Println("Sleeping for 10 seconds...")
	time.Sleep(tenSeconds)

	currentHour := time.Now().Hour()

	if currentHour < startHour || currentHour > endHour {
		fmt.Println("Work hours over. Exiting program.")
		return
	}
}

func main() {
	for {
		checkMouseMovement()
	}
}
