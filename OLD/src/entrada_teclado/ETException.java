package entrada_teclado;

public class ETException extends RuntimeException {
    public ETException(String message) {
        super(message);
    }

    public ETException(Exception ex) {
        super(ex);
    }
}
