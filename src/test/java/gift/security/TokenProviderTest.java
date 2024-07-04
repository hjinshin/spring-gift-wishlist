package gift.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class TokenProviderTest {
    private final String secret = "secretkeysecretkeysecretkeysecretkeysecretkeysecretkey";
    private final TokenProvider tokenProvider = new TokenProvider(secret);
    @Test
    @DisplayName("Jwt Token 테스트[성공]")
    void JWtTokenTest() {
        // given
        Long id = 1L;
        String email = "test@gmail.com";
        String token = tokenProvider.generateToken(id, email);

        // when
        Long resultId = tokenProvider.extractUserId(token);

        // then
        assertThat(resultId).isEqualTo(id);
    }
}