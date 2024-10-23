package libraryInterfaces;

public class ContrastInterface {
    static {
        String libraryPath = System.getProperty("user.dir") + "/ImageEffectBackend/Libraries/ContrastLibrary/ContrastLib.so";
        System.load(libraryPath);
    }

    public static native Pixel[][] applyContrast(Pixel[][] image, float amount);
}
