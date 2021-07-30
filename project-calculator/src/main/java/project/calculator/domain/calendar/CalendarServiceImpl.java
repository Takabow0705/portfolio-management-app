package project.calculator.domain.calendar;

import org.springframework.stereotype.Service;
import project.calculator.domain.repository.master.holidays.Holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final HolidayService holidayService;

    public CalendarServiceImpl(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    /**
     * 指定日付から営業日換算で指定日数分の過去日付を取得した際の日付のリストを返す。
     * 指定日付は返却される過去日付に含まれる。
     * 祝日は国コードに紐ついた祝日を参照し、これはHolidayテーブルに登録されたマスターデータを参照している。
     * <p>
     * 例： countryCode = JP, endDate = 20210409, days = 3
     * 戻り値： [20210409,20210408,20210407]
     *
     * @param countryCode
     * @param endDate
     * @param days
     * @return
     */
    @Override
    public List<LocalDate> getBusinessDaysBefore(CountryCode countryCode, LocalDate endDate, long days) {
        if (days < 0) {
            return Collections.emptyList();
        }
        Set<LocalDate> holidays = this.holidayService.getHolidayByCountryCode(countryCode).stream().map(Holiday::getDate).collect(Collectors.toSet());
        List<LocalDate> businessDays = Stream.iterate(0l, l -> l + 1)
                .map(l -> endDate.minusDays(l))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(date -> !holidays.contains(date))
                .limit(days)
                .collect(Collectors.toList());
        return businessDays;
    }

    /**
     * 開始日付から終了日付までの営業日のリストを返す。
     * 開始日付と終了日付は返り値のリストに含まれる。
     *
     * @param countryCode
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public List<LocalDate> getBusinessDaysBetween(CountryCode countryCode, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return Collections.emptyList();
        }
        Set<LocalDate> holidays = this.holidayService.getHolidayByCountryCode(countryCode).stream().map(Holiday::getDate).collect(Collectors.toSet());
        List<LocalDate> businessDays = Stream.concat(startDate.datesUntil(endDate), Stream.of(endDate))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(date -> !holidays.contains(date))
                .collect(Collectors.toList());
        return businessDays;
    }

    @Override
    public List<LocalDate> getBusinessDaysAfter(CountryCode countryCode, LocalDate startDate, long days) {
        if (days < 0) {
            return Collections.emptyList();
        }
        Set<LocalDate> holidays = this.holidayService.getHolidayByCountryCode(countryCode).stream().map(Holiday::getDate).collect(Collectors.toSet());
        List<LocalDate> businessDays = Stream.iterate(0l, l -> l + 1)
                .map(l -> startDate.plusDays(l))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(date -> !holidays.contains(date))
                .limit(days)
                .collect(Collectors.toList());
        return businessDays;
    }
}
