package app.domain.portfolio;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CsvUploadResult {

    private final List<String> errorMessages;
    private final List<String> warnMessages;
    private UploadStatus status;

    public CsvUploadResult() {
        this.errorMessages = new ArrayList<>();
        this.warnMessages = new ArrayList<>();
    }

    /**
     * エラーメッセージの存在判定
     * @return
     */
    public boolean hasError(){
        return !errorMessages.isEmpty();
    }

    public void setStatus(UploadStatus status){
        this.status = status;
    }

    public void merge(CsvUploadResult result){
        this.errorMessages.addAll(result.getErrorMessages());
        this.warnMessages.addAll(result.getWarnMessages());
    }
    public void registerErrorMesasge(String message){
        errorMessages.add(message);
    }

    public void registerErrorMesasge(List<String> message){
        errorMessages.addAll(message);
    }

    public List<String> getErrorMessages(){
        List<String> clone = Collections.unmodifiableList(this.errorMessages);
        return clone;
    }

    public List<String> getWarnMessages(){
        List<String> clone = Collections.unmodifiableList(this.warnMessages);
        return clone;
    }

    /**
     * アップロード結果をHTTPレスポンス形式に変換する。
     * @return
     */
    public ResponseEntity<CsvUploadResult> convertToHttpResponse(){
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<CsvUploadResult> resultResponseEntity = new ResponseEntity<>(this, headers, HttpStatus.OK);
        return resultResponseEntity;
    }
    public enum UploadStatus{
        OK,
        ERROR,
        WARN
    }
}
