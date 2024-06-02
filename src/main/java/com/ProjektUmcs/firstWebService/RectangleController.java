package com.ProjektUmcs.firstWebService;

import org.springframework.web.bind.annotation.*;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.List;

/*Napisz kontroler REST RectangleController posiadający metodę,
której wywołanie spowoduje wyświetlenie obiektu Rectangle zmapowanego na JSON.*/

@RestController
public class RectangleController {

    @GetMapping("/Rectangle")
    public Rectangle getRectangle(){
        return new Rectangle(50,50,100,200,"blue");
    }

    /*
    Zadanie 3
    W kontrolerze:
    Stwórz prywatną listę prostokątów.
    Napisz metodę, która dodaje prostokąt o określonych w kodzie parametrach.
    Napisz metodę, która zwróci listę prostokątów zmapowaną na JSON.
    Napisz metodę, która wygeneruje napis zawierający kod SVG z prostokątami znajdującymi się na liście.
     */
    private List<Rectangle> rectangles=new ArrayList<>();

    //Napisz metodę, która dodaje prostokąt o określonych w kodzie parametrach.
    @PostMapping("/addRectangle") //Wysyła nam dane
    public void addRectangle(){
        Rectangle rectToAdd= new Rectangle(120,70,100,200,"green");
        rectangles.add(rectToAdd);
    }

    //Wyswietlanie tego co mamy w liscie
    @GetMapping("/getRectangles")
    public List<Rectangle> getRectangles(){
        return rectangles;
    }

    //Napisz metodę, która wygeneruje napis zawierający kod SVG z prostokątami znajdującymi się na liście.
    @GetMapping("/svg")
    public String toSvg(){
        StringBuilder sb=new StringBuilder();
        sb.append("<svg>\n");
        sb.append("<svg height=\"800\" width=\"3000\" xmlns=\"http://www.w3.org/2000/svg\">\n" );

        for (Rectangle rect : rectangles){
            sb.append(String.format("\"<rect width=\"%d\" height=\"%d\" x=\"%d\" y=\"%d\"\n fill=\"%s\" />\n",rect.width,rect.height,rect.x, rect.y, rect.color));
        }
        sb.append("<svg>");
        return sb.toString();
    }

    //zadanie 4
    /*Niech metoda przyjmuje prostokąt, który zostanie zdefiniowany w ciele żądania HTTP.*/
    @PostMapping("/customRec")
    public void addCustomRectangle(@RequestBody Rectangle rect){
        rectangles.add(rect);
    }

/*
Zadanie 5.

Napisz metody:
    GET z argumentem typu int,  zwracającą prostokąt w liście o podanym indeksie.
    PUT z argumentem typu int i argumentem typu Rectangle, modyfikującą istniejący na liście
    pod tym indeksem prostokąt na prostokąt przekazany argumentem.
    DELETE  z argumentem typu int, usuwającą prostokąt z listy z miejsca o podanym indeksie.
     */

    //GET z argumentem typu int,  zwracającą prostokąt w liście o podanym indeksie.
//http://localhost:8080/get/0
//http://localhost:8080/get/1
    @GetMapping("/get/{index}")
    public Rectangle getRectangle(@PathVariable int index){
        return rectangles.get(index);
    }
//PUT z argumentem typu int i argumentem typu Rectangle, modyfikującą istniejący na liście
    @PostMapping("/put/{index}")
    public void putRectangle(@PathVariable int index, @RequestBody Rectangle rectangle) {
        //rectangles.remove(index) index? chyba ta
        rectangles.add(index,rectangle);
    }

    //DELETE  z argumentem typu int, usuwającą prostokąt z listy z miejsca o podanym indeksie.
    @DeleteMapping("/delete/{index}")
    public void delete(@PathVariable int index) {
        if (index >= rectangles.size() || index < 0) {
            throw new IndexOutOfBoundsException("podano zły index");
        } else {
            rectangles.remove(index);
        }
    }
}
