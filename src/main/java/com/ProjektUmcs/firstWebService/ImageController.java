package com.ProjektUmcs.firstWebService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/*
zadanie 6
Napisz kontroler REST ImageController, w którym znajdzie się metoda zawracająca obraz ze zmodyfikowaną jasnością.
Metoda typu GET powinna przyjąć obraz w formacie base64 oraz liczbę całkowitą określającą jasność.
Metoda powinna rozjaśnić obraz o podaną wartość i zwrócić go w formacie base64.*/

//rest controller nie pozwoli na pobranie templatki html, czyl ijakby dać return image.html,
// to wyświetli na stronie "image.html", a nie afaktyczny plik html
// jak będzie zwykły @Controller, to wtedy pobierze sobie tę tempaltkę html
@RestController
public class ImageController {
    @GetMapping("/imageBrightness")
    public String showBrightnessImage(@RequestParam String base64Image, @RequestParam int level) {
        if (base64Image.startsWith("data:image")) {
            base64Image = base64Image.substring(base64Image.indexOf(",") + 1);
        }
        base64Image = base64Image.replace(" ", "+");
        base64Image = base64Image.replace("\n", "");
//        System.out.println(base64Image);
//        System.out.println(base64Image);
        String increasedBrightness = increaseBrigthnessReturnBase64(level,base64Image);
//        System.out.println(increasedBrightness);
        String output = "<div>\n" +
                "  <img src=\"data:image/jpeg;base64," + increasedBrightness + "\" alt=\"Cursed cat\" />\n" +
                "</div>";
        return output;
    }

    private String increaseBrigthnessReturnBase64(int level, String base64Image) {
        BufferedImage image = increaseBrigthnessReturnBufferedImage(level, base64Image);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
        }
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }
    /*
    Zadanie 7.
    Napisz kolejną, zbliżoną metodę, w której wyniku znajdzie się niezakodowany obraz.
    */
    private BufferedImage increaseBrigthnessReturnBufferedImage(int level, String base64Image) {
        byte[] bytes = DatatypeConverter.parseBase64Binary(base64Image);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int b = rgb & 0XFF;
                int g = (rgb & 0XFF00) >> 8;
                int r = (rgb & 0XFF0000) >> 16;

                int newB = clamp(b + level, 0, 255);
                int newG = clamp(g + level, 0, 255);
                int newR = clamp(r + level, 0, 255);

                image.setRGB(x, y, (newR << 16) + (newG << 8) + newB);
            }
        }
        return image;
    }

    private static int clamp(int value, int min, int max){
        if (value > max)
            return max;
        if (value<min)
            return min;
        return value;
    }
}
