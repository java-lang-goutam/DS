/**
 * Given list of folders, print the path of a given folder from root. If there is no path to the root folder then return an empty string. 
 * Root level folders will have 0 as id. The structure of Folder is like this:
class Folder {
int id;
List subfolders;
String name;
}

Ex:
list = [Folder(0, [7, 3], “abc”), Folder(0, [], “xyz”), Folder(3, [], “pqr”), Folder(8, [], “def”), Folder(7, [9], “ijk), 
Folder(9, [], “lmn”)]

/abc/ijk/lmn
/abc/pqr
/xyz
?? def/

printPath(9) = “abc” -> “ijk” -> “lmn” printPath(8) = ""

 * */


import java.util.*;
import java.util.concurrent.*;

class Folder {
    int id;
    List<Integer> subFolders;
    String name;

    Folder(int id, List<Integer> subFolders, String name) {
        this.id = id;
        this.subFolders = new ArrayList<>(subFolders);
        this.name = name;
    }
}

public class PrintFolder {

    private final int ROOT_ID = 0;
    private int rootCount = 0;

    private final ConcurrentMap<Integer, Integer> folderIdToParentId;
    private final ConcurrentMap<Integer, Folder> folderIdToFolderDetails;

    public PrintFolder() {
        this.folderIdToParentId = new ConcurrentHashMap<>();
        this.folderIdToFolderDetails = new ConcurrentHashMap<>();
    }

    public void addFolder(Folder folder) {
        if (folder.id == 0) folder.id = --rootCount;
        folderIdToFolderDetails.put(folder.id, folder);
        folder.subFolders.forEach(childFolderId -> folderIdToParentId.put(childFolderId, folder.id));
    }

    public String printPath(int folderId) {
        final StringBuilder path = new StringBuilder();
        while (true) {
            final Folder currFolder = folderIdToFolderDetails.get(folderId);
            if (currFolder == null) break;
            path.insert(0, '/' + currFolder.name);
            final Integer parentFolderId = folderIdToParentId.get(folderId);
            if (parentFolderId == null) {
                if (folderId > 0) path.setLength(0);
                break;
            } else {
                folderId = parentFolderId;
            }
        }
        return path.toString();
    }

    public static void main(String... args) {
        final PrintFolder printFolder = new PrintFolder();
        printFolder.addFolder(new Folder(0, Arrays.asList(7, 3), "abc"));
        printFolder.addFolder(new Folder(0, new ArrayList<>(), "xyz"));
        printFolder.addFolder(new Folder(3, new ArrayList<>(), "pqr"));
        printFolder.addFolder(new Folder(8, new ArrayList<>(), "def"));
        printFolder.addFolder(new Folder(7, Arrays.asList(9), "ijk"));
        printFolder.addFolder(new Folder(9, new ArrayList<>(), "lmn"));

        System.out.println("Path 9 : " + printFolder.printPath(9));
        System.out.println("Path 8 : " + printFolder.printPath(8));
    }
}