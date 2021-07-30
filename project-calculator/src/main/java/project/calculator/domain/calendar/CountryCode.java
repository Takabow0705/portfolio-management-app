package project.calculator.domain.calendar;

public enum CountryCode {
    JP("JP");

    CountryCode(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private final String value;
}
