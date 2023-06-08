package me.Incbom.srm.Commands;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class Group implements CommandExecutor, TabCompleter {
    
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        return completions;
    }

    public static String getPlayerGroup(Player player, Collection<String> possibleGroups) {
        for (String group : possibleGroups) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("group") && args.length == 0) {
            if (sender.hasPermission("srm.group")) {
                sender.sendMessage(ChatColor.RED + "Incorrect usage. Usage: /group <user> <possible groups>");
                sender.sendMessage(ChatColor.GREEN + "For possible groups, separate groups you want to check the user by with commas");
                return true;
            }
        } else if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "Incorrect usage. Usage: /group <user> <possible groups>");
            sender.sendMessage(ChatColor.GREEN + "For possible groups, separate groups you want to check the user by with commas");
        } else if (args.length == 2) {
            Player player = (Player) sender;
            String group = args[1];
            String[] groupsArray = group.split(",");
            List<String> groupsList = Arrays.asList(groupsArray);
            String playerGroup = getPlayerGroup(player, groupsList);
            if (playerGroup == null) {
                sender.sendMessage(ChatColor.RED + "Incorrect usage. Usage: /group <user> <possible groups>");
                sender.sendMessage(ChatColor.GREEN + "For possible groups, separate groups you want to check the user by with commas");
                return true;
            }
            sender.sendMessage(ChatColor.GREEN + playerGroup);
            return true;
        }

        return false;
    }
}
