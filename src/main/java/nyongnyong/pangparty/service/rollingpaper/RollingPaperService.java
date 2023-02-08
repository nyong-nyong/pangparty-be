package nyongnyong.pangparty.service.rollingpaper;

public interface RollingPaperService {
//    RollingPaperSimple findRollingPaperByEventUid(Long eventUid);

    /**
     * 롤링페이퍼 존재 여부
     * @param rollingPaperUid 롤링페이퍼 uid
     * @return  boolean 존재 여부
     */
    boolean isExistRollingPaperByRollingPaperUid(Long rollingPaperUid);
}
