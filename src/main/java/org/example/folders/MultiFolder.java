package org.example.folders;

import java.util.List;

public interface MultiFolder extends Folder {
    List<Folder> getFolders();
}
