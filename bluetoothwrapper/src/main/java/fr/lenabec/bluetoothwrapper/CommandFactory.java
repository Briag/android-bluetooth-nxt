package fr.lenabec.bluetoothwrapper;

import java.nio.charset.StandardCharsets;

/**
 * Created by Briag Le Nabec on 02/12/2014.
 */
public class CommandFactory {

    private byte getResponseByte(boolean hasResponse) {
        if(hasResponse)
            return 0x00;
        else
            return 0x01;
    }

    private byte boolToByte(boolean bool) {
        if(bool == true)
            return 0x01;
        else
            return 0x00;
    }

    public Command getStartProgramCommand(boolean hasResponse, String fileName){
        byte[] command = new byte[22];
        command[0] = getResponseByte(hasResponse);
        command[1] = 0x00;

        System.arraycopy(fileName.getBytes(StandardCharsets.US_ASCII), 0, command, 2, fileName.length());
        return new Command(CommandType.StartProgram, command);
    }

    public Command getStopProgram(boolean hasResponse){
        byte[] command = new byte[2];
        command[0] = getResponseByte(hasResponse);
        command[1] = 0x01;
        return new Command(CommandType.StopProgram, command);
    }

    public Command getPlaySoundFile(boolean hasResponse, boolean isLoop, String fileName){

        byte[] command = new byte[23];
        command[0] = getResponseByte(hasResponse);
        command[1] = 0x02;
        command[2] = boolToByte(isLoop);
        System.arraycopy(fileName.getBytes(StandardCharsets.US_ASCII), 0, command, 3, fileName.length());
        return new Command(CommandType.PlaySoundFile, command);
    }

    public Command getPlayTone(boolean hasResponse, int frequency, int duration){

        byte[] command = new byte[6];

        command[0] = getResponseByte(hasResponse);
        command[1] = 0x03;
        command[2] = (byte) (frequency);
        command[3] = (byte) (frequency >> 8);
        command[4] = (byte) (duration);
        command[5] = (byte) (duration >> 8);

        return new Command(CommandType.PlayTone, command);
    }

    public Command getSetOutputState(boolean hasResponse, int outputPort, int power, int mode, int regulation, int turnRatio, int runState, int tachoLimit){
/*
        byte[] command = new byte[13];

        command[0] = getResponseByte(hasResponse);
        command[1] = 0x03;
        command[2] = (byte) (frequency);
        command[3] = (byte) (frequency >> 8);
        command[4] = (byte) (duration);
        command[5] = (byte) (duration >> 8);
*/
        return null;
    }

    public Command getSetInputMode(){ return null;}
    public Command getGetOutputState(){ return null;}
    public Command getGetInputValue(){ return null;}
    public Command getResetInputScaledValue(){ return null;}
    public Command getMessageWrite(){ return null;}
    public Command getResetMotorPosition(){ return null;}
    public Command getGetBatteryLevel(){ return null;}
    public Command getStopSoundPlayback(){ return null;}
    public Command getKeepAlive(){ return null;}
    public Command getLSGetStatus(){ return null;}
    public Command getLSWrite(){ return null;}
    public Command getLSRead(){ return null;}
    public Command getGetCurrentProgramName(){return null;}
    public Command getMessageRead(){ return null;}

}
