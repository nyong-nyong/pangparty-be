package nyongnyong.pangparty.service.badge;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.dto.badge.BadgeRes;
import nyongnyong.pangparty.dto.badge.MemberBadgeRes;
import nyongnyong.pangparty.entity.badge.MemberBadge;
import nyongnyong.pangparty.repository.badge.BadgeRepository;
import nyongnyong.pangparty.repository.badge.MemberBadgeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;
    private final MemberBadgeRepository memberBadgeRepository;

    @Override
    @Transactional
    public List<BadgeRes> getBadgeList() {
        return badgeRepository.getBadgeList();
    }

    @Override
    @Transactional
    public List<MemberBadgeRes> getMemberBadgeList(String memberId) {

        List<MemberBadge> memberBadgeList = memberBadgeRepository.findByMemberUid(Long.parseLong(memberId));

        List<MemberBadgeRes> memberBadgeResList = new ArrayList<>();
        

    }
}
