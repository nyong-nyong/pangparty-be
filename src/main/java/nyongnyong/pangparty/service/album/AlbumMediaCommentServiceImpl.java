package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.dto.album.AlbumMediaCommentSimpleRes;
import nyongnyong.pangparty.entity.album.AlbumMediaComment;
import nyongnyong.pangparty.entity.member.Member;
import nyongnyong.pangparty.repository.album.AlbumMediaCommentRepository;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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
        Member member = memberRepository.findById(memberUid).get();
        // 참여자 여부 확인 로직
         AlbumMediaComment albumMediaComment= albumMediaCommentRepository.save(
                AlbumMediaComment.builder()
                        .member(member)
                        .albumMedia(albumMediaRepository.findById(albumMediaUid).get())
                        .content(comment)
                        .createTime(LocalDateTime.now())
                        .build());;
        return new AlbumMediaCommentSimpleRes(albumMediaComment);
    }

    @Override
    public Page<AlbumMediaCommentSimpleRes> getAlbumMediaCommentList(Long albumMediaUid, Pageable pageable) {
        // 참여자 여부 확인 로직
        return albumMediaCommentRepository.findByAlbumMediaUid(albumMediaUid, pageable).map(AlbumMediaCommentSimpleRes::new);
    }

    @Override
    public void deleteAlbumMediaComment(Long memberUid, Long albumMediaCommentUid) {
        albumMediaCommentRepository.delete(albumMediaCommentRepository.findById(albumMediaCommentUid).get());
    }

    @Override
    public boolean isAlbumMediaCommentOwner(Long memberUid, Long albumMediaCommentUid) throws NoSuchElementException {
        if (albumMediaCommentRepository.findById(albumMediaCommentUid).get().getMember().getUid().equals(memberUid)) {
            log.debug("albumMediaComment = " + albumMediaCommentUid + " is owner");
            return true;
        }else {
            log.debug("albumMediaComment = " + albumMediaCommentUid + " is not owner");
            return false;
        }
    }
}
