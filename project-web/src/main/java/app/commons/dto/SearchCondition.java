package app.commons.dto;

import java.util.Optional;

/**
 * 検索条件を定義するDTOクラスです。
 */
public class SearchCondition {
    
    /** 範囲指定の始点に相当するフィールド*/
    private Long fromNumber;
    /** 範囲指定の終点に相当するフィールド*/
    private Long toNumber;
    /** 部分一致テキストを保持するフィールド*/
    private String partialMatchText;
    /** 完全一致を期待するテキストを保持するフィールド*/
    private String perfectMatchText;

    public Long getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(Long fromNumber) {
        this.fromNumber = fromNumber;
    }

    public Long getToNumber() {
        return toNumber;
    }

    public void setToNumber(Long toNumber) {
        this.toNumber = toNumber;
    }

    public String getPartialMatchText() {
        return partialMatchText;
    }

    public void setPartialMatchText(String partialMatchText) {
        this.partialMatchText = partialMatchText;
    }

    public String getPerfectMatchText() {
        return perfectMatchText;
    }

    public void setPerfectMatchText(String perfectMatchText) {
        this.perfectMatchText = perfectMatchText;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "fromNumber=" + fromNumber +
                ", toNumber=" + toNumber +
                ", partialMatchText='" + partialMatchText + '\'' +
                ", perfectMatchText='" + perfectMatchText + '\'' +
                '}';
    }
}
