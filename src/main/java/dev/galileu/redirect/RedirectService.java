package dev.galileu.redirect;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {
    @Autowired private RedirectRepository redirectRepository;

    public RedirectEntity createRedirect(RedirectEntity redirectEntity) {
        RedirectEntity newRedirect = new RedirectEntity();
        newRedirect.setUrl(redirectEntity.getUrl());
        do {
            newRedirect.setShortenUrl(GenerateShortenUrl());
        } while (redirectRepository.existsByShortenUrl(newRedirect.getShortenUrl()));

        newRedirect.setCreatedAt(LocalDate.now());
        newRedirect.setValidity(redirectEntity.getValidity() > 0 ? redirectEntity.getValidity() : 30);
        return redirectRepository.save(newRedirect);
        
    }

    public String GenerateShortenUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(10);
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

    public List<RedirectEntity> getAllRedirects() {
        return redirectRepository.findAll();
    }

    public RedirectEntity getByShortenUrl(String shortUrl) {
        RedirectEntity getRedirect = new RedirectEntity();
        getRedirect = redirectRepository.findByShortenUrl(shortUrl);
        if (getRedirect == null || getRedirect.getCreatedAt().plusDays(getRedirect.getValidity()).isBefore(LocalDate.now())) {
            return null;
        }
        return getRedirect;
    }

}
