package project.calculator.domain.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import project.calculator.domain.repository.master.holidays.Holiday;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalendarServiceImplTest {
    private CalendarService calendarService;
    private HolidayService holidayService = mock(HolidayService.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        calendarService = new CalendarServiceImpl(holidayService);
    }

    @Nested
    class GetBusinessDaysBeforeMethodTest {

        /**
         * 開始日が平日で対象期間に休日を含まないケース
         */
        @Test
        void endDateIsBusinessDayAndNoHoliday() {
            // given
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(Collections.emptySet());
            // when
            List<LocalDate> result = calendarService.getBusinessDaysBefore(CountryCode.JP, LocalDate.of(2021, 4, 9), 2);

            assertThat(result.size(), equalTo(2));
            assertThat(result, containsInAnyOrder(LocalDate.of(2021, 4, 9),
                    LocalDate.of(2021, 4, 8)));
        }

        /**
         * 開始日が平日で対象期間に土日を含むケース
         */
        @Test
        void endDateIsBusinessDayAndContainsSundayAndSaturday() {
            // given
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(Collections.emptySet());
            // when
            List<LocalDate> result = calendarService.getBusinessDaysBefore(CountryCode.JP, LocalDate.of(2021, 4, 9), 9);

            assertThat(result.size(), equalTo(9));
            assertThat(result, containsInAnyOrder(LocalDate.of(2021, 4, 9),
                    LocalDate.of(2021, 4, 8),
                    LocalDate.of(2021, 4, 7),
                    LocalDate.of(2021, 4, 6),
                    LocalDate.of(2021, 4, 5),
                    LocalDate.of(2021, 4, 2),
                    LocalDate.of(2021, 4, 1),
                    LocalDate.of(2021, 3, 31),
                    LocalDate.of(2021, 3, 30)));
        }

        /**
         * 開始日が平日で対象期間に土日、祝日を含むケース
         */
        @Test
        void endDateIsBusinessDayAndContainsSundayAndSaturdayAndOtherHoliday() {
            // given
            Set<Holiday> holiday = Arrays.asList(create20210405()).stream().collect(Collectors.toSet());
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(holiday);
            // when
            List<LocalDate> result = calendarService.getBusinessDaysBefore(CountryCode.JP, LocalDate.of(2021, 4, 9), 9);

            assertThat(result.size(), equalTo(9));
            assertThat(result, containsInAnyOrder(LocalDate.of(2021, 4, 9),
                    LocalDate.of(2021, 4, 8),
                    LocalDate.of(2021, 4, 7),
                    LocalDate.of(2021, 4, 6),
                    LocalDate.of(2021, 4, 2),
                    LocalDate.of(2021, 4, 1),
                    LocalDate.of(2021, 3, 31),
                    LocalDate.of(2021, 3, 30),
                    LocalDate.of(2021, 3, 29)));
        }

        /**
         * 開始日が日曜日で対象期間に土日を含むケース
         */
        @Test
        void endDateIsHolidayAndContainsSundayAndSaturday() {
            // given
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(Collections.emptySet());

            // when
            List<LocalDate> result = calendarService.getBusinessDaysBefore(CountryCode.JP, LocalDate.of(2021, 4, 4), 2);

            assertThat(result.size(), equalTo(2));
            assertThat(result, containsInAnyOrder(LocalDate.of(2021, 4, 2),
                    LocalDate.of(2021, 4, 1)));
        }

        /**
         * 開始日が祝日で対象期間に土日を含むケース
         */
        @Test
        void endDateIsOtherHolidayAndContainsSundayAndSaturday() {
            // given
            Set<Holiday> holiday = Arrays.asList(create20210405()).stream().collect(Collectors.toSet());
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(holiday);

            // when
            List<LocalDate> result = calendarService.getBusinessDaysBefore(CountryCode.JP, LocalDate.of(2021, 4, 5), 2);

            assertThat(result.size(), equalTo(2));
            assertThat(result, containsInAnyOrder(LocalDate.of(2021, 4, 2),
                    LocalDate.of(2021, 4, 1)));
        }
    }

    @Nested
    class TestGetBusinessDaysBetween {

        /**
         * 開始日付、終了日付ともに営業日で期間に休日がないケース
         */
        @Test
        public void case1() {
            // given
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(Collections.emptySet());
            LocalDate startDate = LocalDate.of(2021, 4, 5);
            LocalDate endDate = LocalDate.of(2021, 4, 9);

            List<LocalDate> result = calendarService.getBusinessDaysBetween(CountryCode.JP, startDate, endDate);
            assertThat(result.size(), equalTo(5));
            assertThat(result, containsInAnyOrder(
                    LocalDate.of(2021, 4, 9),
                    LocalDate.of(2021, 4, 8),
                    LocalDate.of(2021, 4, 7),
                    LocalDate.of(2021, 4, 6),
                    LocalDate.of(2021, 4, 5)
            ));
        }

        /**
         * 開始日付が休日、終了日付が営業日で期間に休日がないケース
         */
        @Test
        public void case2() {
            // given
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(Collections.emptySet());
            LocalDate startDate = LocalDate.of(2021, 4, 4);
            LocalDate endDate = LocalDate.of(2021, 4, 9);

            List<LocalDate> result = calendarService.getBusinessDaysBetween(CountryCode.JP, startDate, endDate);
            assertThat(result.size(), equalTo(5));
            assertThat(result, containsInAnyOrder(
                    LocalDate.of(2021, 4, 9),
                    LocalDate.of(2021, 4, 8),
                    LocalDate.of(2021, 4, 7),
                    LocalDate.of(2021, 4, 6),
                    LocalDate.of(2021, 4, 5)
            ));
        }
        /**
         * 開始日付が休日、終了日付が営業日で期間に祝日が存在するケース
         */
        @Test
        public void case3() {
            // given
            Set<Holiday> holiday = Arrays.asList(create20210405()).stream().collect(Collectors.toSet());
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(holiday);
            LocalDate startDate = LocalDate.of(2021, 4, 4);
            LocalDate endDate = LocalDate.of(2021, 4, 9);

            List<LocalDate> result = calendarService.getBusinessDaysBetween(CountryCode.JP, startDate, endDate);
            assertThat(result.size(), equalTo(4));
            assertThat(result, containsInAnyOrder(
                    LocalDate.of(2021, 4, 9),
                    LocalDate.of(2021, 4, 8),
                    LocalDate.of(2021, 4, 7),
                    LocalDate.of(2021, 4, 6)
            ));
        }
        /**
         * 開始日付、終了日付がで期間に休日がないケース
         */
        @Test
        public void case4() {
            // given
            when(holidayService.getHolidayByCountryCode(CountryCode.JP)).thenReturn(Collections.emptySet());
            LocalDate startDate = LocalDate.of(2021, 4, 4);
            LocalDate endDate = LocalDate.of(2021, 4, 10);

            List<LocalDate> result = calendarService.getBusinessDaysBetween(CountryCode.JP, startDate, endDate);
            assertThat(result.size(), equalTo(5));
            assertThat(result, containsInAnyOrder(
                    LocalDate.of(2021, 4, 9),
                    LocalDate.of(2021, 4, 8),
                    LocalDate.of(2021, 4, 7),
                    LocalDate.of(2021, 4, 6),
                    LocalDate.of(2021, 4, 5)
            ));
        }
    }

    private Holiday create20210405() {
        Holiday holiday = new Holiday();
        holiday.setDate(LocalDate.of(2021, 4, 5));
        return holiday;
    }
}