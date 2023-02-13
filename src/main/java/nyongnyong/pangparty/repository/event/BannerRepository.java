package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.dto.event.BannerRes;
import nyongnyong.pangparty.entity.event.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    @Query("select new nyongnyong.pangparty.dto.event.BannerRes(b.uid, mp.id, b.content) from Banner b" +
            " left join Member m on b.member.uid = m.uid" +
            " left join MemberProfile mp on m.uid = mp.member.uid" +
            " where b.startTime <= current_timestamp and b.endTime >= current_timestamp")
    List<BannerRes> findBanners();
}
