package fr.lenabec.bluetoothwrapper;

/**
 * Created by Briag Le Nabec on 27/11/2014.
 */
public class Response {

    CommandType mType;

    private byte[] mResponse;

    public Response(CommandType type, byte[] response) {
        mType = type;
        mResponse = response;
    }
}
