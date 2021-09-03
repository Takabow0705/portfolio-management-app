package project.calculator.domain.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import project.infra.rdb.holiday.Holiday;
import project.infra.rdb.holiday.HolidayRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HolidayServiceCacheImpl implements HolidayService{
    private final HolidayRepository holidayRepository;
    private static final Logger logger = LoggerFactory.getLogger(HolidayServiceCacheImpl.class);

    HolidayServiceCacheImpl(HolidayRepository holidayRepository){
        this.holidayRepository = holidayRepository;
    }

    @Override
    @Cacheable(cacheNames = "holidays", value = "holidays")
    public Set<Holiday> getHolidayByCountryCode(CountryCode countryCode) {
        return this.holidayRepository.findAll().stream().filter(h -> countryCode.getValue().equals(h.getCountryCode())).collect(Collectors.toSet());
    }
}
