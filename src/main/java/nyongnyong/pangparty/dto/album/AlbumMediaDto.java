package nyongnyong.pangparty.dto.album;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AlbumMediaDto {

    @Id
    private Long uid;
    @NotNull
    private Long albumUid;
    @UniqueElements
    private List<Long> albumMediaCommentsUid;
    @UniqueElements
    private List<Long> albumMediaLikesUid;
    private Long mediaUid;
    private String extension;
    private LocalDateTime uploadTime;
    private LocalDateTime takenTime;
    private String takenLat;
    private String takenLng;

}
