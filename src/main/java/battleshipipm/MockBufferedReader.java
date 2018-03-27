package battleshipipm;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class MockBufferedReader extends BufferedReader {

    public MockBufferedReader(@NotNull Reader in) {
        super(in);
    }
    public int exceptionCount = 0;

    public String readLine() throws IOException {
        if (exceptionCount < 1) {
            this.exceptionCount++;
            throw new java.io.IOException();
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        }
    }

}
