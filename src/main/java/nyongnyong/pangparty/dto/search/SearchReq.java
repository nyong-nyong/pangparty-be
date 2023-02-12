package nyongnyong.pangparty.dto.search;

import lombok.Builder;
import lombok.Data;
import nyongnyong.pangparty.common.CategoryType;
import nyongnyong.pangparty.common.SortType;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class SearchReq {

    @Builder.Default
    private CategoryType category = CategoryType.EVENT;
    private String keyword;
    @Builder.Default
    private SortType sort = SortType.POPULAR;
    @NotNull
    private Pageable pageable;
    private Long hashtagUid;

}
