package fr.lenabec.bluetoothwrapper;

/**
 * Created by Briag Le Nabec on 27/11/2014.
 */
public class Command {

    CommandType mType;
    byte[] mCommand;

    public Command(CommandType type, byte[] command) {
        mType = type;
        mCommand = command;
    }

    public byte[] getCommand() { return mCommand; }
    public int getReplyLength() { return 0; }

    public CommandType getType() {
        return mType;
    }
}
