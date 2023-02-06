package nyongnyong.pangparty.service.rollingpaper;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.entity.rollingpaper.RollingPaper;
import nyongnyong.pangparty.repository.rollingpaper.RollingPaperRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RollingPaperServiceImpl implements RollingPaperService{

    private final RollingPaperRepository rollingPaperRepository;

    @Override
    public RollingPaper findRollingPaperByEventUid(Long eventUid) {
        return rollingPaperRepository.findRollingPaperByEventUid(eventUid);
    }
}
