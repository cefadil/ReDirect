package dev.galileu.redirect;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping({"/api", "/api/"})
public class RedirectController {
    @Autowired private RedirectService redirectService;
    
    @GetMapping // Welcome message
    public ResponseEntity<String> index() {return ResponseEntity.ok("Welcome to the Redirect API!");}

    @GetMapping("/{shortenURL}") // Get a redirect details by its shortened URL. If the redirect is not found or expired, it returns a 404 response.
    public ResponseEntity<RedirectEntity> getRedirect(@PathVariable String  shortenURL) {
        RedirectEntity redirect = redirectService.getByShortenUrl(shortenURL);
        return redirect == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(redirect);
    }
    
    @GetMapping({"/redirects", "/redirects/"}) //List all redirects (only for admin use)
    public ResponseEntity<List<RedirectEntity>> getall() {
        List <RedirectEntity> redirects = redirectService.getAllRedirects();
        if (redirects.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(redirects);
    }

    @PostMapping // Create a new redirect
    public ResponseEntity<RedirectEntity> createRedirect(@RequestBody RedirectEntity redirectEntity) {
        return ResponseEntity.ok(redirectService.createRedirect(redirectEntity));        
    }
    
}
