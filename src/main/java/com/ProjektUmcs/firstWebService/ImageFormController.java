package com.ProjektUmcs.firstWebService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/*
Zadanie 8.
Napisz kontroler ImageFormController.
Skorzystaj z udostępnionych szablonów w plikach index.html i image.html. W kontrolerze napisz:
Metodę, która wyświetli plik index.html.
Metodę upload, która zostanie wyzwolona przez naciśnięcie przycisku Upload.
Metoda powinna wyświetlić plik image.html, wyświetlając w nim przesłany obraz.
*/

//musi być zwykły controller, bo jakby był rest to  nie pobrałby sobie templatki
@Controller
public class ImageFormController {

    @GetMapping("/")
    public String viewHomePage() {
        return "index.html";
    }

    @PostMapping("/imageform/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        System.out.println("n");
        return "redirect:/";
    }

    /*
Zadanie 9.
Zmodyfikuj formatkę w pliku index.html i dodaj do niej pole, w którym można ustawić zmianę jasności.
Niech naciśnięcie przycisku Upload spowoduje wyświetlenie załadowanego obrazu rozjaśnionego o daną wartość.
*/

}
