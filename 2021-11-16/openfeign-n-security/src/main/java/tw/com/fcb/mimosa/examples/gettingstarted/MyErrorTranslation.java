package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.domain.t9n.Term;
import tw.com.fcb.mimosa.domain.t9n.Translated;
import tw.com.fcb.mimosa.domain.t9n.TranslationService;

@Service
@RequiredArgsConstructor
public class MyErrorTranslation implements TranslationService {

  final ErrorCodeRepository repository;

  @Override
  public Optional<Translated> translate(@NonNull Term term) {
    //    if (term.getCode().equals("ERR1")) {
    //      return Optional.of(
    //          new MyTranslation(term.getCategory(),
    //              term.getCode(),
    //              "查無姓名"));
    //    }
    //    return Optional.empty();
    return repository.findByCategoryAndCode(term.getCategory(), term.getCode());
  }

  //  @Getter
  //  @RequiredArgsConstructor
  //  static class MyTranslation implements Translated {
  //    final String category;
  //    final String code;
  //    final String translation;
  //  }

}
