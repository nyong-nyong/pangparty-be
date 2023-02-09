package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaCommentSimpleRes;
import nyongnyong.pangparty.entity.album.AlbumMediaComment;
import nyongnyong.pangparty.repository.album.AlbumMediaCommentRepository;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AlbumMediaCommentServiceImpl implements AlbumMediaCommentService {

    private final MemberRepository memberRepository;
    private final AlbumMediaRepository albumMediaRepository;
    private final AlbumMediaCommentRepository albumMediaCommentRepository;

    public AlbumMediaCommentServiceImpl(MemberRepository memberRepository, AlbumMediaRepository albumMediaRepository, AlbumMediaCommentRepository albumMediaCommentRepository) {
        this.memberRepository = memberRepository;
        this.albumMediaRepository = albumMediaRepository;
        this.albumMediaCommentRepository = albumMediaCommentRepository;
    }

    @Override
    public AlbumMediaCommentSimpleRes createAlbumMediaComment(Long memberUid, Long albumMediaUid, String comment) {
        //TODO: create album media comment
        albumMediaCommentRepository.save(
                AlbumMediaComment.builder()
                        .member(memberRepository.findById(memberUid).get())
                        .albumMedia(albumMediaRepository.findById(albumMediaUid).get())
                        .content(comment)
                        .createTime(LocalDateTime.now())
                        .build());
        return null;
    }

    @Override
    public Page<AlbumMediaCommentSimpleRes> getAlbumMediaCommentList(Long albumMediaUid, Pageable pageable) {
        //TODO: get album media comment list
        albumMediaCommentRepository.findByAlbumMediaUid(albumMediaUid, pageable);
        return null;
    }

    @Override
    public void deleteAlbumMediaComment(Long albumMediaCommentUid) {
        //TODO: delete album media comment
    }
}
