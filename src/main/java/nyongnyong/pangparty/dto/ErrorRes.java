package nyongnyong.pangparty.dto;

import lombok.Data;

@Data
public class ErrorRes {
    private String message;
    private int status;
    private String code;

    public ErrorRes(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }
}
