package org.example.folders;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
public class FileCabinet implements Cabinet, MultiFolder {

    private String name;
    private String size;
    private List<Folder> folders;

    public FileCabinet(String name, String size) {
        this.name = name;
        this.size = size;
        this.folders = new ArrayList<>();
    }

    public FileCabinet(String name, String size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = folders;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }


    @Override
    public Optional<Folder> findFolderByName(String name) {

        if(folders.isEmpty())
            return Optional.empty();

        for(Folder folder : folders) {
            if(folder.getName().equals(name))
                return Optional.of(folder);
            else {
                Optional<Folder> optionalFolderByName = ((Cabinet) folder).findFolderByName(name);
                if(optionalFolderByName.isPresent())
                    return optionalFolderByName;
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        List<Folder> list = new ArrayList<>();

        if(folders.isEmpty())
            return list;

        for(Folder folder : folders) {
            if(folder.getSize().equals(size))
                list.add(folder);
            List<Folder> foldersBySize = ((Cabinet) folder).findFoldersBySize(size);
            if(!foldersBySize.isEmpty())
                list.addAll(foldersBySize);
        }

        return list;
    }

    @Override
    public int count() {

        if(folders.isEmpty())
            return 0;

        int count = 0;
        count+=folders.size();

        for(Folder folder : folders) {
            count += ((Cabinet) folder).count();
        }

        return count;
    }

}
