package nyongnyong.pangparty.service.album;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.entity.album.AlbumMediaLike;
import nyongnyong.pangparty.exception.MemberNotFoundException;
import nyongnyong.pangparty.repository.album.AlbumMediaLikeRepository;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumMediaLikeServiceImpl implements AlbumMediaLikeService {

    private final AlbumMediaLikeRepository albumMediaLikeRepository;
    private final AlbumMediaRepository albumMediaRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void likeAlbumMedia(Long memberUid, Long albumMediaUid) {
        AlbumMedia albumMedia = albumMediaRepository.findById(albumMediaUid).orElseThrow(NoSuchElementException::new);
        albumMediaLikeRepository.save(
                AlbumMediaLike.builder()
                        .member(memberRepository.findById(memberUid).orElseThrow(MemberNotFoundException::new))
                        .albumMedia(albumMedia)
                        .likeTime(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    @Transactional
    public void unlikeAlbumMedia(Long memberUid, Long albumMediaUid) {
        albumMediaLikeRepository.deleteByAlbumMediaUidAndMemberUid(albumMediaUid, memberUid);
    }

    @Override
    public boolean likedAlbumMedia(Long memberUid, Long mediaUid) {
        return albumMediaLikeRepository.findByAlbumMediaUidAndMemberUid(mediaUid, memberUid) != null;
    }
}
