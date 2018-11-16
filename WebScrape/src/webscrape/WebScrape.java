package webscrape;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paco G
 */
public class WebScrape {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        String archCSV = "E:\\nuevo.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(archCSV));
        final String search = "https://www.amazon.es/s/ref=nb_sb_noss_2?__mk_es_ES=ÅMÅŽÕÑ&url=search-alias%3Daps&field-keywords=";
        Scanner busqueda= new Scanner (System.in);
        System.out.println("Introduce busqueda: ");
        String keyword = busqueda.nextLine();
        String url = search+keyword;
        
        try{
            final Document document = Jsoup.connect(url).get();
            System.out.println(url);
            Element nextpag = document.select(".pagnNext").first();
                        String next = 
                            nextpag.attr("href");
                System.out.println(next);
            if (next.equals("")){
                        System.out.println("no hay mas paginas");
                        
                    }
                else {
            for (Element row : document.select(
                    ".s-result-item"))
            {
                if(row.select(".s-result-item").text().equals("")){
                    System.out.println("no hay mas articulos");
                    continue;
                
                }
                else {
                final String titulo = 
                        row.select("h2.a-size-medium").text();
                            System.out.println(titulo);
                        Element link = row.select("a").first();
                        String links = 
                            link.attr("href");
                            //System.out.println(links);
                        Element imgs = row.select("img").first();
                        String images = 
                            imgs.attr("src");
                            System.out.println(images);
                        Element asins = row.select("li").first();
                        String asin = 
                            asins.attr("data-asin");
                            System.out.println(asin);
                                String[] parts = links.split(asin);
                                String part1 = parts[0]; // 123
                                String part2 = parts[1]; // 654321
                                String afiliado = part1+asin+"?tag=webmerchandising-21";
                                String [] dato = {afiliado, images, titulo, asin};
                                

                                writer.writeNext(dato);

                                
                }
                url = "https://www.amazon.es"+next;
                }url = "https://www.amazon.es"+next;
            }url = "https://www.amazon.es"+next;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        
        writer.close();
    }
    
}
