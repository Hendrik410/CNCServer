# CNCServer
A server-solution to control a CNC device with GCode via a webinterface written in Java


Changes:
commit #1
  -Added classes for serial communication
  -Added InputListener for userinput
    type quit to end properly
    type send [cmd] and [cmd] will be send over serial
    type help to view help
