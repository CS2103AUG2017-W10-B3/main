package seedu.address.logic.commands;

import java.io.File;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.SwitchAddressBookRequestEvent;
import seedu.address.logic.commands.exceptions.CommandException;

//@@author chrisboo
/**
 * Open Address Book
 */
public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";
    public static final String COMMAND_SHORT = "o";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Open a different DeathNote. \n"
        + "Paremeters: LOCATION \n"
        + "Example: " + COMMAND_WORD + " C:\\Users\\crispy\\Downloads\\DeathNote.xml";

    public static final String MESSAGE_OPEN_PERSON_SUCCESS = "Opened DeathNote: %1$s";

    private final File file;

    public OpenCommand(File file) {
        this.file = file;
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            EventsCenter.getInstance().post(new SwitchAddressBookRequestEvent(file, false));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CommandResult(String.format(MESSAGE_OPEN_PERSON_SUCCESS, file.getPath()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof OpenCommand // instanceof handles nulls
            && this.file.equals(((OpenCommand) other).file)); // state check
    }
}
//@@author