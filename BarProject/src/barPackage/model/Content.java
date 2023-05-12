package barPackage.model;

import java.time.LocalDate;

public class Content {
    private Integer id;
    private String consumableName;
    private Double quantity;
    private LocalDate expirationDate;

    public Content(Integer id, String consumableName, Double quantity, LocalDate expirationDate) {
        this.id = id;
        this.consumableName = consumableName;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public Content(String consumableName, Double quantity, LocalDate expirationDate) {
        this(null, consumableName, quantity, expirationDate);
    }

    public Content(String consumableName, Double quantity) {
        this(consumableName, quantity, null);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setConsumableName(String consumableName) {
        this.consumableName = consumableName;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getId() {
        return id;
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

}
