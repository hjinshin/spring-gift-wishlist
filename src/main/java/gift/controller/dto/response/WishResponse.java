package gift.controller.dto.response;

import gift.model.Wish;

import java.time.LocalDateTime;

public record WishResponse(
        Long id,

        Long productId,
        String productName,
        int productPrice,
        String productImageUrl,

        LocalDateTime createAt,
        LocalDateTime updateAt
) {
    public static WishResponse from(Wish wish) {
        return new WishResponse(wish.getId(),
                wish.getProduct().getId(), wish.getProduct().getName(),
                wish.getProduct().getPrice(), wish.getProduct().getImageUrl(),
                wish.getCreateAt(), wish.getUpdateAt());
    }
}