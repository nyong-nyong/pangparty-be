package nyongnyong.pangparty.service.rollingpaper;

import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;

public interface RollingPaperService {

    RollingPaper findRollingPaperByEventUid(Long eventUid);
}
