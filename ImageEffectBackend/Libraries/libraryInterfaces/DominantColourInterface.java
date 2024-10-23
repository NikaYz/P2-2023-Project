package libraryInterfaces;

public class DominantColourInterface {
    static {
        String libraryPath = System.getProperty("user.dir") + "/ImageEffectBackend/Libraries/DominantColourLibrary/DominantColourLib.so";
        System.load(libraryPath);
    }

    public static native Pixel[][] applyDominantColour(Pixel[][] image);
}
