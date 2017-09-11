package main;

import java.io.IOException;

/**
 * Created by Matt_Lab on 14/03/2017.
 */
public interface INet {
    void connect() throws IOException;

    void disconnect() throws IOException;

    void send(String data) throws IOException;

    String receive() throws IOException;

}

