package fr.lenabec.bluetoothwrapper;

/**
 * Created by Briag Le Nabec on 27/11/2014.
 */
public enum CommandType {

    StartProgram,
    StopProgram,
    PlaySoundFile,
    PlayTone,
    SetOutputState,
    SetInputMode,
    GetOutputState,
    GetInputValue,
    ResetInputScaledValue,
    MessageWrite,
    ResetMotorPosition,
    GetBatteryLevel,
    StopSoundPlayback,
    KeepAlive,
    LSGetStatus,
    LSWrite,
    LSRead,
    GetCurrentProgramName,
    MessageRead
}
