package nyongnyong.pangparty.service.album;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService{


    @Value("${cloud.aws.s3.region}")
    private Regions clientRegion;   // 리전

    @Value("${cloud.aws.s3.bucket}")
    private String bucket; // 버킷 명

    private final AmazonS3 s3Client;

    @Override
    public String uploadMedia(MultipartFile file, String key, String contentType, long contentLength) {
        upload(file, key, contentType, contentLength);
        return "https://" + bucket + ".s3." + clientRegion + ".amazonaws.com/" + key;
    }

    @Override
    public void deleteMedia(String key) {
        delete(key);
        log.debug("deleted albumMediaKey = " + key);
    }

    @Override
    public MultipartFile resizeMediaToAlbumSize(MultipartFile file) {
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

    /**
     * MultipartFile을 S3에 업로드
     * @param multipartFile 파일
     * @param key 파일경로/파일명.확장자
     * @param contentType 파일 확장자
     * @param contentLength 파일 크기
     */
    private void upload(MultipartFile multipartFile, String key, String contentType, long contentLength) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(contentLength);
        try{
            InputStream file = multipartFile.getInputStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, file, objectMetadata);
            uploadToS3(putObjectRequest);
        } catch(IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.");
        }
    }

    /**
     * S3에 업로드
     * @param putObjectRequest Aws s3 버킷에 업로드할 객체 메타 데이터와 파일 데이터를 담은 객체
     */
    private void uploadToS3(PutObjectRequest putObjectRequest) {
        try {
            s3Client.putObject(putObjectRequest);
            System.out.println(String.format("[%s] upload complete", putObjectRequest.getKey()));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * S3에서 삭제
     * @param key 파일경로/파일명.확장자
     */
    private void delete(String key) {
        try {
            // Delete 객체 생성
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, key);

            s3Client.deleteObject(deleteObjectRequest);

            System.out.printf(String.format("[%s] delete key"), key);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

}
