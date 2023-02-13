package nyongnyong.pangparty.controller.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.common.CategoryType;
import nyongnyong.pangparty.dto.event.EventCard;
import nyongnyong.pangparty.dto.event.SimpleHashtagName;
import nyongnyong.pangparty.dto.member.FriendshipRes;
import nyongnyong.pangparty.dto.member.MemberProfileSimpleRes;
import nyongnyong.pangparty.dto.search.SearchReq;
import nyongnyong.pangparty.service.event.EventService;
import nyongnyong.pangparty.service.hashtag.HashtagService;
import nyongnyong.pangparty.service.member.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final EventService eventService;
    private final HashtagService hashtagService;
    private final MemberService memberService;

    @GetMapping("/event")
    public ResponseEntity<?> searchEvent(SearchReq conditions, Pageable pageable) {
        conditions.setCategory(CategoryType.EVENT);
        Page<EventCard> events = eventService.searchEvent(conditions, pageable);
        if (events.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/hashtag")
    public ResponseEntity<?> searchHashtag(SearchReq conditions, Pageable pageable) {
        conditions.setCategory(CategoryType.HASHTAG);
        Page<SimpleHashtagName> hashtags = hashtagService.searchHashtag(conditions, pageable);
        if (hashtags.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/member")
    public ResponseEntity<?> searchMember(SearchReq conditions, Pageable pageable) {
        conditions.setCategory(CategoryType.MEMBER);
        Page<MemberProfileSimpleRes> members = memberService.searchMember(conditions, pageable);
        if (members.isEmpty())
            return ResponseEntity.noContent().build();

        HashMap<String, Object> response = new HashMap<>();
        response.put("size", pageable.getPageSize());
        response.put("page", pageable.getPageNumber());
        response.put("itemCnt", members.getTotalElements());
        response.put("totalPageCnt", members.getTotalPages());
        response.put("members", members.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
