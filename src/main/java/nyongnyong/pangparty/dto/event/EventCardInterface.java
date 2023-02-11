package nyongnyong.pangparty.dto.event;


import java.time.LocalDate;

public interface EventCardInterface {
    Long getEventUid();
    String getEventName();
    String getTargetId();
    String getImgUrl();
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)   // LocalDateTime 변환 과정에서 오류나기도 하나봄..?
    LocalDate getDDay();
}
