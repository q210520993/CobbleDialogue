package com.c1ok.cobbledialogue;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.server.level.ServerPlayer;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Utils {
    public static void sendMessage(ServerPlayer serverPlayer, String content){
        serverPlayer.sendChatMessage(new OutgoingChatMessage.Disguised(Component.literal(content)), false, ChatType.bind(ChatType.CHAT, serverPlayer));
    }

    public static void displayMessage(ServerPlayer serverPlayer,String content){
        serverPlayer.displayClientMessage(Component.literal(content).withStyle(),false);
    }

    public static File checkForDirectory(String path) {
        File dir = new File(new File("").getAbsolutePath() + path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * Write data to a file asynchronously
     */
    public static CompletableFuture<Boolean> writeFileAsync(String directory, String filename, String data) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Ensure directory exists
                File dir = checkForDirectory(directory);
                Path path = Paths.get(dir.getAbsolutePath(), filename);

                // Write file
                try (FileWriter writer = new FileWriter(path.toFile())) {
                    writer.write(data);
                }
                return true;
            } catch (Exception e) {
                System.out.println("Failed to write file: " + filename);
                return false;
            }
        });
    }

    /**
     * Read a file asynchronously
     */
    public static CompletableFuture<Boolean> readFileAsync(String directory, String filename, Consumer<String> callback) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(new File("").getAbsolutePath() + "/" + directory, filename);
                File file = path.toFile();

                if (!file.exists()) {
                    callback.accept("");
                    return false;
                }

                String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                callback.accept(content);
                return true;
            } catch (Exception e) {
                System.out.println("Failed to read file: " + filename);
                callback.accept("");
                return false;
            }
        });
    }

    /**
     * Read a file synchronously
     */
    public static String readFileSync(String directory, String filename) {
        try {
            // Ensure directory exists
            File dir = checkForDirectory("/" + directory);
            Path path = Paths.get(dir.getAbsolutePath(), filename);
            File file = path.toFile();

            if (!file.exists()) {
                return "";
            }

            return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Failed to read file: " + filename);
            return "";
        }
    }

    /**
     * Write to a file synchronously
     */
    public static boolean writeFileSync(String directory, String filename, String data) {
        try {
            // Ensure directory exists
            File dir = checkForDirectory("/" + directory);
            Path path = Paths.get(dir.getAbsolutePath(), filename);

            // Write file
            try (FileWriter writer = new FileWriter(path.toFile())) {
                writer.write(data);
            }
            System.out.println("Wrote file: " + path.toAbsolutePath());
            return true;
        } catch (Exception e) {
            System.out.println("Failed to write file: " + filename);
            return false;
        }
    }
}
