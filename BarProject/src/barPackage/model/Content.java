package barPackage.model;

import barPackage.exceptions.NumberInputValueException;

import java.time.LocalDate;

public class Content {
    private final static double MAX_QUANTITY = 99999999;
    private Integer id;
    private String consumableName;
    private Double quantity;
    private LocalDate expirationDate;
    private String unit;

    public Content(Integer id, String consumableName, Double quantity, LocalDate expirationDate, String unit) throws NumberInputValueException {
        this.unit = unit;
        this.id = id;
        this.consumableName = consumableName;
        setQuantity(quantity);
        this.expirationDate = expirationDate;
    }


    public Content(String consumableName, Double quantity, LocalDate expirationDate) throws NumberInputValueException {
        this(null, consumableName, quantity, expirationDate, null);
    }

    public Content(String consumableName, Double quantity) throws NumberInputValueException {
        this(consumableName, quantity, null);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantity(Double quantity) throws NumberInputValueException {
        if (quantity <= MAX_QUANTITY) {
            this.quantity = quantity;
        } else {
            throw new NumberInputValueException("La quantité ne peut pas dépasser " + MAX_QUANTITY, quantity, MAX_QUANTITY, 0.0);
        }
    }

    public void addQuantity(Double quantity) {
        this.quantity += quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    public String getConsumableName() {
        return consumableName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
