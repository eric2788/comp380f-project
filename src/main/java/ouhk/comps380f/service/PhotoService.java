package ouhk.comps380f.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PhotoService {

    void savePhotos(int itemId, List<MultipartFile> files);

    void savePhotos(int itemId, MultipartFile[] files);

    void deletePhoto(long id);

}
