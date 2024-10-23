package libraryInterfaces;

public class GrayscaleInterface {
    static {
        String libraryPath = System.getProperty("user.dir") + "/ImageEffectBackend/Libraries/GrayscaleLibrary/GrayscaleLib.so";
        System.load(libraryPath);
    }

    public static native Pixel[][] applyGrayscale(Pixel[][] image);
}
