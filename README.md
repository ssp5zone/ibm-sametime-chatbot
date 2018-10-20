# IBM Sametime Bot

A chatbot integrated to IBM Sametime messaging app. You can start the app at any windows client. Once started all your messages would be replied by the bot.

## Working

This is made up of 2 parts,

### Java Code

The Java code does 4 things,

1. Maintains and uses the bots.
2. Activates the current chat to reply in it, when a new ping is recieved.
3. Reads the messages dumped in a local log file.
4. Talks to the Auto-It Script to write responses. _(Nope, you cant write them back to the log file. You have to actually send them!)_

### Auto-It Script

The Auto-It Scipt does 1 job,

1. Writes the responses back to the active chat window 

## Build

1. Clone or Download the repo.
2. Add the library files provided in `lib` folder.
3. Update all the file path locations to your local directory locations.
4. Build with the starting point as `Amber.java`.

## Run

1. Update the location of log file path to your local system log path.
2. Start the Java application.
3. Start the Auto-It Script.

## Warning

Be careful, if you have selected the bot as `Cleverbot`. The explicit filter is disabled for it. 
