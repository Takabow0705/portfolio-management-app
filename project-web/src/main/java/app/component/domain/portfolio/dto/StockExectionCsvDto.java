package app.component.domain.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

public class StockExectionCsvDto {

    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
