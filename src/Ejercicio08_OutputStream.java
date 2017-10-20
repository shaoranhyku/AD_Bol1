import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Ejercicio08_OutputStream extends ObjectOutputStream {
    public Ejercicio08_OutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected Ejercicio08_OutputStream() throws IOException, SecurityException {
        super();
    }

    protected void writeStreamHeader() throws IOException
    { }
}
