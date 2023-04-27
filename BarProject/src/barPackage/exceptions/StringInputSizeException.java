package barPackage.exceptions;

public class StringInputSizeException extends Exception {
    int size;
    int maxSize;
    public StringInputSizeException(String message, int size, int maxSize) {
        super(message);
        this.size = size;
        this.maxSize = maxSize;
    }

    public int getSize() {
        return size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String toString() {
        return super.toString() + "\nTaille: " + size + " Taille max: " + maxSize;
    }
}
