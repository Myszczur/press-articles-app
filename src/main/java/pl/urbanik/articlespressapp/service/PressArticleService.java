package pl.urbanik.articlespressapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.urbanik.articlespressapp.repository.PressArticleRepository;

@Service
@RequiredArgsConstructor
public class PressArticleService {

    private final PressArticleRepository pressArticleRepository;


}
