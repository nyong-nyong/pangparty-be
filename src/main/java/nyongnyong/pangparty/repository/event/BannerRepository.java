package nyongnyong.pangparty.repository.event;

import nyongnyong.pangparty.entity.event.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
