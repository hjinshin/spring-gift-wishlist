package gift.service;

import gift.common.exception.EntityNotFoundException;
import gift.controller.dto.request.WishRequest;
import gift.controller.dto.response.WishResponse;
import gift.model.ProductDao;
import gift.model.WishDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishDao wishDao;
    private final ProductDao productDao;

    public WishService(WishDao wishDao, ProductDao productDao) {
        this.wishDao = wishDao;
        this.productDao = productDao;
    }

    public void save(WishRequest request, Long memberId) {
        if(!productDao.existsById(request.productId())) {
            throw new EntityNotFoundException("Product with id " + request.productId() + " does not exist");
        }
        wishDao.save(request, memberId);
    }

    public List<WishResponse> findAllByMemberId(Long memberId) {
        return wishDao.findAllByMemberId(memberId).stream()
                .map(WishResponse::from)
                .toList();
    }

    public void deleteById(Long id, Long memberId) {
        wishDao.deleteById(id, memberId);
    }
}
