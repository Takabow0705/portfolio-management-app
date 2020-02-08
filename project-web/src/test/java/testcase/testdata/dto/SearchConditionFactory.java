package testcase.testdata.dto;

import app.commons.dto.SearchCondition;

public class SearchConditionFactory {

    /**
     * 部分一致条件が非nullの検索条件
     * @return
     */
    public static SearchCondition createCondition(){
        SearchCondition data = new SearchCondition();
        data.setFromNumber(Long.valueOf("1500"));
        data.setToNumber(Long.valueOf("2000"));
        data.setPartialMatchText("a");
        return data;
    }

    /**
     * 部分一致検索条件がnullの検索条件DTO
     * @return
     */
    public static SearchCondition createEmptyPartialTextCondition(){
        SearchCondition data = new SearchCondition();
        data.setFromNumber(Long.valueOf("1500"));
        data.setToNumber(Long.valueOf("2000"));
        data.setPartialMatchText(null);
        return data;
    }
}
