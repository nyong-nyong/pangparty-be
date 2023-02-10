package nyongnyong.pangparty.service.album;

import nyongnyong.pangparty.entity.album.AlbumMediaLike;
import nyongnyong.pangparty.repository.album.AlbumMediaLikeRepository;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;

import java.time.LocalDateTime;

public class AlbumMediaLikeServiceImpl implements AlbumMediaLikeService {

    private final AlbumMediaLikeRepository albumMediaLikeRepository;
    private final AlbumMediaRepository albumMediaRepository;
    private final MemberRepository memberRepository;

    public AlbumMediaLikeServiceImpl(AlbumMediaLikeRepository albumMediaLikeRepository, AlbumMediaRepository albumMediaRepository, MemberRepository memberRepository) {
        this.albumMediaLikeRepository = albumMediaLikeRepository;
        this.albumMediaRepository = albumMediaRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void likeAlbumMedia(Long albumMediaUid, Long memberUid) {
        albumMediaLikeRepository.save(
                AlbumMediaLike.builder()
                        .member(memberRepository.findById(memberUid).get())
                        .albumMedia(albumMediaRepository.findById(albumMediaUid).get())
                        .likeTime(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public void unlikeAlbumMedia(Long albumMediaUid, Long memberUid) {
        albumMediaLikeRepository.deleteByAlbumMediaUidAndMemberUid(albumMediaUid, memberUid);
    }
}
