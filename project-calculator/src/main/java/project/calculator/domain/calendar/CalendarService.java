package project.calculator.domain.calendar;

import java.time.LocalDate;
import java.util.Set;

public interface CalendarService {
    public abstract Set<LocalDate> getBusinessDaysBefore(CountryCode countryCode, LocalDate endDate, long days);
    public abstract Set<LocalDate> getBusinessDaysBetween(CountryCode countryCode, LocalDate startDate, LocalDate endDate) ;
    public abstract Set<LocalDate> getBusinessDaysAfter(CountryCode countryCode, LocalDate startDate, long days);
}
