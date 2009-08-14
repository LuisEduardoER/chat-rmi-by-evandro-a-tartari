package util.componente;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;
/**
 * 
 * @author evandro.tartari
 *
 */

public class FileFilterImpl extends FileFilter {
    private String description = "";
    private ArrayList<String> extensions = new ArrayList<String>();

    public void addExtension(String extension) {
        if (!extension.startsWith("."))
            extension = "." + extension;

        extensions.add(extension.toLowerCase());
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public String getDescription() {
        return description;
    }

    public boolean accept(File f) {

        if (f.isDirectory())
            return true;

        String name = f.getName().toLowerCase();

        for (String extension : extensions)
            if (name.endsWith(extension))
                return true;

        return false;
    }

}
