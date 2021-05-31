package ouhk.comps380f.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ouhk.comps380f.dao.Photo;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.exception.CustomException;
import ouhk.comps380f.repository.PhotoRepository;
import ouhk.comps380f.repository.ShopItemRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Transactional
    @Override
    public void savePhotos(int itemId, List<MultipartFile> files) {
        ShopItem item = shopItemRepository.findById(itemId).orElseThrow(() -> new CustomException("no item found by id "+itemId));
        try {
            for (MultipartFile file : files) {
                Photo p = new Photo();
                p.setItem(item);
                p.setContent(file.getBytes());
                photoRepository.save(p);
                item.getPhotos().add(p);
            }
            shopItemRepository.save(item);
        }catch (IOException e){
            throw new CustomException(e);
        }
    }

    @Override
    public void savePhotos(int itemId, MultipartFile[] files) {
        this.savePhotos(itemId, Arrays.asList(files));
    }

    @Transactional
    @Override
    public void deletePhoto(long id) {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new CustomException("photo not found: "+id));
        ShopItem shopItem = photo.getItem();
        photoRepository.deleteById(id);
        shopItem.getPhotos().remove(photo);
        shopItemRepository.save(shopItem);
    }
}
