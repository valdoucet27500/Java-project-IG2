package barPackage.exceptions;

public class NumberInputValueException extends Exception{
    Double value;
    Double maxValue;
    Double minValue;

    public NumberInputValueException(String message, Double value, Double minValue, Double maxValue) {
        super(message);
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public Double getValue() {
        return value;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(minValue);
        sb.append(" < valeur < ");
        sb.append(maxValue);
        return sb.toString();
    }
}
