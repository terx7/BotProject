package me.erki.testBot.Utils;

import com.google.common.reflect.ClassPath;
import me.erki.testBot.Main;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ClassUtils {

    public static void registerAllCommands() throws IOException{
        ClassPath cp = ClassPath.from(ClassUtils.class.getClassLoader());
        cp.getTopLevelClassesRecursive("me.erki.testBot.Commands").forEach(classInfo -> {
            try {
                Class c = Class.forName(classInfo.getName());
                Object obj = c.getDeclaredConstructor().newInstance();
                if(obj instanceof CommandExecutor commandExecutor){
                    Main.COMMANDS.put(commandExecutor.name().toLowerCase(), commandExecutor);
                }
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
