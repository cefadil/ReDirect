package dev.galileu.redirect;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RedirectRepository extends JpaRepository<RedirectEntity, Long> {
    boolean existsByShortenUrl(String shortenUrl);
    RedirectEntity findByShortenUrl(String shortenUrl);
}
