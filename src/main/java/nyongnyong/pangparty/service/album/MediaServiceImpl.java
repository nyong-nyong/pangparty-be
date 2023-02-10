package nyongnyong.pangparty.service.album;

import lombok.extern.slf4j.Slf4j;
import nyongnyong.pangparty.entity.album.AlbumMedia;
import nyongnyong.pangparty.repository.album.AlbumMediaRepository;
import nyongnyong.pangparty.repository.album.AlbumRepository;
import nyongnyong.pangparty.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class MediaServiceImpl implements MediaService{

    private final AlbumMediaRepository albumMediaRepository;
    private final AlbumRepository albumRepository;
    private final MemberRepository memberRepository;

    public MediaServiceImpl(AlbumMediaRepository albumMediaRepository, AlbumRepository albumRepository, MemberRepository memberRepository) {
        this.albumMediaRepository = albumMediaRepository;
        this.albumRepository = albumRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public AlbumMedia saveMedia(Long albumUid, Long memberUid, MultipartFile file) {

        String s3url = "https://s3";
        return albumMediaRepository.save(
                AlbumMedia.builder()
                        .album(albumRepository.findById(albumUid).get())
                        .member(memberRepository.findById(memberUid).get())
                        .mediaUrl(s3url)
                        .build()
        );
    }

    @Override
    public void deleteMedia(Long albumMediaUid) {

    }

    @Override
    public MultipartFile resizeMediaToSmall(MultipartFile file) {
        return null;
    }

    @Override
    public MultipartFile resizeMediaToThumbnail(MultipartFile file) {
        return null;
    }

    @Override
    public MultipartFile reformatMedia(MultipartFile file) {
        return null;
    }
}
