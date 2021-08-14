package project.calculator.domain.calendar;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class BusinessDays {

    private final SortedSet<LocalDate> businessDays;

    private BusinessDays(SortedSet<LocalDate> businessDays) {
        this.businessDays = businessDays;
    }

    public static BusinessDays of(SortedSet<LocalDate> businessDays){
        return new BusinessDays(businessDays);
    }

    public static BusinessDays of(Set<LocalDate> businessDays){
        SortedSet<LocalDate> sortedSet = new TreeSet<>(businessDays);
        return new BusinessDays(sortedSet);
    }

    /**
     * 指定されたBusinessDayを取り除いた新しいBusinessDaysオブジェクトを作成する。
     *
     * @param businessDays
     * @return
     */
    public BusinessDays removeAll(BusinessDays businessDays){
        boolean target = this.businessDays.removeAll(businessDays.getBusinessDays());
        return new BusinessDays(new TreeSet<>(this.businessDays));
    }

    /**
     * BusinessDays オブジェクトが持つコレクションを不変オブジェクトとして返す。
     * このオブジェクトは順序付けられており、昇順にソートされている。
     * @return
     */
    public SortedSet<LocalDate> getBusinessDays(){
        return Collections.unmodifiableSortedSet(this.businessDays);
    }
}
