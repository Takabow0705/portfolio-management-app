package project.calculator.domain.calendar;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {
    public abstract List<LocalDate> getBusinessDaysBefore(CountryCode countryCode, LocalDate endDate, long days);
    public abstract List<LocalDate> getBusinessDaysBetween(CountryCode countryCode, LocalDate startDate, LocalDate endDate) ;
    public abstract List<LocalDate> getBusinessDaysAfter(CountryCode countryCode, LocalDate startDate, long days);
}
