package nyongnyong.pangparty.repository.member;

import nyongnyong.pangparty.entity.member.MemberSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSettingRepository extends JpaRepository<MemberSetting, Long> {
}
