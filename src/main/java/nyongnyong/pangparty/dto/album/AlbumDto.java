package nyongnyong.pangparty.dto.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AlbumDto {

    @Id
    private Long uid;
    private String eventUid;
    private List<String> albumMediaUid;

}
