package com.example.sam.textadventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Obifang on 2016-10-17.
 */
public class CommandProcessor extends GameObject{
    private List<Command> listOfCommands;
    private Player player;
    private String [] command;

    public CommandProcessor (Player p, String commands)
    {
        super(new String [] {"CommandProcessor"}, "Command Processor", "Command Processor");
        listOfCommands = new ArrayList<>();
        player = p;
        command = commands.toLowerCase().split (" ");

        AddCommands ();
    }

    public String Execute ()
    {
        Command commands;
        commands = SearchForCommand ();
        if (commands != null) {
            return commands.Execute (player, command);
        } else
            return "Command does not exist: " + IdentifyTypedCommand ();
    }

    //Add the available commands to the commands list
    private void AddCommands ()
    {
        LookCommand look = new LookCommand ();
        MoveCommand move = new MoveCommand ();
        PutCommand put = new PutCommand();
        TakeCommand take = new TakeCommand();

        listOfCommands.add (move);
        listOfCommands.add (look);
        listOfCommands.add(put);
        listOfCommands.add(take);

    }

    //Identify the first work in the request
    private String IdentifyTypedCommand ()
    {
        return command [0];
    }

    //Identifies the command request and returns it
    private Command SearchForCommand ()
    {
        Command commands = null;
        //Looks through the list of commands for a command
        for (Command commandUse:listOfCommands) {
            if(commandUse.getFirstID().equals(IdentifyTypedCommand())){
                commands = commandUse;
                break;
            }
        }
        return commands;
    }
}
