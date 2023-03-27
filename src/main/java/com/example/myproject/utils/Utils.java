package com.example.myproject.utils;

import com.example.myproject.domain.model.view.ImageViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


public class Utils {

    public List<ImageViewModel> removeFirstImage(List<ImageViewModel> images) {
        images.remove(0);
        return images;
    }

    public String capitalizeWord(String str) {
        str = str.replace('_', ' ');
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String rest = w.substring(1);
            capitalizeWord += first.toUpperCase() + rest.toLowerCase() + " ";
        }
        return capitalizeWord.trim();
    }

    public void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    //static Destinations Descriptions Values
    public static final String SUNNY_BEACH_DESCRIPTION = "Sunny Beach is the most modern and" +
            " cosmopolitan resort on the Bulgarian Black Sea coast, located about 35 km north of Burgas." +
            "          It is located in the southern part of the Black Sea coast, in a spacious and picturesque bay." +
            " Its beach is 8 km long and in places is up to 100 meters wide. Hotels offer every opportunity" +
            " for a relaxing holiday - indoor and outdoor pools, green parks and gardens," +
            " sports facilities, restaurants, day and night bars," +
            " coffeehouses, beauty salons, spas and more. ";
}
