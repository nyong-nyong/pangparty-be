package nyongnyong.pangparty.repository.auth;

import nyongnyong.pangparty.entity.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}