package project.infra.rdb.user;


/**
 * ユーザ権限を表す列挙型
 *
 */
public enum UserAuthentication {
    ADMIN("ADMIN"),
    GENERAL("GENERAL"),
    UNDEFINED("UNDEFINED");

    /** DBで保持される値*/
    private String dbValue;

    /** コンストラクタ*/
    private UserAuthentication(String dbValue) {
        this.dbValue = dbValue;
    }

    /**
     *
     */
    public String getDbValue() {
    	return dbValue;
    }
    /**
     * <p>
     * 入力値を対応する列挙型に変換します。
     * 対応する値が存在しない場合は、UNDEFINEDを返す。
     * </p>
     *
     * @param val
     * @return UserAuthentication ユーザ権限の列挙型
     */
    public static UserAuthentication convertFrom(String val){
        for(UserAuthentication userAuth : UserAuthentication.values()){
            if(userAuth.getDbValue().equals(val)){
                return userAuth;
            }
        }
        return UserAuthentication.UNDEFINED;
    }
}
