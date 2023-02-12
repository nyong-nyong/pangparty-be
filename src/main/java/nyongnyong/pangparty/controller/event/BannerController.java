package nyongnyong.pangparty.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.event.BannerRes;
import nyongnyong.pangparty.service.event.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banners")
@RequiredArgsConstructor
@Slf4j
public class BannerController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<?> findBanners(){
        try{
            Map<String, Object> response = new HashMap<>();
            List<BannerRes> banners = eventService.findBanners();
            System.out.println(banners.get(0).getContent());
            response.put("banners", banners);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
