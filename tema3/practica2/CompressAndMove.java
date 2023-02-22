package practica2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressAndMove {
    public static void main(String[] args) throws IOException {
        String path1, path2;

        Scanner sc = new Scanner(System.in);
        System.out.println("File to compress:");
        path1 = sc.nextLine();
        System.out.println("Path to move compressed file:");
        path2 = sc.nextLine();

        Future<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                String compressedName = "compressed.zip";
                FileOutputStream fos = new FileOutputStream(compressedName);
                ZipOutputStream zipOut = new ZipOutputStream(fos);

                File fileToZip = new File(path1);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }

                zipOut.close();
                fis.close();
                fos.close();
                return compressedName;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).whenComplete((response, error) -> {
            try {
                Files.move(Path.of(response), Path.of(path2 + "/" + response), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        while (!future.isDone()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
