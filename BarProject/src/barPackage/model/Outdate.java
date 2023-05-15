package barPackage.model;

import java.time.LocalDate;

public class Outdate {
    private String name;
    private LocalDate outdate;
    private String type;

    public Outdate(String name, LocalDate outdate, String type) {
        this.name = name;
        this.outdate = outdate;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public LocalDate getOutdate() {
        return outdate;
    }
    public String getType() {
        return type;
    }


}
